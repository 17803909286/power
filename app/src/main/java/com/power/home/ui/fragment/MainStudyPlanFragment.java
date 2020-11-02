package com.power.home.ui.fragment;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.StatusBarUtil;
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
 * Created by zhangpeng on 2017/11/23.
 */

public class MainStudyPlanFragment extends BaseFragment<StudyPlanPresenter> implements StudyPlanContract.View {


    @BindView(R.id.rl_today_plan_finished)
    RelativeLayout rlTodayPlanFinished;
    @BindView(R.id.rl_today_plan_unfinished)
    RelativeLayout rlTodayPlanUnfinished;
    @BindView(R.id.recycle_today_plan)
    RecyclerView recycleTodayPlan;
    @BindView(R.id.recycle_study_finish)
    RecyclerView recycleStudyFinish;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.ll_content)
    RelativeLayout llContent;
    @BindView(R.id.tv_finish_empty)
    TextView tvFinishEmpty;
    @BindView(R.id.swipeRefreshLayout_study)
    SwipeRefreshLayout swipeRefreshLayoutStudy;
    private MainTodayPlanAdapter todayPlanAdapter;
    private MainFinishedCourseAdapter finishedCourseAdapter;


    @Override
    public int setLayout() {
        StatusBarUtil.transparencyBar2(getActivity());
        StatusBarUtil.setStatusTextColor(false, getActivity());
        return R.layout.fragment_study;
    }


    @Override
    protected void getExtras() {

    }

    @Override
    public void init() {

        initRecycleView();
    }

    private void initRecycleView() {

        LinearLayoutManager todayPlanManager = new LinearLayoutManager(getActivity());
        recycleTodayPlan.setLayoutManager(todayPlanManager);
        recycleTodayPlan.setNestedScrollingEnabled(false);//禁止滑动
        recycleTodayPlan.setHasFixedSize(true);
        recycleTodayPlan.setFocusable(false);//禁止获取焦点
        todayPlanAdapter = new MainTodayPlanAdapter(R.layout.item_study_today_plan, null);
        recycleTodayPlan.setAdapter(todayPlanAdapter);

        LinearLayoutManager courseFinishManager = new LinearLayoutManager(getActivity());
        recycleStudyFinish.setLayoutManager(courseFinishManager);
        recycleStudyFinish.setNestedScrollingEnabled(false);//禁止滑动
        recycleStudyFinish.setHasFixedSize(true);
        recycleStudyFinish.setFocusable(false);//禁止获取焦点
        finishedCourseAdapter = new MainFinishedCourseAdapter(R.layout.item_study_finish, null);
        recycleStudyFinish.setAdapter(finishedCourseAdapter);

    }

    @Override
    protected void setListener() {
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
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerStudyPlanComponent.builder().appComponent(appComponent)
                .studyPlanModule(new StudyPlanModule(this))
                .build().inject(this);

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            setStatusBarColor(getActivity(), getActivity().getResources().getColor(R.color.colorBlue0D7EF9));
            StatusBarUtil.setStatusTextColor(false, getActivity());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getStudyPlan();
    }

    /**
     * 设置状态栏颜色
     *
     * @param activity
     */
    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            //状态栏改变颜色。
            window.setStatusBarColor(color);
        }
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