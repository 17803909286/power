package com.power.home.ui.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.power.home.R;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.VipRecordBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerVipRecordComponent;
import com.power.home.di.module.VipRecordModule;
import com.power.home.presenter.VipRecordPresenter;
import com.power.home.presenter.contract.VipRecordContract;
import com.power.home.ui.adapter.VipRecordAdapter;
import com.power.home.ui.widget.MyTitleBar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08 动力营名额明细
 */
@Route(path = "/android/vip_list")
public class VipRecordActivity extends BaseActivity<VipRecordPresenter> implements VipRecordContract.View {

    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.recycle_vip_record)
    RecyclerView recycleVipRecord;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int page = 0;
    private int size = 10;
    private VipRecordAdapter vipRecordAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_vip_record;
    }

    @Override
    public void getExtras() {
    }

    @Override
    public boolean init() {
        initRecycle();
        mPresenter.getVipRecord(page, size);
        return false;
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getVipRecord(page, size);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                mPresenter.getVipRecord(page, size);
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerVipRecordComponent.builder().appComponent(appComponent)
                .vipRecordModule(new VipRecordModule(this))
                .build().inject(this);
    }

    private void initRecycle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtil.getContext(), LinearLayoutManager.VERTICAL, false);
        recycleVipRecord.setLayoutManager(linearLayoutManager);
        vipRecordAdapter = new VipRecordAdapter(R.layout.item_vip_record, null);
        recycleVipRecord.setAdapter(vipRecordAdapter);
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
    }


    @Override
    public void getVipRecordSuccess(List<VipRecordBean> vipRecordBeans) {
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
        if (vipRecordBeans.size() == 0) {
            if (page == 0) {
                vipRecordAdapter.setNewData(vipRecordBeans);
                vipRecordAdapter.setEmptyView(getEmptyView(R.drawable.icon_empty_money, "暂无动力营名额明细"));
            }
        } else {
            if (page == 0) {
                vipRecordAdapter.setNewData(vipRecordBeans);
            } else {
                vipRecordAdapter.addData(vipRecordBeans);
            }
            page++;
        }
        if (vipRecordBeans.size() < size) {
            refreshLayout.finishLoadMoreWithNoMoreData();
        }
    }
}
