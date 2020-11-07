package com.power.home.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.ClipboardManagerUtil;
import com.power.home.common.util.DrawableUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.StatusBarUtil;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.common.util.UserCacheUtil;
import com.power.home.data.bean.ForwardAddressBean;
import com.power.home.data.bean.PersonCenterBean;
import com.power.home.data.bean.UserInfoChildBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerMainMineComponent;
import com.power.home.di.module.MainMineModule;
import com.power.home.presenter.MainMinePresenter;
import com.power.home.presenter.contract.MainMineContract;
import com.power.home.ui.activity.PersonInfoActivity;
import com.power.home.ui.activity.WebViewActivity;
import com.power.home.ui.adapter.MineDiamonAdapter;
import com.power.home.ui.adapter.MineMenuAdapter;
import com.power.home.ui.widget.CircleImageView;
import com.power.home.ui.widget.RedTipRechImageView;
import com.power.home.ui.widget.floatview.FloatingView;

import butterknife.BindView;


/**
 * Created by zhangpeng on 2017/11/23.
 */

public class MainMineFragment extends BaseFragment<MainMinePresenter> implements View.OnClickListener, MainMineContract.View {

    @BindView(R.id.civ_head)
    CircleImageView civHead;
    @BindView(R.id.tv_login_name)
    TextView tvLoginName;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_copy)
    TextView tvCopy;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.ll_level)
    LinearLayout llLevel;

    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.swipeRefreshLayout_mine)
    SwipeRefreshLayout swipeRefreshLayoutMine;
    @BindView(R.id.recycle_hor)
    RecyclerView recycleHor;
    @BindView(R.id.recycle_ver)
    RecyclerView recycleVer;
    @BindView(R.id.iv_main_mine_adv)
    ImageView ivMainMineAdv;
    @BindView(R.id.iv_mine_is_vip)
    ImageView ivMineIsVip;
    @BindView(R.id.red_mine_message)
    RedTipRechImageView redMineMessage;
    @BindView(R.id.tv_login_tip)
    TextView tvLoginTip;

    private String recCode;
    private MineDiamonAdapter mineDiamonAdapter;
    private MineMenuAdapter mineMenuAdapter;
    private GridLayoutManager gridLayoutManager;
    private UserInfoChildBean userInfoChildBean;
    private String queryType = "NON_AGENT";
    /*NON_AGENT("NON_AGENT", "普通用户"),
    AMBASSADOR("AMBASSADOR", "推广大使"),
    SCHOOLMASTER("SCHOOLMASTER", "联盟校长"),
    HEADQUARTERS("HEADQUARTERS","总部");*/


    @Override
    public int setLayout() {
        StatusBarUtil.transparencyBar2(getActivity());
        if (null != FloatingView.get().getView()) {
            FloatingView.get().getView().setVisibility(View.GONE);
        }
        StatusBarUtil.setStatusTextColor(false, getActivity());
        return R.layout.fragment_mine;
    }


    @Override
    protected void getExtras() {

    }

    @Override
    public void init() {

        gridLayoutManager = new GridLayoutManager(UIUtil.getContext(), 3, GridLayoutManager.VERTICAL, false);
        recycleHor.setLayoutManager(gridLayoutManager);
        recycleHor.setNestedScrollingEnabled(false);//禁止滑动 recycleHor.setNes
        recycleHor.setHasFixedSize(true);
        recycleHor.setFocusable(false);//禁止获取焦点
        mineDiamonAdapter = new MineDiamonAdapter(R.layout.item_mine_diamon, null);
        recycleHor.setAdapter(mineDiamonAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtil.getContext(), LinearLayoutManager.VERTICAL, false);
        recycleVer.setLayoutManager(linearLayoutManager);
        recycleVer.setNestedScrollingEnabled(false);//禁止滑动
        recycleVer.setHasFixedSize(true);
        recycleVer.setFocusable(false);//禁止获取焦点
        mineMenuAdapter = new MineMenuAdapter(R.layout.item_mine_menu, null);
        recycleVer.setAdapter(mineMenuAdapter);

//        showInfo();
        swipeRefreshLayoutMine.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getUserInfo(SharePreferencesUtils.getUserId());
                mPresenter.myPage(SharePreferencesUtils.getUserId());
            }
        });
    }

    /**
     * 根据是否登录，做页面显示以及数据处理
     */
    private void showInfo() {
        mPresenter.myPage(SharePreferencesUtils.getUserId());
        if (UserCacheUtil.isLogin()) {
            swipeRefreshLayoutMine.setEnabled(true);
            mPresenter.getUserInfo(SharePreferencesUtils.getUserId());
            llLevel.setVisibility(View.VISIBLE);
            tvLoginTip.setVisibility(View.GONE);

        } else {
            swipeRefreshLayoutMine.setEnabled(false);
            llLevel.setVisibility(View.GONE);
            tvLoginTip.setVisibility(View.VISIBLE);
            redMineMessage.setVisibility(RedTipRechImageView.RED_TIP_INVISIBLE);
        }
    }

    @Override
    protected void setListener() {
        redMineMessage.setOnClickListener(this);
        tvLoginName.setOnClickListener(this);
        civHead.setOnClickListener(this);
        ivMainMineAdv.setOnClickListener(this);
        tvCopy.setOnClickListener(this);

        mineDiamonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (StringUtil.isNotNullString(mineDiamonAdapter.getData().get(position).getIsNeedLogin()) &&
                        mineDiamonAdapter.getData().get(position).getIsNeedLogin().equals("true")) {

                    if (UserCacheUtil.isLogin()) {
                        jump(mineDiamonAdapter.getData().get(position));
                    } else {
                        goLogin();
                    }

                } else {
                    jump(mineDiamonAdapter.getData().get(position));
                }
            }
        });

        mineMenuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (StringUtil.isNotNullString(mineMenuAdapter.getData().get(position).getIsNeedLogin()) &&
                        mineMenuAdapter.getData().get(position).getIsNeedLogin().equals("true")) {

                    if (UserCacheUtil.isLogin()) {
                        jump(mineMenuAdapter.getData().get(position));
                    } else {
                        goLogin();
                    }

                } else {
                    if (StringUtil.isNotNullString(mineMenuAdapter.getData().get(position).getId()) &&
                            mineMenuAdapter.getData().get(position).getForwardAddress().contains("contactUs")) {
                        UIUtil.goCall(mineMenuAdapter.getData().get(position).getSubtitle(), getActivity());
                    } else {
                        jump(mineMenuAdapter.getData().get(position));
                    }
                }
            }
        });

    }

    private void jump(PersonCenterBean.MenuBean menuBean) {
        String forwardAddress = menuBean.getForwardAddress();
        ForwardAddressBean forwardAddressBean = new Gson().fromJson(forwardAddress, ForwardAddressBean.class);

        switch (menuBean.getForwardType()) {
            //1:原生   2:web
            case "1":
                ARouter.getInstance().build("/android/" + forwardAddressBean.getRouter())
                        .withString(Constant.type, forwardAddressBean.getRouter().contains("invitedRecord") ? "INVITED" : queryType).navigation();
                break;
            case "2":
                WebViewActivity.launcher(UIUtil.getContext(), forwardAddressBean.getWebUrl(), menuBean.getTitle());
                break;
        }
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

        DaggerMainMineComponent.builder().appComponent(appComponent)
                .mainMineModule(new MainMineModule(this))
                .build().inject(this);
    }

    //startActivity(new Intent(UIUtil.getContext(), VipRecordActivity.class));
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_copy:
                ClipboardManagerUtil.copy(recCode, UIUtil.getContext());
                UIUtil.showLongToastSafe("复制成功");
                break;
            case R.id.red_mine_message:
                if (UserCacheUtil.isLogin()) {
                    ARouter.getInstance().build("/android/message").navigation();
                } else {
                    goLogin();
                }
                break;
            case R.id.tv_login_name:
            case R.id.civ_head:
                if (UserCacheUtil.isLogin()) {
                    if (userInfoChildBean == null) {
                        UIUtil.showToastSafe("网络异常,无法修改个人资料");
                        return;
                    }
                    Intent intent = new Intent(UIUtil.getContext(), PersonInfoActivity.class);
                    intent.putExtra(Constant.data, userInfoChildBean);
                    startActivity(intent);
                } else {
                    goLogin();
                }
                break;
            case R.id.iv_main_mine_adv:
                ARouter.getInstance().build("/android/growth").navigation();
                break;
        }
    }

    @Override
    public void getUserInfoSuccess(UserInfoChildBean userInfoChildBean) {
        swipeRefreshLayoutMine.setRefreshing(false);
        this.userInfoChildBean = userInfoChildBean;
        UserCacheUtil.saveInfo(userInfoChildBean);
    }


    @SuppressLint("WrongConstant")
    @Override
    public void myPageSuccess(PersonCenterBean personCenterBean) {

        if (null != personCenterBean) {
            gridLayoutManager.setSpanCount(personCenterBean.getDiamond().size());
            mineDiamonAdapter.setNewData(personCenterBean.getDiamond());
            mineMenuAdapter.setNewData(personCenterBean.getMenu());
            mineDiamonAdapter.setGrowthPromotionActive(personCenterBean.getGrowthPromotionActive());
            mineDiamonAdapter.setGrowthPromotionIcon(personCenterBean.getGrowthPromotionIcon());
            initLottie(personCenterBean);
            recCode = personCenterBean.getRecCode();
            tvCode.setText("我的推广码：" + recCode);
            tvLoginName.setText(StringUtil.isNullString(personCenterBean.getNickName()) ? "登录注册" : personCenterBean.getNickName());
            DrawableUtil.loadUrl(R.drawable.icon_avatar_header, civHead, personCenterBean.getAvatar());

            queryType = personCenterBean.getAgentLevel();
            if (StringUtil.isNotNullString(queryType)) {
                //代理等级: AMBASSADOR：推广大使，SCHOOLMASTER：联盟校长
                switch (queryType) {
                    case "AMBASSADOR":
                        tvLevel.setText("推广大使");
                        tvLevel.setVisibility(View.VISIBLE);
                        break;
                    case "SCHOOLMASTER":
                        tvLevel.setText("联盟校长");
                        tvLevel.setVisibility(View.VISIBLE);
                        break;
                    case "HEADQUARTERS":
                        tvLevel.setText("总部");
                        tvLevel.setVisibility(View.VISIBLE);
                        break;
                    case "NON_AGENT":
                        tvLevel.setVisibility(View.GONE);
                        break;
                }
            }

            if (personCenterBean.isHappinessVip()) {
                tvDate.setVisibility(View.VISIBLE);
                if (personCenterBean.getHappinessVipSource().equals("PAY")) {
                    tvDate.setText("动力营剩余时间" + personCenterBean.getHappinessVipSurplusDays() + "天");
                } else {
                    tvDate.setText("免费体验还剩" + personCenterBean.getHappinessVipSurplusDays() + "天");
                }
            } else {
                tvDate.setVisibility(View.GONE);
            }

            tvCode.setText("我的推广码：" + personCenterBean.getRecCode());
            if (UserCacheUtil.isLogin()) {
                tvLoginName.setText(personCenterBean.getNickName());
            }
            if (personCenterBean.isHappinessVip()) {
                ivMineIsVip.setBackgroundResource(R.drawable.icon_mine_vip);
            } else {
                ivMineIsVip.setBackgroundResource(R.drawable.icon_mine_vip_null);
            }
            if (personCenterBean.getAllRead()) {
                redMineMessage.setVisibility(RedTipRechImageView.RED_TIP_INVISIBLE);
            } else {
                redMineMessage.setVisibility(RedTipRechImageView.RED_TIP_VISIBLE);
            }
        }
    }

    private void initLottie(PersonCenterBean personCenterBean) {
        if (personCenterBean != null && personCenterBean.getAd().size() > 0) {
            ivMainMineAdv.setVisibility(View.VISIBLE);
            Glide.with(UIUtil.getContext()).load(personCenterBean.getAd().get(0).getDisplayPic()).into(ivMainMineAdv);
        } else {
            ivMainMineAdv.setVisibility(View.GONE);
        }

        ivMainMineAdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (personCenterBean != null && personCenterBean.getAd().size() != 0) {
                    String forwardAddress = personCenterBean.getAd().get(0).getForwardAddress();
                    String type = personCenterBean.getAd().get(0).getForwardType();
                    ForwardAddressBean forwardAddressBean = new Gson().fromJson(forwardAddress, ForwardAddressBean.class);
                    switch (type) {
                        case "1":
                            ARouter.getInstance().build("/android/" + forwardAddressBean.getRouter())
                                    .withString(Constant.type, personCenterBean.getAd().get(0).getType())
                                    .withString(Constant.id, forwardAddressBean.getId())
                                    .navigation();
                            break;
                        case "2":
                            WebViewActivity.launcher(UIUtil.getContext(), forwardAddressBean.getWebUrl(), personCenterBean.getAd().get(0).getTitle());
                            break;
                    }

                }
            }
        });
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        swipeRefreshLayoutMine.setRefreshing(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        showInfo();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            setStatusBarColor(getActivity(), getActivity().getResources().getColor(R.color.colorWhite00));
            StatusBarUtil.setStatusTextColor(false, getActivity());
        }
    }

    /**
     * 设置状态栏颜色
     *
     * @param activity
     */
    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            //状态栏改变颜色。
            window.setStatusBarColor(color);
        }
    }
}
