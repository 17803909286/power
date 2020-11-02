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
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.CourseFreeDailyBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerCourseFreeComponent;
import com.power.home.di.module.CourseFreeModule;
import com.power.home.presenter.CourseFreePresenter;
import com.power.home.presenter.contract.CourseFreeContract;
import com.power.home.ui.adapter.CourseFreeAdapter;
import com.power.home.ui.widget.MyTitleBar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;

/**
 * @author xwl
 * @description:免费体验
 * @date :2020/2/20
 */
@Route(path = "/android/freeCourse")
public class CourseFreeActivity extends BaseActivity<CourseFreePresenter> implements CourseFreeContract.View {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.recycle_course)
    RecyclerView recycleCourse;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int pageSize = 10;
    private int pageNumber = 0;
    private CourseFreeAdapter courseFreeAdapter;
    private String title;
    private String type;


    @Override
    public int setLayout() {
        return R.layout.activity_course_free_daily;
    }

    @Override
    public void getExtras() {
        title = getIntent().getStringExtra(Constant.title);
        //首页：types：COMMON,COMMON_DAILY 学霸课堂：types：K12,K12_CHAMP
        String from = getIntent().getStringExtra(Constant.from);
        if (StringUtil.isNotNullString(from)) {
            type = "K12,K12_CHAMP";
        } else {
            type = "COMMON,COMMON_DAILY";
        }
    }

    @Override
    public boolean init() {
        initView();
        getData();


        return false;
    }

    private void getData() {
        mPresenter.getFreeCourse(pageNumber, pageSize,type);
    }

    private void initView() {
        titleBar.setTitle(title);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtil.getContext());
        recycleCourse.setLayoutManager(linearLayoutManager);
        courseFreeAdapter = new CourseFreeAdapter(R.layout.item_course_album, null);
        recycleCourse.setAdapter(courseFreeAdapter);

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
        courseFreeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = courseFreeAdapter.getData().get(position).getId();
                String type = courseFreeAdapter.getData().get(position).getType();
                ARouter.getInstance().build("/android/video")
                        .withString(Constant.type, type)
                        .withString(Constant.id, id)
                        .navigation();
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerCourseFreeComponent.builder().appComponent(appComponent)
                .courseFreeModule(new CourseFreeModule(this))
                .build().inject(this);
    }


    @Override
    public void getFreeCourseSuccess(List<CourseFreeDailyBean> courseFreeDailyBeans) {

        setData(courseFreeDailyBeans);
    }


    private void setData(List<CourseFreeDailyBean> courseFreeDailyBeans) {
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
        if (courseFreeDailyBeans.size() == 0) {
            if (pageNumber == 0) {
                courseFreeAdapter.setNewData(courseFreeDailyBeans);
                courseFreeAdapter.setEmptyView(getEmptyView(R.drawable.icon_place_holder_64_96, "没有相关内容"));
            }
        } else {
            if (pageNumber == 0) {
                courseFreeAdapter.setNewData(courseFreeDailyBeans);
            } else {
                courseFreeAdapter.addData(courseFreeDailyBeans);
            }
            pageNumber++;
        }
        if (courseFreeDailyBeans.size() < pageSize) {
            refreshLayout.finishLoadMoreWithNoMoreData();
            UIUtil.showToastSafe("没有更多数据");
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
