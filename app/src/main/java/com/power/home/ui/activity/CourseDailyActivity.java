package com.power.home.ui.activity;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.CourseFreeDailyBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerCourseDailyComponent;
import com.power.home.di.module.CourseDailyModule;
import com.power.home.presenter.CourseDailyPresenter;
import com.power.home.presenter.contract.CourseDailyContract;
import com.power.home.ui.adapter.CourseDailyAdapter;
import com.power.home.ui.widget.MyTitleBar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;

/**
 * @author xwl
 * @description:商道五分钟
 * @date :2020/2/20
 */
@Route(path = "/android/businessCourse")
public class CourseDailyActivity extends BaseActivity<CourseDailyPresenter> implements CourseDailyContract.View {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.recycle_course)
    RecyclerView recycleCourse;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int pageSize = 10;
    private int pageNumber = 0;
    private CourseDailyAdapter courseDailyAdapter;
    private String title;


    @Override
    public int setLayout() {
        return R.layout.activity_course_free_daily;
    }

    @Override
    public void getExtras() {
        title = getIntent().getStringExtra(Constant.title);

    }

    @Override
    public boolean init() {
        initView();
        getData();


        return false;
    }

    private void getData() {
        mPresenter.getDailyCourse(pageNumber, pageSize);
    }

    private void initView() {
        titleBar.setTitle(title);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtil.getContext());
        recycleCourse.setLayoutManager(linearLayoutManager);
        courseDailyAdapter = new CourseDailyAdapter(R.layout.item_course_daily, null);
        recycleCourse.setAdapter(courseDailyAdapter);

    }

    @Override
    public void setListener() {
        backListener(titleBar);

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNumber = 0;
                getData();
            }
        });


        courseDailyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = courseDailyAdapter.getData().get(position).getId();
                String type = courseDailyAdapter.getData().get(position).getType();
                ARouter.getInstance().build("/android/video")
                        .withString(Constant.type, type)
                        .withString(Constant.id, id)
                        .navigation();
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerCourseDailyComponent.builder().appComponent(appComponent)
                .courseDailyModule(new CourseDailyModule(this))
                .build().inject(this);
    }

    private void setData(List<CourseFreeDailyBean> courseFreeDailyBeans) {
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
        if (courseFreeDailyBeans.size() == 0) {
            if (pageNumber == 0) {
                courseDailyAdapter.setNewData(courseFreeDailyBeans);
                courseDailyAdapter.setEmptyView(getEmptyView(R.drawable.icon_place_holder_64_96, "没有相关内容"));
            }
        } else {
            if (pageNumber == 0) {
                courseDailyAdapter.setNewData(courseFreeDailyBeans);
            } else {
                courseDailyAdapter.addData(courseFreeDailyBeans);
            }
            pageNumber++;
        }
        if (courseFreeDailyBeans.size() < pageSize) {
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

    @Override
    public void getDailyCourseSuccess(List<CourseFreeDailyBean> courseFreeDailyBeans) {
        setData(courseFreeDailyBeans);
    }

}
