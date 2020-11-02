package com.power.home.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.BindEventBus;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.ToastUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.common.util.UserCacheUtil;
import com.power.home.data.bean.CommodityBean;
import com.power.home.data.bean.EMBAOnlineBean;
import com.power.home.data.bean.ShareBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerEMBAOlineComponent;
import com.power.home.di.module.EMBAOnlineModule;
import com.power.home.presenter.EMBAOnlinePresenter;
import com.power.home.presenter.contract.EMBAOnlineContract;
import com.power.home.ui.adapter.EMBAOnlineAdapter;
import com.power.home.ui.widget.MyTitleBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * @author xwl
 * @description:在线EMBA
 * @date :2020/2/20
 */
@BindEventBus
@Route(path = "/android/emba")
public class EMBAOnlineActivity extends BaseActivity<EMBAOnlinePresenter> implements EMBAOnlineContract.View, View.OnClickListener {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;

    @BindView(R.id.iv_course_icon)
    ImageView ivCourseIcon;
    @BindView(R.id.tv_course_title)
    TextView tvCourseTitle;
    @BindView(R.id.tv_course_content)
    TextView tvCourseContent;
    @BindView(R.id.tv_recommend_album)
    TextView tvRecommendAlbum;
    @BindView(R.id.recycle_recommend_album)
    RecyclerView recycleRecommendAlbum;
    @BindView(R.id.tv_expired_time)
    TextView tvExpiredTime;
    @BindView(R.id.tv_renew)
    TextView tvRenew;
    @BindView(R.id.tv_emba_buy)
    TextView tvEmbaBuy;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.NestedScrollView)
    androidx.core.widget.NestedScrollView NestedScrollView;
    private EMBAOnlineAdapter embaOnlineAdapter;
    private EMBAOnlineBean embaOnlineBean;

    @Override
    public int setLayout() {
        return R.layout.activity_embaonline;
    }

    @Override
    public void getExtras() {

    }

    @Override
    public boolean init() {
        initView();
        initWeb();
        initRecycler();
        mPresenter.getEmbaData();
        return false;
    }

    private void initWeb() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setLoadWithOverviewMode(true);//适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setUserAgentString(webSettings.getUserAgentString() + "android");
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        String cacheDirPath = "/data/data/" + getPackageName() + "/databases/";
        //      String cacheDirPath = getCacheDir().getAbsolutePath()+Constant.APP_DB_DIRNAME;
        Log.i("WebActivity", "cacheDirPath=" + cacheDirPath);
        //设置  Application Caches 缓存目录
        webSettings.setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能

        webView.setBackgroundColor(UIUtil.getColor(R.color.colorWhiteEEEEEE));
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webView.getSettings().setDatabasePath(cacheDirPath);
        }
    }

    private void initView() {
        if (SharePreferencesUtils.getIsEmba()) {
            webView.setVisibility(View.GONE);
            NestedScrollView.setVisibility(View.VISIBLE);
            tvEmbaBuy.setVisibility(View.GONE);
        } else {
            webView.setVisibility(View.VISIBLE);
            NestedScrollView.setVisibility(View.GONE);
            tvEmbaBuy.setVisibility(View.VISIBLE);
        }
    }

    private void initRecycler() {
        LinearLayoutManager mannager = new LinearLayoutManager(this);
        recycleRecommendAlbum.setLayoutManager(mannager);
        recycleRecommendAlbum.setNestedScrollingEnabled(false);//禁止滑动
        recycleRecommendAlbum.setHasFixedSize(true);
        recycleRecommendAlbum.setFocusable(false);//禁止获取焦点
        embaOnlineAdapter = new EMBAOnlineAdapter(R.layout.item_course_album, null);
        recycleRecommendAlbum.setAdapter(embaOnlineAdapter);
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (embaOnlineBean == null) {
                    ToastUtils.shortShow("网络异常,无法分享");
                    return;
                }
                ShareBean shareBean = new ShareBean();
                shareBean.setPosterImgs(embaOnlineBean.getPosterImgs());
                shareBean.setSlogans(embaOnlineBean.getSlogans());
                shareBean.setInviteFrontName("邀请您加入");
                shareBean.setCourseName("在线EMBA");
                shareBean.setInviteBehindName("，一起学习");
                shareBean.setShareImg(embaOnlineBean.getShareImg());
                shareBean.setShareTitle(embaOnlineBean.getShareTitle());
                shareBean.setShareSubtitle(embaOnlineBean.getShareSubtitle());
                shareBean.setShareUrl(embaOnlineBean.getShareUrl());
                Intent intent = new Intent(EMBAOnlineActivity.this, ShareActivity.class);
                intent.putExtra(Constant.data, shareBean);
                startActivity(intent);

            }
        });

        embaOnlineAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = embaOnlineAdapter.getData().get(position).getId();
                String type = embaOnlineAdapter.getData().get(position).getType();
                ARouter.getInstance().build("/android/video")
                        .withString(Constant.type, type)
                        .withString(Constant.id, id)
                        .navigation();
            }
        });

        tvEmbaBuy.setOnClickListener(this);
        tvRenew.setOnClickListener(this);
        tvRecommendAlbum.setOnClickListener(this);


    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerEMBAOlineComponent.builder().appComponent(appComponent)
                .eMBAOnlineModule(new EMBAOnlineModule(this))
                .build().inject(this);
    }


    @Override
    public void getEmbaDataSuces(EMBAOnlineBean embaOnlineBean) {
        this.embaOnlineBean = embaOnlineBean;
        if (embaOnlineBean.isIsBuy()) {
            webView.setVisibility(View.GONE);
            NestedScrollView.setVisibility(View.VISIBLE);
            tvEmbaBuy.setVisibility(View.GONE);

            if (embaOnlineBean.isIsExpired()) {
                //已过期
                tvExpiredTime.setText("到期时间：" + embaOnlineBean.getEmbaDueTime() + " 已过期");
                tvExpiredTime.setTextColor(getResources().getColor(R.color.colorBlack79808B));
            } else {
                //未过期
                tvExpiredTime.setText("到期时间：" + embaOnlineBean.getEmbaDueTime());
                tvExpiredTime.setTextColor(getResources().getColor(R.color.colorYellowECBF86));
            }
        } else {
            webView.setVisibility(View.VISIBLE);
            NestedScrollView.setVisibility(View.GONE);
            tvExpiredTime.setText("");
            tvEmbaBuy.setVisibility(View.VISIBLE);

        }

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.icon_place_holder_64_64)
                .error(R.drawable.icon_place_holder_64_64)
                .fallback(R.drawable.icon_place_holder_64_64);
        Glide.with(this).load(embaOnlineBean.getGrowingCourse().getDisplayPic()).apply(requestOptions).into(ivCourseIcon);
        tvCourseTitle.setText(embaOnlineBean.getGrowingCourse().getTitle());
        tvCourseContent.setText(embaOnlineBean.getGrowingCourse().getSubtitle());
        embaOnlineAdapter.setNewData(embaOnlineBean.getCourseTopics());
        tvEmbaBuy.setText("立即加入 ¥" + embaOnlineBean.getPrice() + "/年");

        String descriptionUrl = embaOnlineBean.getDescriptionUrl();
        webView.loadUrl(descriptionUrl);
    }

    /**
     * @param date 分享返回结果
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EventBusEvent date) {
        if (date != null) {
            switch (date.getCode()) {
                case EventBusUtils.EventCode.LOGIN_BUY_REFRESH:
                    mPresenter.getEmbaData();
                    break;
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_emba_buy:
            case R.id.tv_renew:

                if (UserCacheUtil.isLogin()) {
                    if (embaOnlineBean == null) {
                        ToastUtils.shortShow("网络异常,无法购买");
                        return;
                    }
                    CommodityBean commodityBean = new CommodityBean();
                    commodityBean.setImg(R.drawable.icon_emba_logo);
                    commodityBean.setPrice(embaOnlineBean.getPrice());
                    commodityBean.setTitle("在线EMBA");
                    commodityBean.setTopicId("0");
                    commodityBean.setType("2");
                    Intent intent = new Intent(this, ChosePayWayActivity.class);
                    intent.putExtra(Constant.commodity, commodityBean);
                    startActivity(intent);
                    SharePreferencesUtils.saveProductName("你已成功购买在线EMBA");
                } else {
                    goLogin();
                }
                break;
            case R.id.tv_recommend_album:
                ARouter.getInstance().build("/android/allCourse").navigation();
                break;
        }
    }


}
