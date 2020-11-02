package com.power.home.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.common.util.TimeUtil;
import com.power.home.data.bean.StudyPlanBean;

import java.util.List;

/**
 * Created by XWL on 2020/3/16.
 * Description:
 */
public class MainFinishedCourseAdapter extends BaseQuickAdapter<StudyPlanBean.FinishedCourseBean, BaseViewHolder> {
    public MainFinishedCourseAdapter(int layoutResId, @Nullable List<StudyPlanBean.FinishedCourseBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudyPlanBean.FinishedCourseBean item) {
        helper.setText(R.id.tv_courseTitle,item.getCourseTitle());
        if (item.getCourseTime()>3600){
            helper.setText(R.id.tv_courseTime, TimeUtil.dateToString(item.getCourseTime()*1000,TimeUtil.dateFormat_hour2));
        }else {
            helper.setText(R.id.tv_courseTime, TimeUtil.dateToString(item.getCourseTime()*1000,TimeUtil.dateFormat_minutes));
        }
    }
}
