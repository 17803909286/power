package com.power.home.ui.adapter;

import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.common.util.DrawableUtil;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.MoneyInRecordBean;

import java.util.List;

/**
 * Created by zhp on 2019/10/25
 */
public class MoneyInRecordAdapter extends BaseQuickAdapter<MoneyInRecordBean, BaseViewHolder> {
    public MoneyInRecordAdapter(int layoutResId, @Nullable List<MoneyInRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MoneyInRecordBean item) {
        TextView detail = helper.getView(R.id.tv_item_money_detail);
        if (StringUtil.isNotNullString(item.getShowDetail()) && item.getShowDetail().equals("true")) {
            detail.setVisibility(View.VISIBLE);
        } else {
            detail.setVisibility(View.GONE);
        }
        helper.setText(R.id.tv_item_money_title, item.getIncomeName());
        helper.setText(R.id.tv_item_money_price, item.getIncomeAmount());
        helper.setText(R.id.tv_item_money_content, StringUtil.isEmpty(item.getNickName())?item.getPrefixName():item.getPrefixName() + item.getNickName());
        helper.setText(R.id.tv_item_money_one, item.getTopicName());
        helper.setText(R.id.tv_item_money_two, item.getTopicPrice());
        helper.setText(R.id.tv_item_money_date, item.getIncomeTime());
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (detail.getText().equals("详情")) {
                    helper.setGone(R.id.ll_item_money_info, true);
                    Drawable drawable = DrawableUtil.loadDrawable(R.drawable.icon_tip_up);
                    detail.setCompoundDrawables(null, null, drawable, null);
                    detail.setCompoundDrawablePadding(UIUtil.dip2px(5));
                    detail.setText("收起");
                } else {
                    helper.setGone(R.id.ll_item_money_info, false);
                    Drawable drawable = DrawableUtil.loadDrawable(R.drawable.icon_tip_down);
                    detail.setCompoundDrawables(null, null, drawable, null);
                    detail.setCompoundDrawablePadding(UIUtil.dip2px(5));
                    detail.setText("详情");
                }
            }
        });
    }

}
