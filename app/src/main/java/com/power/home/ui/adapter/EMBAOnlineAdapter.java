package com.power.home.ui.adapter;

import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.common.util.StringUtil;
import com.power.home.data.bean.EMBAOnlineBean;

import java.util.List;

/**
 * Created by XWL on 2020/3/4.
 * Description:
 */
public class EMBAOnlineAdapter extends BaseQuickAdapter<EMBAOnlineBean.CourseTopicsBean, BaseViewHolder> {


    public EMBAOnlineAdapter(int layoutResId, @Nullable List<EMBAOnlineBean.CourseTopicsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EMBAOnlineBean.CourseTopicsBean item) {
        helper.setText(R.id.tv_course_title,item.getTitle());
        helper.setText(R.id.tv_course_content,item.getSubtitle());
        helper.setText(R.id.tv_course_price,Double.parseDouble(item.getPrice())>0?"¥"+item.getPrice():"免费");
        helper.setText(R.id.tv_play_amount,item.getDisplayCount()>9999? StringUtil.getPlayCount(item.getDisplayCount())+"万":item.getDisplayCount()+"");
        ImageView courseIcon = helper.getView(R.id.iv_course_icon);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.icon_place_holder_64_96)
                .error(R.drawable.icon_place_holder_64_96)
                .fallback(R.drawable.icon_place_holder_64_96);
        Glide.with(mContext).load(item.getDisplayPic()).apply(requestOptions).into(courseIcon);
        if (item.isBuy()) {
            helper.setGone(R.id.tv_course_price,false);
        }else {
            helper.setGone(R.id.tv_course_price,true);
        }

    }

    @Override
    public int getItemCount() {
        return getData().size()>3?3:getData().size();
    }
}
