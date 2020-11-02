package com.power.home.ui.adapter;

import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.TimeUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.SearchBean;
import com.power.home.ui.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by XWL on 2020/3/6.
 * Description:
 */
public class SearchChildAdapter extends BaseMultiItemQuickAdapter<SearchBean.SearchResultsBean, BaseViewHolder> {


    private String type;

    public SearchChildAdapter(List<SearchBean.SearchResultsBean> data, String type) {
        super(data);

        //此处放反了
        addItemType(1, R.layout.item_all_course);//专辑
        addItemType(2, R.layout.item_course_album);//单课

        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchBean.SearchResultsBean item) {
        helper.setText(R.id.tv_course_title, item.getTitle());
        helper.setText(R.id.tv_course_content, item.getSubtitle());
        ImageView courseIcon = helper.getView(R.id.iv_course_icon);
        RequestOptions requestOptions = RequestOptions.fitCenterTransform().transform(new GlideRoundTransform(4))
                .placeholder(R.drawable.icon_place_holder_92_138_round)
                .error(R.drawable.icon_place_holder_92_138_round)
                .fallback(R.drawable.icon_place_holder_92_138_round);
        Glide.with(UIUtil.getContext()).load(item.getDisplayPic()).apply(requestOptions).into(courseIcon);
        switch (helper.getItemViewType()) {
            case 1:

                if (item.isContinueUpdating()) {
                    helper.setText(R.id.tv_course_size, "更新至" + item.getCourseSize() + "节/共" + item.getCourseTotalSize() + "节");
                } else {
                    helper.setText(R.id.tv_course_size, "已完结/共" + item.getCourseTotalSize() + "节");
                }
                helper.setText(R.id.tv_course_price, "¥" + item.getPrice() + "/年");
                TextView original_price = helper.getView(R.id.tv_course_original_price);
                original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                original_price.setText("¥" + item.getOriginalPrice());
                helper.setText(R.id.tv_play_amount, item.getPlayCount() > 9999 ? StringUtil.getPlayCount(item.getPlayCount()) + "万" : item.getPlayCount() + "");

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

                break;
            case 2:

                helper.setText(R.id.tv_play_amount, item.getPlayCount() > 9999 ? StringUtil.getPlayCount(item.getPlayCount()) + "万" : item.getPlayCount() + "");
                helper.setText(R.id.tv_course_phase, item.getSort());
                if (item.getClassification() == 1) {
                    //商道分钟显示期数
                    helper.setGone(R.id.ll_date, true);
                } else {
                    helper.setGone(R.id.ll_date, false);
                }
                if (item.getCourseTime() > 3600) {
                    helper.setText(R.id.tv_course_time, TimeUtil.dateToString(item.getCourseTime() * 1000, TimeUtil.dateFormat_hour2));
                } else {
                    helper.setText(R.id.tv_course_time, TimeUtil.dateToString(item.getCourseTime() * 1000, TimeUtil.dateFormat_minutes));
                }
                if (item.isFree()) {
                    helper.setGone(R.id.tv_course_label, true);
                    helper.setGone(R.id.iv_course_lock, false);
                    helper.setText(R.id.tv_course_label, "免费");
                } else {
                    if (item.isBuy()) {
                        helper.setGone(R.id.tv_course_label, true);
                        helper.setGone(R.id.iv_course_lock, false);
                        helper.setText(R.id.tv_course_label, "已解锁");
                    } else {
                        helper.setGone(R.id.tv_course_label, false);
                        helper.setGone(R.id.iv_course_lock, true);
                    }
                }
                break;
        }


    }

    @Override
    public int getItemCount() {
        if (StringUtil.isEquals(type, "1")) {
            return getData().size() > 3 ? 3 : getData().size();
        }
        return getData().size();
    }
}
