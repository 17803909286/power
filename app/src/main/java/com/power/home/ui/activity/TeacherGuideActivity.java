package com.power.home.ui.activity;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.ChampGuideBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerTeacherComponent;
import com.power.home.di.module.TeacherModule;
import com.power.home.presenter.TeacherPresenter;
import com.power.home.presenter.contract.TeacherContract;
import com.power.home.ui.adapter.CourseAdapter;
import com.power.home.ui.adapter.TeacherAdapter;
import com.power.home.ui.widget.MyTitleBar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 */

@Route(path = "/android/teacherGuide")
public class TeacherGuideActivity extends BaseActivity<TeacherPresenter> implements TeacherContract.View {

    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.recycle_teacher)
    RecyclerView recycleTeacher;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String title;
    private TeacherAdapter teacherAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_teacher_guide;
    }

    @Override
    public void getExtras() {
        title = getIntent().getStringExtra(Constant.title);
    }

    @Override
    public boolean init() {
        initRecycle();
        mPresenter.getChamp();
        return false;
    }

    @Override
    public void setListener() {
        titleBar.setTitle(title);
        backListener(titleBar);
        teacherAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(UIUtil.getContext(), TeacherDetailActivity.class);
                intent.putExtra(Constant.id, teacherAdapter.getData().get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerTeacherComponent.builder().appComponent(appComponent)
                .teacherModule(new TeacherModule(this))
                .build().inject(this);
    }

    private void initRecycle() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtil.getContext(), LinearLayoutManager.VERTICAL, false);
        recycleTeacher.setLayoutManager(linearLayoutManager);
        teacherAdapter = new TeacherAdapter(R.layout.item_teacher_list, null);
        recycleTeacher.setAdapter(teacherAdapter);
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
    public void getChampSuccess(List<ChampGuideBean> champGuideBeans) {
        teacherAdapter.setNewData(champGuideBeans);
    }
}
