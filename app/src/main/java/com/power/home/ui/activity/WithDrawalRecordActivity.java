package com.power.home.ui.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.power.home.R;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.WithDrawalRecordBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerWithdrawlRecordComponent;
import com.power.home.di.module.WithdrawlRecordModule;
import com.power.home.presenter.WithdrawlRecordPresenter;
import com.power.home.presenter.contract.WithdrawlRecordContract;
import com.power.home.ui.adapter.WithDrawalRecordAdapter;
import com.power.home.ui.widget.MyTitleBar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 */
@Route(path = "/android/withdraw_list")
public class WithDrawalRecordActivity extends BaseActivity<WithdrawlRecordPresenter> implements WithdrawlRecordContract.View {

    @BindView(R.id.titleBar)
    MyTitleBar titleBar;

    @BindView(R.id.recycle_withdrawal)
    RecyclerView recycleWithdrawal;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    private WithDrawalRecordAdapter withDrawalRecordAdapter;

    private int page = 0;
    private int size = 10;

    @Override
    public int setLayout() {
        return R.layout.activity_withdrawal_record;
    }

    @Override
    public void getExtras() {
    }

    @Override
    public boolean init() {
        initRecycle();
        mPresenter.getWithdrawRecord(page, size);
        return false;
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getWithdrawRecord(page, size);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                mPresenter.getWithdrawRecord(page, size);
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

        DaggerWithdrawlRecordComponent.builder().appComponent(appComponent)
                .withdrawlRecordModule(new WithdrawlRecordModule(this))
                .build().inject(this);
    }

    private void initRecycle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtil.getContext(), LinearLayoutManager.VERTICAL, false);
        recycleWithdrawal.setLayoutManager(linearLayoutManager);
        withDrawalRecordAdapter = new WithDrawalRecordAdapter(R.layout.item_withdrawal_record, null);
        recycleWithdrawal.setAdapter(withDrawalRecordAdapter);
    }

    @Override
    public void getWithdrawRecordSuccess(List<WithDrawalRecordBean> withDrawalRecordBeans) {
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
        if (withDrawalRecordBeans.size() == 0) {
            if (page == 0) {
                withDrawalRecordAdapter.setNewData(withDrawalRecordBeans);
                withDrawalRecordAdapter.setEmptyView(getEmptyView(R.drawable.icon_empty_withdrawl, "暂无提现记录"));
            }
        } else {
            if (page == 0) {
                withDrawalRecordAdapter.setNewData(withDrawalRecordBeans);
            } else {
                withDrawalRecordAdapter.addData(withDrawalRecordBeans);
            }
            page++;
        }
        if (withDrawalRecordBeans.size() < size) {
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


}
