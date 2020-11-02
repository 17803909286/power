package com.power.home.ui.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.data.bean.StudyPlanBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerStudyPlanComponent;
import com.power.home.di.module.StudyPlanModule;
import com.power.home.presenter.StudyPlanPresenter;
import com.power.home.presenter.contract.StudyPlanContract;
import com.power.home.ui.adapter.MainFinishedCourseAdapter;
import com.power.home.ui.adapter.MainTodayPlanAdapter;

import butterknife.BindView;

/**
 * Created by ZHP on 2020/7/16 0016.
 */
@SuppressLint("Registered")
@Route(path = "/android/studyPlan")
public class MainStudyPlanActivity extends BaseActivity<StudyPlanPresenter> implements StudyPlanContract.View {
    @BindView(R.id.rl_today_plan_finished)
    RelativeLayout rlTodayPlanFinished;
    @BindView(R.id.rl_today_plan_unfinished)
    RelativeLayout rlTodayPlanUnfinished;
    @BindView(R.id.recycle_today_plan)
    RecyclerView recycleTodayPlan;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.recycle_study_finish)
    RecyclerView recycleStudyFinish;
    @BindView(R.id.tv_finish_empty)
    TextView tvFinishEmpty;
    @BindView(R.id.swipeRefreshLayout_study)
    SwipeRefreshLayout swipeRefreshLayoutStudy;
    @BindView(R.id.left_layout)
    RelativeLayout leftLayout;
    @BindView(R.id.ll_content)
    RelativeLayout llContent;

    private MainTodayPlanAdapter todayPlanAdapter;
    private MainFinishedCourseAdapter finishedCourseAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_study;
    }

    @Override
    public void getExtras() {

    }

    @Override
    public boolean init() {
        initRecycleView();
        mPresenter.getStudyPlan();
        return true;
    }

    private void initRecycleView() {

        LinearLayoutManager todayPlanManager = new LinearLayoutManager(this);
        recycleTodayPlan.setLayoutManager(todayPlanManager);
        recycleTodayPlan.setNestedScrollingEnabled(false);//禁止滑动
        recycleTodayPlan.setHasFixedSize(true);
        recycleTodayPlan.setFocusable(false);//禁止获取焦点
        todayPlanAdapter = new MainTodayPlanAdapter(R.layout.item_study_today_plan, null);
        recycleTodayPlan.setAdapter(todayPlanAdapter);

        LinearLayoutManager courseFinishManager = new LinearLayoutManager(this);
        recycleStudyFinish.setLayoutManager(courseFinishManager);
        recycleStudyFinish.setNestedScrollingEnabled(false);//禁止滑动
        recycleStudyFinish.setHasFixedSize(true);
        recycleStudyFinish.setFocusable(false);//禁止获取焦点
        finishedCourseAdapter = new MainFinishedCourseAdapter(R.layout.item_study_finish, null);
        recycleStudyFinish.setAdapter(finishedCourseAdapter);

    }

    @Override
    public void setListener() {

        todayPlanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = todayPlanAdapter.getData().get(position).getCourseId();
                ARouter.getInstance().build("/android/video")
                        .withString(Constant.type, "2")
                        .withString(Constant.id, id)
                        .navigation();
            }
        });
        finishedCourseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = finishedCourseAdapter.getData().get(position).getCourseId();
                ARouter.getInstance().build("/android/video")
                        .withString(Constant.type, "2")
                        .withString(Constant.id, id)
                        .navigation();
            }
        });

        swipeRefreshLayoutStudy.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getStudyPlan();
            }
        });
        leftLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerStudyPlanComponent.builder().appComponent(appComponent)
                .studyPlanModule(new StudyPlanModule(this))
                .build().inject(this);
    }

    @Override
    public void getStudyPlanSuces(StudyPlanBean studyPlanBean) {
        swipeRefreshLayoutStudy.setRefreshing(false);
        if (studyPlanBean.getTodayPlan().size() > 0) {
            rlTodayPlanFinished.setVisibility(View.GONE);
            rlTodayPlanUnfinished.setVisibility(View.VISIBLE);
            recycleTodayPlan.setVisibility(View.VISIBLE);
        } else {
            rlTodayPlanFinished.setVisibility(View.VISIBLE);
            rlTodayPlanUnfinished.setVisibility(View.GONE);
            recycleTodayPlan.setVisibility(View.GONE);
        }

        if (studyPlanBean.getFinishedCourse().size() > 0) {
            tvFinish.setVisibility(View.VISIBLE);
            tvFinishEmpty.setVisibility(View.GONE);
            recycleStudyFinish.setVisibility(View.VISIBLE);
        } else {
            tvFinish.setVisibility(View.VISIBLE);
            tvFinishEmpty.setVisibility(View.VISIBLE);
            recycleStudyFinish.setVisibility(View.GONE);
        }
        todayPlanAdapter.setNewData(studyPlanBean.getTodayPlan());
        finishedCourseAdapter.setNewData(studyPlanBean.getFinishedCourse());
    }

}
