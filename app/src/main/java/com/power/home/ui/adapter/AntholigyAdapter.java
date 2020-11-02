package com.power.home.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.CoursePlayerBean;

import java.util.List;

/**
 * Created by zhp on 2019-11-20
 */
public class AntholigyAdapter extends BaseQuickAdapter<CoursePlayerBean.CoursesBean, BaseViewHolder> {
    private int selectPosition;

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    public AntholigyAdapter(int layoutResId, @Nullable List<CoursePlayerBean.CoursesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoursePlayerBean.CoursesBean item) {
        helper.setText(R.id.tv_course_sort,item.getSort());
        helper.setText(R.id.tv_course_title,item.getTitle());
        if (item.isCanPlay()){
            helper.setVisible(R.id.iv_course_lock,false);
        }else {
            helper.setVisible(R.id.iv_course_lock,true);
        }


        if (selectPosition == helper.getLayoutPosition()){
            helper.setTextColor(R.id.tv_course_sort,mContext.getResources().getColor(R.color.colorAccent));
            helper.setTextColor(R.id.tv_course_title,mContext.getResources().getColor(R.color.colorAccent));
        }else {
            helper.setTextColor(R.id.tv_course_sort,mContext.getResources().getColor(R.color.colorWhite));
            helper.setTextColor(R.id.tv_course_title,mContext.getResources().getColor(R.color.colorWhite));

        }
    }

}
