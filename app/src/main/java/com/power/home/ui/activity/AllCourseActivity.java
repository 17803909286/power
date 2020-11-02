package com.power.home.ui.activity;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.StringUtil;
import com.power.home.data.bean.AllCourseBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerAllCourseComponent;
import com.power.home.di.module.AllCourseModule;
import com.power.home.presenter.AllCoursePresenter;
import com.power.home.presenter.contract.AllCourseContract;
import com.power.home.ui.adapter.AllCourseChildAdapter;
import com.power.home.ui.adapter.AllCourseClassifyAdapter;
import com.power.home.ui.widget.MyTitleBar;

import java.util.List;

import butterknife.BindView;

/**
 * Created by XWL on 2020/2/13.
 * Description: 全部课程
 */

@Route(path = "/android/allCourse")
public class AllCourseActivity extends BaseActivity<AllCoursePresenter> implements AllCourseContract.View {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.recycle_classify)
    RecyclerView recycleClassify;
    @BindView(R.id.recycle_course)
    RecyclerView recycleCourse;
    private AllCourseClassifyAdapter classifyAdapter;
    private AllCourseChildAdapter allCourseChildAdapter;

    private List<AllCourseBean> allCourseBean;
    private String type;
    private String name;

    @Override
    public int setLayout() {
        return R.layout.activity_all_course;
    }

    @Override
    public void getExtras() {
        //首页：COMMON，学霸课堂：K12
        String from = getIntent().getStringExtra(Constant.from);
        name = getIntent().getStringExtra(Constant.data);
        if (StringUtil.isNotNullString(from)) {
            type = "K12";
        } else {
            type = "COMMON";
        }
    }

    @Override
    public boolean init() {
        initView();
        initRecycler();
        mPresenter.findAllCourse(type);
        return false;
    }


    private void initView() {

    }

    private void initRecycler() {
        //左侧
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycleClassify.setLayoutManager(manager);
        classifyAdapter = new AllCourseClassifyAdapter(R.layout.item_all_course_classify, null);
        recycleClassify.setAdapter(classifyAdapter);
        //右侧
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleCourse.setLayoutManager(layoutManager);
        allCourseChildAdapter = new AllCourseChildAdapter(R.layout.item_all_course, null);
        recycleCourse.setAdapter(allCourseChildAdapter);
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        classifyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                classifyAdapter.setPosition(position);
                if (allCourseBean != null) {
                    allCourseChildAdapter.setNewData(allCourseBean.get(classifyAdapter.getPosition()).getCourseTopics());
                }
            }
        });
        allCourseChildAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = allCourseChildAdapter.getData().get(position).getId();
                String type = allCourseChildAdapter.getData().get(position).getType();
                ARouter.getInstance().build("/android/video")
                        .withString(Constant.type, type)
                        .withString(Constant.id, id)
                        .navigation();
            }
        });
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/android/search")
                        .navigation();
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerAllCourseComponent.builder().appComponent(appComponent)
                .allCourseModule(new AllCourseModule(this))
                .build().inject(this);
    }

    @Override
    public void findAllCourseSuces(List<AllCourseBean> allCourseBean) {
        this.allCourseBean = allCourseBean;

        classifyAdapter.setNewData(allCourseBean);
        classifyAdapter.setPosition(getPosition(allCourseBean));
        allCourseChildAdapter.setNewData(allCourseBean.get(classifyAdapter.getPosition()).getCourseTopics());
    }

    private int getPosition(List<AllCourseBean> allCourseBean) {
        int position = 0;
        if (StringUtil.isNotNullString(name)) {
            for (int i = 0; i < allCourseBean.size(); i++) {
                if (allCourseBean.get(i).getModuleName().equals(name)) {
                    position = i;
                    break;
                }
            }
        }
        return position;
    }


}
