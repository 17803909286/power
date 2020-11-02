package com.power.home.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.VipRecordBean;

import java.util.List;

/**
 * Created by zhp on 2019/10/25
 */
public class VipRecordAdapter extends BaseQuickAdapter<VipRecordBean, BaseViewHolder> {
    public VipRecordAdapter(int layoutResId, @Nullable List<VipRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VipRecordBean item) {

        helper.setText(R.id.tv_item_vip_title, item.getChangeTitle());
        helper.setText(R.id.tv_item_vip_price, item.getChangeNumberStr());
        helper.setText(R.id.tv_item_vip_content, item.getChangeSubtitle());
        helper.setText(R.id.tv_item_vip_date, item.getChangeTime());
    }

}
