package com.power.home.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.BindEventBus;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.DrawableUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.FriendsBean;
import com.power.home.data.bean.ShareImgeBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerInviteFriendComponent;
import com.power.home.di.module.InviteFriendlModule;
import com.power.home.presenter.InviteFriendPresenter;
import com.power.home.presenter.contract.InviteFriendContract;
import com.power.home.ui.adapter.PagerFragmentAdapter;
import com.power.home.ui.fragment.ShareFragment;
import com.power.home.ui.widget.MyTitleBar;
import com.power.home.ui.widget.ScalePageTransformer;
import com.power.home.wxapi.WxShare;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;

@BindEventBus
@Route(path = "/android/invitedFriend")
public class InviteFriendActivity extends BaseActivity<InviteFriendPresenter> implements InviteFriendContract.View, View.OnClickListener {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.tv_share_wx)
    TextView tvShareWx;
    @BindView(R.id.tv_share_friends)
    TextView tvShareFriends;
    @BindView(R.id.tv_share_location)
    TextView tvShareLocation;
    @BindView(R.id.viewPager_share)
    ViewPager viewPagerShare;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
//    @BindView(R.id.rl_share_change)
//    RelativeLayout rlShareChange;
//    @BindView(R.id.rl_share_write)
//    RelativeLayout rlShareWrite;
    @BindView(R.id.tv_invite)
    TextView tv_invite;
    private List<Fragment> list = new ArrayList<>();
    private List<String> posterImages;
    private List<String> slogans;
    private int position = 0;
    private String inviteUrl;
    private PagerFragmentAdapter pagerFragmentAdapter;
    private PopupWindow mPopupWindow;
    private Bitmap drawingCache;


    @Override
    public int setLayout() {
        return R.layout.activity_invite;
    }

    @Override
    public void getExtras() {
    }

    @Override
    public boolean init() {
        titleBar.setRightLayoutVisibility(View.GONE);
        mPresenter.inviteFriends();
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EventBusEvent date) {
        if (date != null) {
            switch (date.getCode()) {
                case EventBusUtils.EventCode.FINISH_EDIT_SHARE:
                    ShareImgeBean share = (ShareImgeBean) date.getData();
                    for (int i = 0; i < list.size(); i++) {
                        if (i != share.getPosition()) {
                            ShareFragment shareFragment = (ShareFragment) list.get(i);
//                            if (null != shareFragment.etTitle) {
//                                shareFragment.etTitle.setText(share.getTitle());
//                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        tvShareWx.setOnClickListener(this);
        tvShareFriends.setOnClickListener(this);
        tvShareLocation.setOnClickListener(this);
        tv_invite.setOnClickListener(this);
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/android/" + "invitedRecord")
                        .withString(Constant.type,"INVITED").navigation();
            }
        });
    }

    private void showSharePopup() {

        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
        backgroundAlpha(0.8f);
        mPopupWindow = new PopupWindow(this);
        mPopupWindow.setHeight(FrameLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setWidth(FrameLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);

        mPopupWindow.setAnimationStyle(R.style.PopupAnimation);
        mPopupWindow.setClippingEnabled(false);
        ViewGroup popView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.item_popwind_share, null);

        View rlContent = popView.findViewById(R.id.rlContent);


        TextView tvSharewx = popView.findViewById(R.id.tv_share_wx);
        TextView tvShareFriend = popView.findViewById(R.id.tv_share_friends);
        TextView tv_share_poster = popView.findViewById(R.id.tv_share_poster);


        mPopupWindow.setContentView(popView);
        mPopupWindow.showAtLocation(popView, Gravity.CENTER, 0, 0);
        mPopupWindow.setOnDismissListener(new PopDismissListener());

        rlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPop();
            }
        });

        tvSharewx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WxShare().WxSharePic(drawingCache, 1);
                dismissPop();
            }
        });

        tvShareFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WxShare().WxSharePic(drawingCache, 2);
                dismissPop();
            }
        });
        tv_share_poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(InviteFriendActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(InviteFriendActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    DrawableUtil.saveImageToGallery(drawingCache, "InviteFriend.jpg");
                }

            }
        });
    }

    public void dismissPop() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    /**
     * 设置弹出Popupwindow以外区域的透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    /**
     * PopupWindow 消失之后设置背景透明度为正常
     */
    private class PopDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerInviteFriendComponent.builder().appComponent(appComponent)
                .inviteFriendlModule(new InviteFriendlModule(this))
                .build().inject(this);
    }

    /**
     * viewPager 初始化必须在请求数据以后初始化，否则首次进入缩放效果失效
     *
     * @param friendsBean
     */
    @Override
    public void inviteFriendsSuccess(FriendsBean friendsBean) {
        titleBar.setRightLayoutVisibility(View.VISIBLE);
        inviteUrl = friendsBean.getInviteUrl();
        posterImages = friendsBean.getPosterImages();
        slogans = friendsBean.getSlogans();
        list.clear();
        for (int i = 0; i < posterImages.size(); i++) {
            list.add(getFragment(slogans.get(position), i));
        }
        pagerFragmentAdapter = new PagerFragmentAdapter(getSupportFragmentManager());
        pagerFragmentAdapter.setFragments(list);
        viewPagerShare.setAdapter(pagerFragmentAdapter);
        viewPagerShare.setPageMargin(UIUtil.dip2px(24));
        viewPagerShare.setOffscreenPageLimit(6);
        viewPagerShare.setPageTransformer(true, new ScalePageTransformer());
        indicator.setViewPager(viewPagerShare);
        pagerFragmentAdapter.registerDataSetObserver(indicator.getDataSetObserver());

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rl_share_change:
                getNext();
                ShareFragment shareFragment = (ShareFragment) list.get(viewPagerShare.getCurrentItem());
//                shareFragment.etTitle.setText(slogans.get(position));
                break;
            case R.id.rl_share_write:
                ShareFragment currentShareFragment = (ShareFragment) list.get(viewPagerShare.getCurrentItem());
//                showSoftInputFromWindow(currentShareFragment.etTitle);
                break;
            case R.id.tv_invite:
                ShareFragment fragment = (ShareFragment) list.get(viewPagerShare.getCurrentItem());
//                if (fragment.tvChosePic != null) {
//                    fragment.tvChosePic.setVisibility(View.GONE);
//                }

                viewPagerShare.setDrawingCacheEnabled(true);
                viewPagerShare.buildDrawingCache();
                drawingCache = Bitmap.createBitmap(viewPagerShare.getDrawingCache());
//                if (fragment.tvChosePic != null) {
//                    fragment.tvChosePic.setVisibility(View.VISIBLE);
//                }
                showSharePopup();

                break;
        }
        viewPagerShare.destroyDrawingCache();
    }

    private void getNext() {
        position++;
        if (position == 6) {
            position = 0;
        }
    }

    private ShareFragment getFragment(String title, int position) {
        ShareFragment shareFragment = new ShareFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.title, title);
        bundle.putInt(Constant.position, position);
        bundle.putString(Constant.url, posterImages.get(position));
        bundle.putString(Constant.inviteUrl, inviteUrl);
        bundle.putString(Constant.inviteFrontName, "邀请您加入");
        bundle.putString(Constant.inviteBehindName, "，一起学习");
        bundle.putString(Constant.courseName, getString(R.string.app_name));
        shareFragment.setArguments(bundle);
        return shareFragment;
    }

    public void showSoftInputFromWindow(EditText editText) {
        editText.setEnabled(true);
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.setSelection(editText.getText().toString().length());
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);
    }
}
