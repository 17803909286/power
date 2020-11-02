package com.power.home.ui.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.common.util.FontUtil;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.TimeUtil;
import com.power.home.data.bean.CoursePlayerBean;

import java.text.DecimalFormat;
import java.util.List;

public class CourseCatalogueAdapter extends BaseQuickAdapter<CoursePlayerBean.CoursesBean, BaseViewHolder> {
    private int selectPosition;




    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    public CourseCatalogueAdapter(int layoutResId, @Nullable List<CoursePlayerBean.CoursesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoursePlayerBean.CoursesBean item) {
        helper.setText(R.id.tv_play_amount, item.getPlayCounts() > 9999 ? StringUtil.getPlayCount(item.getPlayCounts()) + "万" : item.getPlayCounts() + "");
        helper.setText(R.id.tv_title,item.getTitle());
//        helper.setText(R.id.tv_duration,item.getTime());
        if (item.isLastStudy()){
            helper.setVisible(R.id.tv_learn_tag,true);
        }else {
            helper.setVisible(R.id.tv_learn_tag,false);
        }
//        if (item.isBuy()){
//            helper.setGone(R.id.tv_free,false);
//            helper.setGone(R.id.iv_course_lock,false);
//
//        }else {
//            if (item.isFree()){
//                helper.setGone(R.id.tv_free,true);
//                helper.setGone(R.id.iv_course_lock,false);
//            }else {
//                helper.setGone(R.id.tv_free,false);
//                helper.setGone(R.id.iv_course_lock,true);
//            }
//        }

        if (item.isFree()){
            helper.setText(R.id.tv_free,"免费");
            helper.setGone(R.id.tv_free,true);
            helper.setGone(R.id.iv_course_lock,false);

        }else {
            if (item.isBuy()){
                helper.setText(R.id.tv_free,"已解锁");
                helper.setGone(R.id.tv_free,true);
                helper.setGone(R.id.iv_course_lock,false);
            }else {
                helper.setGone(R.id.tv_free,false);
                helper.setGone(R.id.iv_course_lock,true);
            }
        }

        if (selectPosition == helper.getLayoutPosition()){
            helper.setText(R.id.tv_already_learning,"学习中…");
            helper.setVisible(R.id.tv_already_learning,true);
            helper.setTextColor(R.id.tv_already_learning,mContext.getResources().getColor(R.color.colorBlack1A1F28));
            helper.setTextColor(R.id.tv_title,mContext.getResources().getColor(R.color.colorBlue0D7EF9));
            helper.setGone(R.id.iv_learning,true);
            helper.setGone(R.id.tv_position,false);
        }else {
            helper.setTextColor(R.id.tv_already_learning,mContext.getResources().getColor(R.color.colorBlack79808B));
            if (item.isFinish()){
                helper.setVisible(R.id.tv_already_learning,true);
                helper.setText(R.id.tv_already_learning,"已学完");
            }else {
                double d = (double) item.getTopProgress() / (double) item.getCourseTime();
                DecimalFormat df = new DecimalFormat("0%");
                String format = df.format(d);
                if (d>0.01){
                    helper.setVisible(R.id.tv_already_learning,true);
                    helper.setText(R.id.tv_already_learning,"已学"+format);
                }else {
                    helper.setVisible(R.id.tv_already_learning,false);
                    helper.setText(R.id.tv_already_learning,"");
                }

            }

            helper.setTextColor(R.id.tv_title,mContext.getResources().getColor(R.color.colorBlack1A1F28));
            helper.setGone(R.id.iv_learning,false);
            helper.setGone(R.id.tv_position,true);
            helper.setText(R.id.tv_position,item.getSort());
            FontUtil.setFont((TextView) helper.getView(R.id.tv_position));
        }

        if (item.getCourseTime()>3600){
            helper.setText(R.id.tv_duration, TimeUtil.dateToString(item.getCourseTime()*1000,TimeUtil.dateFormat_hour2));
        }else {
            helper.setText(R.id.tv_duration, TimeUtil.dateToString(item.getCourseTime()*1000,TimeUtil.dateFormat_minutes));
        }
    }
}
