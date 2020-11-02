package com.power.home.ui.activity;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.CourseOfflineBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerCourseOfflineComponent;
import com.power.home.di.module.CourseOfflineModule;
import com.power.home.presenter.CourseOfflinePresenter;
import com.power.home.presenter.contract.CourseOfflineContract;
import com.power.home.ui.adapter.CourseAdapter;
import com.power.home.ui.widget.MyTitleBar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 */

@Route(path = "/android/offlineActivity")
public class CourseOfflineActivity extends BaseActivity<CourseOfflinePresenter> implements CourseOfflineContract.View {

    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.recycle_course)
    RecyclerView recycleCourse;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private CourseAdapter courseAdapter;

    private int pageNumber = 0;
    private int pageSize = 10;

    @Override
    public int setLayout() {
        return R.layout.activity_course_offline;
    }

    @Override
    public void getExtras() {

    }

    @Override
    public boolean init() {
        initRecycle();
        mPresenter.getOfflineCourse(pageNumber, pageSize);
        return false;
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getOfflineCourse(pageNumber, pageSize);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNumber = 0;
                mPresenter.getOfflineCourse(pageNumber, pageSize);
            }
        });
        courseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(UIUtil.getContext(), CourseDetailsActivity.class);
                intent.putExtra(Constant.id, courseAdapter.getData().get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerCourseOfflineComponent.builder().appComponent(appComponent)
                .courseOfflineModule(new CourseOfflineModule(this))
                .build().inject(this);
    }

    private void initRecycle() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtil.getContext(), LinearLayoutManager.VERTICAL, false);
        recycleCourse.setLayoutManager(linearLayoutManager);
        courseAdapter = new CourseAdapter(R.layout.item_course_list, null);
        recycleCourse.setAdapter(courseAdapter);
    }

    @Override
    public void getOfflineCourseSucess(List<CourseOfflineBean> courseOfflineBeans) {
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
        if (courseOfflineBeans.size() == 0) {
            if (pageNumber == 0) {
                courseAdapter.setNewData(courseOfflineBeans);
                courseAdapter.setEmptyView(getEmptyView(R.drawable.icon_empty_course, "暂无线下课程"));
            }
        } else {
            if (pageNumber == 0) {
                courseAdapter.setNewData(courseOfflineBeans);
            } else {
                courseAdapter.addData(courseOfflineBeans);
            }
            pageNumber++;
        }

        if (courseOfflineBeans.size() < pageSize) {
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
