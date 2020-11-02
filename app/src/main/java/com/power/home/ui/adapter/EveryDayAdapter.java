package com.power.home.ui.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.EveryDayBean;

import java.util.List;

/**
 * Created by ZHP on 2020/7/21 0021.
 */
public class EveryDayAdapter extends BaseQuickAdapter<EveryDayBean, BaseViewHolder> {
    public EveryDayAdapter(int layoutResId, @Nullable List<EveryDayBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EveryDayBean item) {
        helper.setText(R.id.tv_item_everyday_title, item.getActivityTitle());
        helper.setText(R.id.tv_item_everyday_subtitle, item.getActivityContent());
        helper.setText(R.id.tv_item_everyday_subtitle, item.getFinishValue() + "/" + item.getTargetValue());
//        REDPACKAGE --> 去分享 --->已完成, VIP/OFFLINE/COURSE--> 去邀请 -->去领取
        TextView tv_item_everyday_button = helper.getView(R.id.tv_item_everyday_button);
        boolean isFinish = item.getFinishValue() == item.getTargetValue();
        switch (item.getActivityClassify()) {
            case "REDPACKAGE":
                tv_item_everyday_button.setText(isFinish ? "已完成" : "去分享");
                tv_item_everyday_button.setEnabled(!isFinish);
                break;
            case "VIP":
                tv_item_everyday_button.setText(isFinish ? "去赠送" : "去邀请");
                tv_item_everyday_button.setEnabled(true);
                break;
            case "OFFLINE":
            case "COURSE":
                tv_item_everyday_button.setText(isFinish ? "去领取" : "去邀请");
                tv_item_everyday_button.setEnabled(true);
                break;
        }

        helper.addOnClickListener(R.id.tv_item_everyday_button);
    }
}
