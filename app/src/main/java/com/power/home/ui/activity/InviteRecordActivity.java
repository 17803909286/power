package com.power.home.ui.activity;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.power.home.App;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.InviteRecordBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerInviteRecordComponent;
import com.power.home.di.module.InviteRecordModule;
import com.power.home.presenter.InviteRecordPresenter;
import com.power.home.presenter.contract.InviteRecordContract;
import com.power.home.ui.adapter.InviteRecordAdapter;
import com.power.home.ui.widget.MyTitleBar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08 邀请记录
 */

@Route(path = "/android/invitedRecord")
public class InviteRecordActivity extends BaseActivity<InviteRecordPresenter> implements InviteRecordContract.View {

    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.recycle_team)
    RecyclerView recycleTeam;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private InviteRecordAdapter inviteRecordAdapter;

    private int page = 0;
    private int size = 10;
    private String queryType = "NON_AGENT";

    @Override
    public int setLayout() {
        return R.layout.activity_invite_record;
    }

    @Override
    public void getExtras() {
        String type = getIntent().getStringExtra(Constant.type);
        if (StringUtil.isNotNullString(type)) {
            queryType = type;
            switch (queryType) {
                case "AMBASSADOR":
                    titleBar.setTitle("我的推广大使");
                    break;
                case "SCHOOLMASTER":
                    titleBar.setTitle("我的联盟校长");
                    break;
                case "VIP":
                    titleBar.setTitle("我的会员");
                    break;

            }
        }
    }

    @Override
    public boolean init() {
        initRecycle();
        mPresenter.getInviteRecord(page, size, queryType);
        return false;
    }

    private void initRecycle() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtil.getContext(), LinearLayoutManager.VERTICAL, false);
        recycleTeam.setLayoutManager(linearLayoutManager);
        inviteRecordAdapter = new InviteRecordAdapter(R.layout.item_team, null);
        recycleTeam.setAdapter(inviteRecordAdapter);

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getInviteRecord(page, size, queryType);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                mPresenter.getInviteRecord(page, size, queryType);
            }
        });
        inviteRecordAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                InviteRecordBean team = (InviteRecordBean) adapter.getItem(position);
                String phone = team.getPhone();
                switch (view.getId()) {
                    case R.id.iv_item_team_phone:
                        UIUtil.goCall(phone, InviteRecordActivity.this);
                        break;
                    case R.id.iv_item_team_info:
                        UIUtil.goSMS(phone, InviteRecordActivity.this);
                        break;
                }
            }
        });
    }

    @Override
    public void setListener() {
        backListener(titleBar);
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerInviteRecordComponent.builder().appComponent(App.getApplication().getAppComponent())
                .inviteRecordModule(new InviteRecordModule(this))
                .build().inject(this);
    }

    @Override
    public void getInviteRecordSuccess(List<InviteRecordBean> inviteRecordBeans) {
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
        if (inviteRecordBeans.size() == 0) {
            if (page == 0) {
                inviteRecordAdapter.setNewData(inviteRecordBeans);
                inviteRecordAdapter.setEmptyView(getEmptyView(R.drawable.icon_empty_team, "暂无相关用户，快去邀请吧"));
            }
        } else {
            if (page == 0) {
                inviteRecordAdapter.setNewData(inviteRecordBeans);
            } else {
                inviteRecordAdapter.addData(inviteRecordBeans);
            }
            page++;
        }
        if (inviteRecordBeans.size() < size) {
            refreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void showError(String msg) {
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
    }
}
