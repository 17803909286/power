package com.power.home.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.power.home.App;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.MoneyInRecordBean;
import com.power.home.di.component.DaggerMoneyComponent;
import com.power.home.di.module.MoneyModule;
import com.power.home.presenter.MoneyPresenter;
import com.power.home.presenter.contract.MoneyContract;
import com.power.home.ui.adapter.MoneyInRecordAdapter;
import com.power.home.ui.widget.BaseLazyFragment;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhp on 2020-03-14
 */
public class MoneyInFragment extends BaseLazyFragment implements MoneyContract.View {

    @BindView(R.id.recycle_money)
    RecyclerView recycleMoney;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private MoneyInRecordAdapter moneyInRecordAdapter;

    private int page = 0;
    private int size = 10;
    private String incomeType;
    @Inject
    MoneyPresenter mPresenter;

    @Override
    protected View onCreateView(LayoutInflater inflater, Bundle savedInstanceState) {
        incomeType = getArguments().getString(Constant.type);
        View view = inflater.inflate(R.layout.fragment_money_in, null);
        mUnbinder = ButterKnife.bind(this, view);
        initRecycle();
        DaggerMoneyComponent.builder().appComponent(App.getApplication().getAppComponent())
                .moneyModule(new MoneyModule(this))
                .build().inject(this);
        mPresenter.getMoneyIn(page, size, incomeType);
        return view;
    }

    /**
     * 在界面可见再进行加载数据
     */
    @Override
    protected void onLazyLoad() {
        super.onLazyLoad();
    }

    private void initRecycle() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtil.getContext(), LinearLayoutManager.VERTICAL, false);
        recycleMoney.setLayoutManager(linearLayoutManager);
        moneyInRecordAdapter = new MoneyInRecordAdapter(R.layout.item_money_in_record, null);
        recycleMoney.setAdapter(moneyInRecordAdapter);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getMoneyIn(page, size, incomeType);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                mPresenter.getMoneyIn(page, size, incomeType);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void dismissLoading() {

    }

    public View getEmptyView(int id, String tip) {
        View view = RelativeLayout.inflate(UIUtil.getContext(), R.layout.empty_layout, null);
        TextView textView = view.findViewById(R.id.tv_dataEmpty);
        textView.setText(tip);
        ImageView imageView = view.findViewById(R.id.img_dataEmpty);
        imageView.setImageDrawable(UIUtil.getDrawable(id));
        return view;
    }

    @Override
    public void getMoneyInSuccess(List<MoneyInRecordBean> moneyInRecordBeans) {
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
        if (moneyInRecordBeans.size() == 0) {
            if (page == 0) {
                moneyInRecordAdapter.setNewData(moneyInRecordBeans);
                moneyInRecordAdapter.setEmptyView(getEmptyView(R.drawable.icon_empty_money, "暂无收入明细"));
            }
        } else {
            if (page == 0) {
                moneyInRecordAdapter.setNewData(moneyInRecordBeans);
            } else {
                moneyInRecordAdapter.addData(moneyInRecordBeans);
            }
            page++;
        }
        if (moneyInRecordBeans.size() < size) {
            refreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

}
