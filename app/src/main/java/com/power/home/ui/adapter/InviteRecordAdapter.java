package com.power.home.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.common.util.DrawableUtil;
import com.power.home.data.bean.InviteRecordBean;

import java.util.List;

/**
 * Created by zhp on 2019/10/25
 */
public class InviteRecordAdapter extends BaseQuickAdapter<InviteRecordBean, BaseViewHolder> {

    public InviteRecordAdapter(int layoutResId, @Nullable List<InviteRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InviteRecordBean item) {
        DrawableUtil.loadUrl(R.drawable.icon_avatar_header, helper.getView(R.id.civ_item_team), item.getAvatar());
        helper.setText(R.id.iv_item_team_name, item.getNickName());
        helper.setText(R.id.tv_item_team_invite_count, item.getInvitedRegistrationCount());
        helper.addOnClickListener(R.id.iv_item_team_phone);
        helper.addOnClickListener(R.id.iv_item_team_info);
        helper.setText(R.id.tv_item_team_buy_count, item.getBuyVipCount());

    }
}
