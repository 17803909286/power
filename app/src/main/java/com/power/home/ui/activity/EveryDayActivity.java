package com.power.home.ui.activity;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.EveryDayBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerEveryDayComponent;
import com.power.home.di.module.EveryDayModule;
import com.power.home.presenter.EveryDayPresenter;
import com.power.home.presenter.contract.EveryDayContract;
import com.power.home.ui.adapter.EveryDayAdapter;
import com.power.home.ui.widget.MyTitleBar;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08 每日活动
 */

@Route(path = "/android/dailyActivity")
public class EveryDayActivity extends BaseActivity<EveryDayPresenter> implements EveryDayContract.View {

    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.recycle_everyDay)
    RecyclerView recycleEveryDay;
    @BindView(R.id.swipeRefreshLayout_everyday)
    SwipeRefreshLayout swipeRefreshLayoutEveryday;
    private EveryDayAdapter everyDayAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_everyday;
    }

    @Override
    public void getExtras() {

    }

    @Override
    public boolean init() {
        backListener(titleBar);
        mPresenter.getEveryDayInfo();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtil.getContext(), LinearLayoutManager.VERTICAL, false);
        recycleEveryDay.setLayoutManager(linearLayoutManager);
        everyDayAdapter = new EveryDayAdapter(R.layout.item_everyday, null);
        recycleEveryDay.setAdapter(everyDayAdapter);
        swipeRefreshLayoutEveryday.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getEveryDayInfo();
            }
        });
        return true;
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        everyDayAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TextView textView = (TextView) view;
                String tip = textView.getText().toString();
                switch (tip) {
                    case "去分享":
                    case "去邀请":
                        ARouter.getInstance().build("/android/invitedFriend")
                                .navigation();
                        break;
                    case "去领取":
                        switch (everyDayAdapter.getData().get(position).getActivityClassify()) {
                            case "OFFLINE":
                                ARouter.getInstance().build("/android/everyDayResult")
                                        .withString(Constant.type, "OFFLINE")
                                        .withString(Constant.id,everyDayAdapter.getData().get(position).getId())
                                        .navigation();
                                break;
                            case "COURSE":
                                ARouter.getInstance().build("/android/everyDayResult")
                                        .withString(Constant.type, "COURSE")
                                        .withString(Constant.id,everyDayAdapter.getData().get(position).getId())
                                        .navigation();
                                break;
                        }
                        break;
                    case "去赠送":
                        //仅VIP
                        ARouter.getInstance().build("/android/everyDayResult")
                                .withString(Constant.type, "VIP")
                                .withString(Constant.id,everyDayAdapter.getData().get(position).getId())
                                .navigation();
                        break;
                }
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

        DaggerEveryDayComponent.builder().appComponent(appComponent)
                .everyDayModule(new EveryDayModule(this))
                .build().inject(this);
    }

    @Override
    public void getEveryDayInfoSuccess(List<EveryDayBean> everyDayBeans) {
        swipeRefreshLayoutEveryday.setRefreshing(false);
        if (null != everyDayBeans && everyDayBeans.size() > 0) {
            everyDayAdapter.setNewData(everyDayBeans);
        }
    }
}

