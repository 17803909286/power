package com.power.home.ui.fragment;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.power.home.R;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.data.bean.CoursePlayerBean;
import com.power.home.di.component.AppComponent;
import com.power.home.ui.adapter.CourseCatalogueAdapter;

import butterknife.BindView;

/**
 * Created by xwl on 2019/11/12
 * 课程详情-目录
 */

public class CourseDetailsCatalogueFragment extends BaseFragment {

    @BindView(R.id.recycle_catalogue)
    RecyclerView recycleCatalogue;

    public CourseCatalogueAdapter catalogueAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_course_details_catalogue;
    }

    @Override
    protected void getExtras() {

    }

    @Override
    public void init() {
        initRecycler();
    }

    private void initRecycler() {


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recycleCatalogue.setLayoutManager(manager);
        catalogueAdapter = new CourseCatalogueAdapter(R.layout.item_course_catalogue, null);
        recycleCatalogue.setAdapter(catalogueAdapter);
    }

    @Override
    protected void setListener() {
        catalogueAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                catalogueAdapter.setSelectPosition(position);

                CoursePlayerBean.CoursesBean coursesBean = catalogueAdapter.getData().get(position);
                coursesBean.setPosition(position);

                EventBusUtils.sendEvent(new EventBusEvent<CoursePlayerBean.CoursesBean>(EventBusUtils.EventCode.VIDEO_BEAN, coursesBean));
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }


}
