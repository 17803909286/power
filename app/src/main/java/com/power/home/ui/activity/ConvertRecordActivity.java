package com.power.home.ui.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.power.home.R;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.ConvertRecordBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerConvertRecordComponent;
import com.power.home.di.module.ConvertRecordModule;
import com.power.home.presenter.ConvertRecordPresenter;
import com.power.home.presenter.contract.ConvertRecordContract;
import com.power.home.ui.adapter.ConvertRecordAdapter;
import com.power.home.ui.widget.MyTitleBar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 */
public class ConvertRecordActivity extends BaseActivity<ConvertRecordPresenter> implements ConvertRecordContract.View {

    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.recycle_convert)
    RecyclerView recycleConvert;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private ConvertRecordAdapter convertRecordAdapter;

    private int page = 0;
    private int size = 10;

    @Override
    public int setLayout() {
        return R.layout.activity_convert_record;
    }

    @Override
    public void getExtras() {
    }

    @Override
    public boolean init() {
        initRecycle();
        mPresenter.getCodeMarketRecord(page, size);
        return false;
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getCodeMarketRecord(page, size);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                mPresenter.getCodeMarketRecord(page, size);
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

        DaggerConvertRecordComponent.builder().appComponent(appComponent)
                .convertRecordModule(new ConvertRecordModule(this))
                .build().inject(this);
    }

    private void initRecycle() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtil.getContext(), LinearLayoutManager.VERTICAL, false);
        recycleConvert.setLayoutManager(linearLayoutManager);
        convertRecordAdapter = new ConvertRecordAdapter(R.layout.item_convert_record, null);
        recycleConvert.setAdapter(convertRecordAdapter);
    }

    @Override
    public void getCodeMarketRecordSuccess(List<ConvertRecordBean> convertRecordBeans) {
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
        if (convertRecordBeans.size() == 0) {
            if (page == 0) {
                convertRecordAdapter.setNewData(convertRecordBeans);
                convertRecordAdapter.setEmptyView(getEmptyView(R.drawable.icon_empty_convert, "暂无兑换记录"));
            }
        } else {
            if (page == 0) {
                convertRecordAdapter.setNewData(convertRecordBeans);
            } else {
                convertRecordAdapter.addData(convertRecordBeans);
            }
            page++;
        }
        if (convertRecordBeans.size() < size) {
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
