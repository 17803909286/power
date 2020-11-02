package com.power.home.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.common.util.DrawableUtil;
import com.power.home.common.util.StringUtil;
import com.power.home.data.bean.PersonCenterBean;

import java.util.List;

/**
 * Created by zhp on 2019/10/25
 */
public class MineDiamonAdapter extends BaseQuickAdapter<PersonCenterBean.MenuBean, BaseViewHolder> {

    private String growthPromotionActive;
    private String growthPromotionIcon;

    public void setGrowthPromotionActive(String growthPromotionActive) {
        this.growthPromotionActive = growthPromotionActive;
    }

    public void setGrowthPromotionIcon(String growthPromotionIcon) {
        this.growthPromotionIcon = growthPromotionIcon;
    }

    public MineDiamonAdapter(int layoutResId, @Nullable List<PersonCenterBean.MenuBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonCenterBean.MenuBean item) {

        helper.setText(R.id.tv_item_mine_diamon, item.getTitle());
        DrawableUtil.loadUrl(R.drawable.icon_mine_study_record, helper.getView(R.id.iv_item_mine_diamon), item.getDisplayPic());
        if (item.getForwardAddress().contains("invite") && StringUtil.isNotNullString(growthPromotionActive) && growthPromotionActive.equals("true")) {
            helper.setGone(R.id.iv_get, true);
            DrawableUtil.loadUrl(R.drawable.icon_mine_get, helper.getView(R.id.iv_get), growthPromotionIcon);
        } else {
            helper.setGone(R.id.iv_get, false);
        }
    }

}
