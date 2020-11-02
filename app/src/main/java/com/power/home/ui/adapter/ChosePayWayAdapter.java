package com.power.home.ui.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.PayChannelBean;

import java.util.List;

/**
 * @author xwl
 * @description:
 * @date :2020/2/19
 */
public class ChosePayWayAdapter extends BaseQuickAdapter<PayChannelBean, BaseViewHolder> {
    private int choseId = 0;

    public int getChoseId() {
        return choseId;
    }

    public void setChoseId(int choseId) {
        this.choseId = choseId;
        notifyDataSetChanged();
    }

    public ChosePayWayAdapter(int layoutResId, @Nullable List<PayChannelBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayChannelBean item) {
        Glide.with(mContext).load(item.getChannelLogo()).into((ImageView) helper.getView(R.id.iv_header));
        helper.setText(R.id.tv_title,item.getChannelName());
        if (helper.getLayoutPosition() == choseId) {
            helper.setImageResource(R.id.iv_chose_state,R.drawable.icon_chose_normal);
        }else {
            helper.setImageResource(R.id.iv_chose_state,R.drawable.icon_chose_default);
        }

    }

}
