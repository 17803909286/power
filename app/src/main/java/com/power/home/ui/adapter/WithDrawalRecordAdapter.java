package com.power.home.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.WithDrawalRecordBean;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by zhp on 2019/10/25
 */
public class WithDrawalRecordAdapter extends BaseQuickAdapter<WithDrawalRecordBean, BaseViewHolder> {
    public WithDrawalRecordAdapter(int layoutResId, @Nullable List<WithDrawalRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WithDrawalRecordBean item) {
        helper.setText(R.id.tv_title, "提现到银行卡账户(" + item.getBankCardNum() + ")");
        helper.setText(R.id.tv_date, item.getApplyTime());
        helper.setText(R.id.tv_money, item.getApplyAmount());
        helper.setText(R.id.tv_status, item.getAuditStatus());
    }

}
