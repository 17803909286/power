package com.power.home.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.ConvertRecordBean;

import java.util.List;

/**
 * Created by zhp on 2019/10/25
 */
public class ConvertRecordAdapter extends BaseQuickAdapter<ConvertRecordBean, BaseViewHolder> {
    public ConvertRecordAdapter(int layoutResId, @Nullable List<ConvertRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ConvertRecordBean item) {

        helper.setText(R.id.tv_item_convert_title,item.getUseType());
        helper.setText(R.id.tv_item_convert_date,item.getUseTime());
    }

}
