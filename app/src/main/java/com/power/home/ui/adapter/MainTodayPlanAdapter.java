package com.power.home.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.common.util.TimeUtil;
import com.power.home.data.bean.StudyPlanBean;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by XWL on 2020/3/16.
 * Description:
 */
public class MainTodayPlanAdapter extends BaseQuickAdapter<StudyPlanBean.TodayPlanBean, BaseViewHolder> {
    public MainTodayPlanAdapter(int layoutResId, @Nullable List<StudyPlanBean.TodayPlanBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudyPlanBean.TodayPlanBean item) {
        helper.setText(R.id.tv_topicTitle,item.getTopicTitle());
        helper.setText(R.id.tv_courseTitle,item.getCourseTitle());
        if (item.getCourseTime()>3600){
            helper.setText(R.id.tv_courseTime, TimeUtil.dateToString(item.getCourseTime()*1000,TimeUtil.dateFormat_hour2));
        }else {
            helper.setText(R.id.tv_courseTime, TimeUtil.dateToString(item.getCourseTime()*1000,TimeUtil.dateFormat_minutes));
        }
        if (item.isIsFree()){
            helper.setGone(R.id.tv_isFree,true);
        }else {
            helper.setGone(R.id.tv_isFree,false);
        }

        double d = (double) item.getTopProcess() / (double) item.getCourseTime();
        DecimalFormat df = new DecimalFormat("0%");
        String format = df.format(d);
        if (d>0.01){
            helper.setText(R.id.tv_already_learning,"已学"+format);
        }else {
            helper.setText(R.id.tv_already_learning,"");
        }

    }
}
