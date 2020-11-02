package com.power.home.ui.fragment;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.power.home.R;
import com.power.home.data.bean.CourseInteractionBean;
import com.power.home.di.component.AppComponent;
import com.power.home.ui.adapter.CourseInteractionAdapter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by xwl on 2019/11/12
 * 课程详情-互动
 */
public class CourseDetailsInteractionFragment extends BaseFragment {

    @BindView(R.id.recycle_interaction)
    RecyclerView recycleInteraction;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private CourseInteractionAdapter interactionAdapter;
    private ArrayList<CourseInteractionBean> courseInteractionBeans;

    @Override
    public int setLayout() {
        return R.layout.fragment_course_details_interaction;
    }

    @Override
    protected void getExtras() {

    }

    @Override
    public void init() {
        initRecycler();
    }

    private void initRecycler() {
        courseInteractionBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CourseInteractionBean courseInteractionBean = new CourseInteractionBean();
            courseInteractionBean.setNickName("木目");
            courseInteractionBean.setDate("09-05 21:00");
            courseInteractionBean.setComment("讲得真心不错");
            courseInteractionBeans.add(courseInteractionBean);
        }
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recycleInteraction.setLayoutManager(manager);
        interactionAdapter = new CourseInteractionAdapter(R.layout.item_course_interaction, courseInteractionBeans);
        recycleInteraction.setAdapter(interactionAdapter);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }


}
