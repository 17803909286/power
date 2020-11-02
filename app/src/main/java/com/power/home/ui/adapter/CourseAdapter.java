package com.power.home.ui.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.CourseOfflineBean;

import java.util.List;

/**
 * Created by zhp on 2019-11-20
 */
public class CourseAdapter extends BaseQuickAdapter<CourseOfflineBean, BaseViewHolder> {
    public CourseAdapter(int layoutResId, @Nullable List<CourseOfflineBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseOfflineBean item) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.icon_place_holder_343_114)
                .error(R.drawable.icon_place_holder_343_114)
                .fallback(R.drawable.icon_place_holder_343_114);
        Glide.with(mContext).load(item.getDisplayPic()).apply(requestOptions).into((ImageView) helper.getView(R.id.iv_course_icon));
        helper.setText(R.id.tv_course_title, item.getName());
        helper.setText(R.id.tv_course_title_tip, item.getDescription());
        helper.setText(R.id.tv_course_date, item.getCreateTime());
    }

}
