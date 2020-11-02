package com.power.home.ui.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.ChampDetailBean;
import com.power.home.data.bean.CourseTopicsBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerTeacherDetailComponent;
import com.power.home.di.module.TeacherDetailModule;
import com.power.home.presenter.TeacherDetailPresenter;
import com.power.home.presenter.contract.TeacherDetailContract;
import com.power.home.ui.adapter.AllCourseChildAdapter;
import com.power.home.ui.widget.CircleImageView;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ZHP on 2020/4/28 0028.
 */
@Route(path = "/android/champ")
public class TeacherDetailActivity extends BaseActivity<TeacherDetailPresenter> implements View.OnClickListener, TeacherDetailContract.View {

    @BindView(R.id.left_layout)
    RelativeLayout leftLayout;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_teacher_detail_info)
    TextView tvTeacherDetailInfo;
    @BindView(R.id.civ_head)
    CircleImageView civHead;
    @BindView(R.id.recycle_teacher_child)
    RecyclerView recycleTeacherChild;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_teacher_detail_name)
    TextView tvTeacherDetailName;

    private int page = 0;
    private int size = 10;
    private String id;
    private AllCourseChildAdapter allCourseChildAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_teacher_detail;
    }

    @Override
    public void getExtras() {
        id = getIntent().getStringExtra(Constant.id);
    }

    @Override
    public boolean init() {
        paddingTop(rlTitle);
        initRecycle();
        mPresenter.getChampDetails(id, page, size);
        return true;
    }

    private void initRecycle() {
        //全部课程
        LinearLayoutManager layoutManager = new LinearLayoutManager(UIUtil.getContext());
        recycleTeacherChild.setLayoutManager(layoutManager);
        recycleTeacherChild.setNestedScrollingEnabled(false);//禁止滑动
        recycleTeacherChild.setHasFixedSize(true);
        recycleTeacherChild.setFocusable(false);//禁止获取焦点
        allCourseChildAdapter = new AllCourseChildAdapter(R.layout.item_all_course, null);
        recycleTeacherChild.setAdapter(allCourseChildAdapter);
    }

    @Override
    public void setListener() {
        leftLayout.setOnClickListener(this);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getChampDetails(id, page, size);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                mPresenter.getChampDetails(id, page, size);
            }
        });

        allCourseChildAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter a, View view, int position) {
                String id = allCourseChildAdapter.getData().get(position).getId();
                String type = allCourseChildAdapter.getData().get(position).getType();
                ARouter.getInstance().build("/android/video")
                        .withString(Constant.type, type)
                        .withString(Constant.id, id)
                        .navigation();
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerTeacherDetailComponent.builder().appComponent(appComponent)
                .teacherDetailModule(new TeacherDetailModule(this))
                .build().inject(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_layout:
                onBackPressed();
                break;
        }
    }

    @Override
    public void getChampDetailsSuccess(ChampDetailBean champDetailBean) {
        tvTeacherDetailName.setText(champDetailBean.getTeacherName());
        tvTeacherDetailInfo.setText(champDetailBean.getTeacherSummary());
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.icon_teacher_holder)
                .error(R.drawable.icon_teacher_holder)
                .fallback(R.drawable.icon_teacher_holder);
        Glide.with(UIUtil.getContext()).load(champDetailBean.getTeacherAvatar()).apply(requestOptions).into(civHead);
        List<CourseTopicsBean> courseTopicDtos = champDetailBean.getCourseTopicDtos();
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
        if (courseTopicDtos.size() == 0) {
            if (page == 0) {
                allCourseChildAdapter.setNewData(courseTopicDtos);
                allCourseChildAdapter.setEmptyView(getEmptyView(R.drawable.icon_empty_money, "暂无动力营名额明细"));
            }
        } else {
            if (page == 0) {
                allCourseChildAdapter.setNewData(courseTopicDtos);
            } else {
                allCourseChildAdapter.addData(courseTopicDtos);
            }
            page++;
        }
        if (courseTopicDtos.size() < size) {
            refreshLayout.finishLoadMoreWithNoMoreData();
        }
    }
}
