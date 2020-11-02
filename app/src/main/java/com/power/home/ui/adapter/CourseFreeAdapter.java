package com.power.home.ui.adapter;

import androidx.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.common.util.FontUtil;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.TimeUtil;
import com.power.home.data.bean.CourseFreeDailyBean;
import com.power.home.ui.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by XWL on 2020/3/4.
 * Description:
 */
 public class CourseFreeAdapter extends BaseQuickAdapter<CourseFreeDailyBean, BaseViewHolder> {
    public CourseFreeAdapter(int layoutResId, @Nullable List<CourseFreeDailyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseFreeDailyBean item) {
        helper.setText(R.id.tv_course_title,item.getTitle());
        helper.setText(R.id.tv_course_content,item.getSubtitle());
        helper.setText(R.id.tv_play_amount,item.getPlayCounts()>9999? StringUtil.getPlayCount(item.getPlayCounts())+"万":item.getPlayCounts()+"");
        ImageView courseIcon = helper.getView(R.id.iv_course_icon);
        //设置图片圆角角度
        RequestOptions requestOptions = RequestOptions.fitCenterTransform().transform(new GlideRoundTransform(4))
                .placeholder(R.drawable.icon_place_holder_92_138_round)
                .error(R.drawable.icon_place_holder_92_138_round)
                .fallback(R.drawable.icon_place_holder_92_138_round);
        Glide.with(mContext).load(item.getDisplayPic()).apply(requestOptions).into(courseIcon);
        if (item.getCourseTime()>3600){
            helper.setText(R.id.tv_course_time, TimeUtil.dateToString(item.getCourseTime()*1000,TimeUtil.dateFormat_hour2));
        }else {
            helper.setText(R.id.tv_course_time, TimeUtil.dateToString(item.getCourseTime()*1000,TimeUtil.dateFormat_minutes));
        }
        if (item.isFree()){
            helper.setGone(R.id.tv_course_label,true);
            helper.setGone(R.id.iv_course_lock,false);
            helper.setText(R.id.tv_course_label,"免费");
        }else {
            if (item.isBuy()){
                helper.setGone(R.id.tv_course_label,true);
                helper.setGone(R.id.iv_course_lock,false);
                helper.setText(R.id.tv_course_label,"已解锁");
            }else {
                helper.setGone(R.id.tv_course_label,false);
                helper.setGone(R.id.iv_course_lock,true);
            }
        }


    }
}
