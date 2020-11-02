package com.power.home.ui.activity;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.BindEventBus;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.CommodityBean;
import com.power.home.data.bean.OrderBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerOrderComponent;
import com.power.home.di.module.OrderModule;
import com.power.home.presenter.OrderPresenter;
import com.power.home.presenter.contract.OrderContract;
import com.power.home.ui.adapter.OrderAdapter;
import com.power.home.ui.widget.MyTitleBar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 * 已购专区
 */

@Route(path = "/android/bought")
@BindEventBus
public class OrderActivity extends BaseActivity<OrderPresenter> implements OrderContract.View {
    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.recycle_order)
    RecyclerView recycleOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private OrderAdapter orderAdapter;

    private int page = 0;
    private int size = 10;

    @Override
    public int setLayout() {
        return R.layout.activity_order;
    }

    @Override
    public void getExtras() {

    }

    @Override
    public boolean init() {
        backListener(titleBar);
        initRecycle();
        mPresenter.getOrderMasterRecord(page, size);
        return false;
    }

    @Override
    public void setListener() {

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getOrderMasterRecord(page, size);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                mPresenter.getOrderMasterRecord(page, size);
            }
        });
        orderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                // 跳转播放专辑
                ARouter.getInstance().build("/android/video")
                        .withString(Constant.type, "1")//专辑
                        .withString(Constant.id, orderAdapter.getData().get(position).getTowTopicId())
                        .navigation();
            }
        });
        orderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.iv_item_order_pay) {
                    //续费

                    CommodityBean commodityBean = new CommodityBean();
                    commodityBean.setPrice(orderAdapter.getData().get(position).getTopicPrice());

                    commodityBean.setTopicId(orderAdapter.getData().get(position).getTowTopicId());
                    commodityBean.setSubTitle(orderAdapter.getData().get(position).getSubtitle());
                    commodityBean.setType("1");

                    commodityBean.setImg(orderAdapter.getData().get(position).getDisplayPic());
                    commodityBean.setTitle(orderAdapter.getData().get(position).getTitle());
                    SharePreferencesUtils.saveProductName("你已成功购买《" + orderAdapter.getData().get(position).getTitle() + "》课程，赶紧去学习吧！");


                    Intent intent = new Intent(UIUtil.getContext(), ChosePayWayActivity.class);
                    intent.putExtra(Constant.commodity, commodityBean);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

        DaggerOrderComponent.builder().appComponent(appComponent)
                .orderModule(new OrderModule(this))
                .build().inject(this);
    }

    private void initRecycle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtil.getContext(), LinearLayoutManager.VERTICAL, false);
        recycleOrder.setLayoutManager(linearLayoutManager);
        orderAdapter = new OrderAdapter(R.layout.item_order_list, null);
        recycleOrder.setAdapter(orderAdapter);
    }

    @Override
    public void getOrderMasterRecord(List<OrderBean> orderBeans) {
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
        if (orderBeans.size() == 0) {
            if (page == 0) {
                orderAdapter.setNewData(orderBeans);
                orderAdapter.setEmptyView(getEmptyView(R.drawable.icon_empty_paid, "暂无已购课程"));
            }
        } else {
            if (page == 0) {
                orderAdapter.setNewData(orderBeans);
            } else {
                orderAdapter.addData(orderBeans);
            }
            page++;
        }
        if (orderBeans.size() < size) {
            refreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
    }

    /**
     * @param date 支付返回结果
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EventBusEvent date) {
        if (date != null) {
            switch (date.getCode()) {
                case EventBusUtils.EventCode.LOGIN_BUY_REFRESH:
                    page = 0;
                    mPresenter.getOrderMasterRecord(page, size);
                    break;
            }
        }
    }
}