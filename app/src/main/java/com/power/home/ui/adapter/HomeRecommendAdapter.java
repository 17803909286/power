package com.power.home.ui.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.FreeExperienceBean;
import com.power.home.data.bean.MainHomeBean;
import com.power.home.ui.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by ZHP on 2020/5/25 0025.
 */
public class HomeRecommendAdapter extends BaseQuickAdapter<FreeExperienceBean.ContentsBean, BaseViewHolder> {
    public HomeRecommendAdapter(int layoutResId, @Nullable List<FreeExperienceBean.ContentsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FreeExperienceBean.ContentsBean item) {
        helper.setText(R.id.tv_course_title, item.getTitle());
        helper.setText(R.id.tv_course_content, item.getSubtitle());
        if (item.isContinueUpdating()){
            helper.setText(R.id.tv_course_size, "更新至"+item.getCourseSize()+"节/共" + item.getCourseTotalSize() + "节");
        }else {
            helper.setText(R.id.tv_course_size, "已完结/共" + item.getCourseTotalSize() + "节");
        }
        helper.setText(R.id.tv_play_amount, item.getDisplayCount() > 9999 ? StringUtil.getPlayCount(item.getDisplayCount()) + "万" : item.getDisplayCount() + "");
        ImageView courseIcon = helper.getView(R.id.iv_course_icon);

        RequestOptions requestOptions = RequestOptions.fitCenterTransform().transform(new GlideRoundTransform(4))
                .placeholder(R.drawable.icon_place_holder_160_90_round)
                .error(R.drawable.icon_place_holder_160_90_round)
                .fallback(R.drawable.icon_place_holder_160_90_round);
        Glide.with(UIUtil.getContext()).load(item.getDisplayPic()).apply(requestOptions).into(courseIcon);
    }
}
