package com.power.home.ui.activity;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.power.home.R;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.StudyRecordsBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerStudyRecordComponent;
import com.power.home.di.module.StudyRecordModule;
import com.power.home.presenter.StudyRecordPresenter;
import com.power.home.presenter.contract.StudyRecordContract;
import com.power.home.ui.adapter.StudyRecordsAdapter;
import com.power.home.ui.widget.MyTitleBar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 */

@Route(path = "/android/studyRecord")
public class StudyRecordsActivity extends BaseActivity<StudyRecordPresenter> implements StudyRecordContract.View {
    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.recycle_study_records)
    RecyclerView recycleStudyRecords;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private StudyRecordsAdapter studyRecordsAdapter;


    @Override
    public int setLayout() {
        return R.layout.activity_study_records;
    }

    @Override
    public void getExtras() {

    }

    @Override
    public boolean init() {
        initRecycle();
        return false;
    }

    @Override
    public void setListener() {
        backListener(titleBar);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getStudyProgress();
            }
        });

    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

        DaggerStudyRecordComponent.builder().appComponent(appComponent)
                .studyRecordModule(new StudyRecordModule(this))
                .build().inject(this);
    }

    private void initRecycle() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtil.getContext(), LinearLayoutManager.VERTICAL, false);
        recycleStudyRecords.setLayoutManager(linearLayoutManager);
        studyRecordsAdapter = new StudyRecordsAdapter(R.layout.item_study_records, null);
        recycleStudyRecords.setAdapter(studyRecordsAdapter);
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void getStudyProgressSuccess(List<StudyRecordsBean> studyRecordsBeans) {


        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
        if (studyRecordsBeans.size() == 0) {
            studyRecordsAdapter.setEmptyView(getEmptyView(R.drawable.icon_empty_study, "暂未开启学习之旅"));
        } else {
            StudyRecordsBean start = new StudyRecordsBean();
            start.setName("你的学习之旅从这里开始");
            studyRecordsBeans.add(start);
            studyRecordsAdapter.setNewData(studyRecordsBeans);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getStudyProgress();
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
    }
}
