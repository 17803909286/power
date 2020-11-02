package com.power.home.ui.adapter;

import androidx.annotation.Nullable;

import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.AllCourseBean;
import com.power.home.data.bean.CourseTopicsBean;
import com.power.home.ui.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by XWL on 2020/3/4.
 * Description:
 */
public class AllCourseChildAdapter extends BaseQuickAdapter<CourseTopicsBean, BaseViewHolder> {
    public AllCourseChildAdapter(int layoutResId, @Nullable List<CourseTopicsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseTopicsBean item) {
        helper.setText(R.id.tv_course_title, item.getTitle());
        helper.setText(R.id.tv_course_content, item.getSubtitle());
        if (item.isContinueUpdating()){
            helper.setText(R.id.tv_course_size, "更新至"+item.getCourseSize()+"节/共" + item.getCourseTotalSize() + "节");
        }else {
            helper.setText(R.id.tv_course_size, "已完结/共" + item.getCourseTotalSize() + "节");
        }
        helper.setText(R.id.tv_course_price, "¥" + item.getPrice() + "/年");
        TextView original_price = helper.getView(R.id.tv_course_original_price);
        original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        original_price.setText("¥" + item.getOriginalPrice());
        helper.setText(R.id.tv_play_amount, item.getDisplayCount() > 9999 ? StringUtil.getPlayCount(item.getDisplayCount()) + "万" : item.getDisplayCount() + "");
        ImageView courseIcon = helper.getView(R.id.iv_course_icon);
        RequestOptions requestOptions = RequestOptions.fitCenterTransform().transform(new GlideRoundTransform(4))
                .placeholder(R.drawable.icon_place_holder_92_138_round)
                .error(R.drawable.icon_place_holder_92_138_round)
                .fallback(R.drawable.icon_place_holder_92_138_round);
        Glide.with(UIUtil.getContext()).load(item.getDisplayPic()).apply(requestOptions).into(courseIcon);

        if (!StringUtil.isEmpty(item.getBelongsTo()) && item.getBelongsTo().toUpperCase().equals(UIUtil.getString(R.string.vip_power))) {
            //属于动力营
            //动力营免费标签一直在
            helper.setGone(R.id.iv_tag, true);
            helper.setImageResource(R.id.iv_tag, R.drawable.icon_album_discounts_growth);
            if (item.isVipFlag()) {
                //已订阅
                helper.setGone(R.id.tv_course_original_price, false);
                helper.setGone(R.id.tv_course_price, false);
                helper.setGone(R.id.tv_course_label, true);
                helper.setText(R.id.tv_course_label, "已订阅");
            } else {
                //未订阅
                if (item.isBuy()) {
                    //已购买
                    helper.setGone(R.id.tv_course_original_price, false);
                    helper.setGone(R.id.tv_course_price, false);
                    helper.setGone(R.id.tv_course_label, true);
                    helper.setText(R.id.tv_course_label, "已购买");
                } else {
                    //未购买
                    if (item.isFree()) {
                        //免费
                        helper.setGone(R.id.tv_course_price, false);
                        helper.setGone(R.id.tv_course_label, true);
                        helper.setGone(R.id.tv_course_original_price, true);
                        helper.setText(R.id.tv_course_label, "免费");
                    } else {
                        //不免费
                        helper.setGone(R.id.tv_course_price, true);
                        helper.setGone(R.id.tv_course_label, false);
                        if (item.getOriginalPrice().equals(item.getPrice())) {
                            //价格相同 原价不展示
                            helper.setGone(R.id.tv_course_original_price, false);
                        } else {
                            helper.setGone(R.id.tv_course_original_price, true);
                            //限时优惠 展现
                            helper.setImageResource(R.id.iv_tag, R.drawable.icon_album_discounts);
                        }
                    }
                }
            }
        } else {
            //不属于动力营
            helper.setGone(R.id.iv_tag, false);
            if (item.isBuy()) {
                //已购买
                helper.setGone(R.id.tv_course_original_price, false);
                helper.setGone(R.id.tv_course_price, false);
                helper.setGone(R.id.tv_course_label, true);
                helper.setText(R.id.tv_course_label, "已购买");
            } else {
                //未购买
                if (item.getOriginalPrice().equals(item.getPrice())) {
                    //价格相同 原价不展示
                    helper.setGone(R.id.tv_course_original_price, false);
                    helper.setGone(R.id.iv_tag, false);
                } else {
                    helper.setGone(R.id.tv_course_original_price, true);
                    //限时优惠 展现
                    helper.setImageResource(R.id.iv_tag, R.drawable.icon_album_discounts);
                    helper.setGone(R.id.iv_tag, true);
                    helper.setImageResource(R.id.iv_tag, R.drawable.icon_album_discounts);
                }

                if (item.isFree()) {
                    //免费
                    helper.setGone(R.id.tv_course_price, false);
                    helper.setGone(R.id.tv_course_label, true);
                    helper.setText(R.id.tv_course_label, "免费");
                } else {
                    //不免费
                    helper.setGone(R.id.tv_course_price, true);
                    helper.setGone(R.id.tv_course_label, false);
                }
            }
        }

    }
}
