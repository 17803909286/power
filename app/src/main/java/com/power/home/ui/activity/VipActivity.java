package com.power.home.ui.activity;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.power.home.R;
import com.power.home.common.bus.BindEventBus;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.OrderUitils;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.ToastUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.common.util.UserCacheUtil;
import com.power.home.data.bean.CommodityBean;
import com.power.home.data.bean.PayChannelBean;
import com.power.home.data.bean.VipBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerVipComponent;
import com.power.home.di.module.ChoseChannelModule;
import com.power.home.di.module.VipModule;
import com.power.home.presenter.ChoseChannelPresenter;
import com.power.home.presenter.VipPresenter;
import com.power.home.presenter.contract.ChoseChannelContract;
import com.power.home.presenter.contract.VipContract;
import com.power.home.ui.adapter.ChosePayWayAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ZHP on 2020/4/28 0028.
 */
@BindEventBus
@Route(path = "/android/vipCenter")
public class VipActivity extends BaseActivity<VipPresenter> implements View.OnClickListener, VipContract.View, ChoseChannelContract.View {

    @BindView(R.id.left_layout)
    RelativeLayout leftLayout;
    @BindView(R.id.tv_pay_vip)
    TextView tvPayVip;
    @BindView(R.id.tv_vip_date)
    TextView tvVipDate;
    @BindView(R.id.tv_vip_price)
    TextView tvVipPrice;
    @BindView(R.id.recycle_pay_way)
    RecyclerView recyclePayWay;
    @BindView(R.id.webView_vip_show)
    WebView webViewVipShow;
    @BindView(R.id.swipeRefreshLayout_vip)
    SwipeRefreshLayout swipeRefreshLayoutVip;

    private ChosePayWayAdapter chosePayWayAdapter;
    private VipBean mVipBean;

    @Inject
    ChoseChannelPresenter mChoseChannelPresenter;

    @Override
    public int setLayout() {
        return R.layout.activity_vip;
    }

    @Override
    public void getExtras() {

    }

    @Override
    public boolean init() {
        initRecycle();
        mPresenter.getVipInfo();
        mChoseChannelPresenter.getPayChannel("Android");
        swipeRefreshLayoutVip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getVipInfo();
            }
        });
        return false;
    }

    private void initRecycle() {
        WebSettings webSettings = webViewVipShow.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setLoadWithOverviewMode(true);//适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setUserAgentString(webSettings.getUserAgentString() + "android");
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        String cacheDirPath = "/data/data/" + UIUtil.getContext().getPackageName() + "/databases/";
        //      String cacheDirPath = getCacheDir().getAbsolutePath()+Constant.APP_DB_DIRNAME;
        Log.i("WebActivity", "cacheDirPath=" + cacheDirPath);
        //设置  Application Caches 缓存目录
        webSettings.setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能

        webViewVipShow.setBackgroundColor(UIUtil.getColor(R.color.colorWhiteEEEEEE));
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webViewVipShow.getSettings().setDatabasePath(cacheDirPath);
        }
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recyclePayWay.setLayoutManager(layout);
        chosePayWayAdapter = new ChosePayWayAdapter(R.layout.item_chose_pay_way, null);
        recyclePayWay.setAdapter(chosePayWayAdapter);
    }

    @Override
    public void setListener() {
        leftLayout.setOnClickListener(this);
        tvPayVip.setOnClickListener(this);
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerVipComponent.builder().appComponent(appComponent)
                .vipModule(new VipModule(this))
                .choseChannelModule(new ChoseChannelModule(this))
                .build().inject(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_layout:
                onBackPressed();
                break;
            case R.id.tv_pay_vip:
                if (UserCacheUtil.isLogin()) {
                    if (mVipBean == null) {
                        ToastUtils.shortShow("网络异常,无法购买");
                        return;
                    }
                    CommodityBean commodityBean = new CommodityBean();
                    commodityBean.setPrice(mVipBean.getGrowingPrice());
                    commodityBean.setTopicId("0");
                    commodityBean.setType("2");
                    commodityBean.setImg(mVipBean.getGrowingCoverImg());
                    commodityBean.setTitle("动力营");
                    SharePreferencesUtils.saveProductName(getString(R.string.pay_vip_tip));

                    if (chosePayWayAdapter.getData().size() == 0) {
                        ToastUtils.shortShow("请选择支付方式");
                        return;
                    }
                    new OrderUitils().CreateOrder(commodityBean.getPrice(),
                            chosePayWayAdapter.getData().get(chosePayWayAdapter.getChoseId()).getId(),
                            chosePayWayAdapter.getData().get(chosePayWayAdapter.getChoseId()).getPayType(), commodityBean.getTopicId(), SharePreferencesUtils.getUserId());
                    break;
                } else {
                    goLogin();
                }
                break;
        }
    }

    @Override
    public void getVipInfoSuccess(VipBean vipBean) {
        swipeRefreshLayoutVip.setRefreshing(false);
        mVipBean = vipBean;
        webViewVipShow.loadUrl(vipBean.getGrowingVipRightsIntroduce());
        tvVipPrice.setText(vipBean.getGrowingPrice());
        if (UserCacheUtil.isLogin()) {
            if (vipBean.isGrowingVip()) {
                tvVipDate.setText("动力营: " + vipBean.getGrowingExpiredTime() + "到期");
                tvPayVip.setText("续费");
            } else {
                tvVipDate.setText("介绍：开通动力营可免费观看全部会员课程一年");
                tvPayVip.setText("立即支付");
            }
        } else {
            tvVipDate.setText("介绍：开通动力营可免费观看全部会员课程一年");
            tvPayVip.setText("立即支付");
        }
    }

    /**
     * @param date 购买或者登陆之后刷新数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EventBusEvent date) {
        if (date != null) {
            switch (date.getCode()) {
                case EventBusUtils.EventCode.LOGIN_BUY_REFRESH:
                    mPresenter.getVipInfo();
                    break;
            }
        }
    }

    @Override
    public void getPayChanneleSuces(List<PayChannelBean> payChannelBeans) {
        chosePayWayAdapter.setNewData(payChannelBeans);
    }
}
