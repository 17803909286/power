package com.power.home.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.BindEventBus;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.DrawableUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.ShareBean;
import com.power.home.data.bean.ShareImgeBean;
import com.power.home.di.component.AppComponent;
import com.power.home.ui.adapter.PagerFragmentAdapter;
import com.power.home.ui.fragment.ShareFragment;
import com.power.home.ui.widget.MySharePicturePopup;
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
public class ShareActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.viewPager_share)
    ViewPager viewPagerShare;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    @BindView(R.id.rl_share_change)
    RelativeLayout rlShareChange;
    @BindView(R.id.rl_share_write)
    RelativeLayout rlShareWrite;
    @BindView(R.id.titleBar)
    MyTitleBar titleBar;


    private List<Fragment> list = new ArrayList<>();
    private List<String> posterImages;
    private List<String> slogans;
    private int position = 0;
    private PagerFragmentAdapter pagerFragmentAdapter;
    private ShareBean shareBean;
    private Bitmap drawingCache;


    @Override
    public int setLayout() {
        return R.layout.activity_share;
    }

    @Override
    public void getExtras() {
        shareBean = (ShareBean) getIntent().getSerializableExtra(Constant.data);
    }

    @Override
    public boolean init() {
        initView();
        return false;
    }

    private void initView() {

        posterImages = shareBean.getPosterImgs();
        slogans = shareBean.getSlogans();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EventBusEvent date) {
        if (date != null) {
            switch (date.getCode()) {
                case EventBusUtils.EventCode.FINISH_EDIT_SHARE:
                    ShareImgeBean share = (ShareImgeBean) date.getData();
                    for (int i = 0; i < list.size(); i++) {
                        if (i != share.getPosition()) {
                            ShareFragment shareFragment = (ShareFragment) list.get(i);
                            if (null != shareFragment.etTitle) {
                                shareFragment.etTitle.setText(share.getTitle());
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        rlShareChange.setOnClickListener(this);
        rlShareWrite.setOnClickListener(this);
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareFragment fragment = (ShareFragment) list.get(viewPagerShare.getCurrentItem());
                if (fragment.tvChosePic != null) {
                    fragment.tvChosePic.setVisibility(View.GONE);
                }

                viewPagerShare.setDrawingCacheEnabled(true);
                viewPagerShare.buildDrawingCache();
                drawingCache = Bitmap.createBitmap(viewPagerShare.getDrawingCache());
                if (fragment.tvChosePic != null) {
                    fragment.tvChosePic.setVisibility(View.VISIBLE);
                }

                MySharePicturePopup mySharePicturePopup = new MySharePicturePopup(ShareActivity.this);
                mySharePicturePopup.showPopWindow();
                mySharePicturePopup.tvSharewx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new WxShare().WxSharePic(drawingCache, 1);
                        mySharePicturePopup.dismissPop();
                    }
                });
                mySharePicturePopup.tvShareFriend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new WxShare().WxSharePic(drawingCache, 2);
                        mySharePicturePopup.dismissPop();
                    }
                });
                mySharePicturePopup.tv_share_poster.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ContextCompat.checkSelfPermission(ShareActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(ShareActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        } else {
                            DrawableUtil.saveImageToGallery(drawingCache, shareBean.getCourseName() + ".jpg");
                        }
                        mySharePicturePopup.dismissPop();
                    }
                });
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rl_share_change:
                getNext();
                ShareFragment shareFragment = (ShareFragment) list.get(viewPagerShare.getCurrentItem());
                shareFragment.etTitle.setText(slogans.get(position));
                break;
            case R.id.rl_share_write:
                ShareFragment currentShareFragment = (ShareFragment) list.get(viewPagerShare.getCurrentItem());
                showSoftInputFromWindow(currentShareFragment.etTitle);
                break;
        }

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
        bundle.putString(Constant.inviteUrl, shareBean.getShareUrl());
        bundle.putString(Constant.inviteFrontName, shareBean.getInviteFrontName());
        bundle.putString(Constant.inviteBehindName, shareBean.getInviteBehindName());
        bundle.putString(Constant.courseName, shareBean.getCourseName());
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
