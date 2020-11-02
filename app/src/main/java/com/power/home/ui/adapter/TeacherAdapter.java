package com.power.home.ui.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.ChampGuideBean;

import java.util.List;

/**
 * Created by zhp on 2019-11-20
 */
public class TeacherAdapter extends BaseQuickAdapter<ChampGuideBean, BaseViewHolder> {
    public TeacherAdapter(int layoutResId, @Nullable List<ChampGuideBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChampGuideBean item) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.icon_teacher_holder)
                .error(R.drawable.icon_teacher_holder)
                .fallback(R.drawable.icon_teacher_holder);
        Glide.with(mContext).load(item.getTeacherAvatar()).apply(requestOptions).into((ImageView) helper.getView(R.id.civ_teacher));
        helper.setText(R.id.tv_teacher_name, item.getTeacherName());
        helper.setText(R.id.tv_teacher_info, "共" + item.getCourseTopicTotalSize() + "节专栏课题");
    }

}
