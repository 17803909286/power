package com.power.home.ui.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.VipPowerBean;

import java.util.List;

/**
 * Created by ZHP on 2020/4/28 0028.
 */
public class VipPowerAdapter extends BaseQuickAdapter<VipPowerBean, BaseViewHolder> {
    public VipPowerAdapter(int layoutResId, @Nullable List<VipPowerBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VipPowerBean item) {
        helper.setText(R.id.tv_vip_title, item.getTitle());
        helper.setText(R.id.tv_vip_content, item.getContent());
        ImageView courseIcon = helper.getView(R.id.iv_vip_icon);
        Glide.with(mContext).load(item.getImg()).into(courseIcon);
    }
}
