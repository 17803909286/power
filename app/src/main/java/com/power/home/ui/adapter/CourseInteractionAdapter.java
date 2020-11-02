package com.power.home.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.CourseInteractionBean;

import java.util.List;

public class CourseInteractionAdapter extends BaseQuickAdapter<CourseInteractionBean, BaseViewHolder> {
    public CourseInteractionAdapter(int layoutResId, @Nullable List<CourseInteractionBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseInteractionBean item) {
        helper.setText(R.id.tv_name,item.getNickName());
        helper.setText(R.id.tv_date,item.getDate());
        helper.setText(R.id.tv_comment,item.getComment());

    }
}
