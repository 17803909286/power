package com.power.home.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.common.util.StringUtil;
import com.power.home.data.bean.MessageBean;

import java.util.List;

/**
 * Created by zhp on 2019-11-20
 */
public class MessageAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> {
    public MessageAdapter(int layoutResId, @Nullable List<MessageBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {

        helper.setText(R.id.tv_message_records_title,item.getContent());
        helper.setText(R.id.tv_message_records_date,item.getSendTime());
        if (StringUtil.isEmpty(item.getForwardAddress())){
            helper.setVisible(R.id.iv_message_records_right,false);
        }else {
            helper.setVisible(R.id.iv_message_records_right,true);
        }
    }

}
