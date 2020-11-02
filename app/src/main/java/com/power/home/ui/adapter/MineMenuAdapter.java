package com.power.home.ui.adapter;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.common.util.DrawableUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.PersonCenterBean;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by zhp on 2019/10/25
 */
public class MineMenuAdapter extends BaseQuickAdapter<PersonCenterBean.MenuBean, BaseViewHolder> {
    public MineMenuAdapter(int layoutResId, @Nullable List<PersonCenterBean.MenuBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonCenterBean.MenuBean item) {

        TextView tv_item_mine_menu = helper.getView(R.id.tv_item_mine_menu);
        TextView tv_item_mine_phone = helper.getView(R.id.tv_item_mine_phone);

        helper.setText(R.id.tv_item_mine_menu, item.getTitle());
        helper.setText(R.id.tv_item_mine_phone, item.getSubtitle());

        if (item.getForwardAddress().contains("contactUs")) {
            tv_item_mine_phone.setVisibility(View.VISIBLE);
            tv_item_mine_menu.setCompoundDrawables(null, null, null, null);
        } else {
            tv_item_mine_phone.setVisibility(View.GONE);
            Drawable drawable = DrawableUtil.loadDrawable(R.drawable.icon_right_gray);
            tv_item_mine_menu.setCompoundDrawables(null, null, drawable, null);
        }

        DrawableUtil.loadUrl(R.drawable.icon_mine_change, helper.getView(R.id.iv_item_mine_menu), item.getDisplayPic());

        if (getData().size() - 1 == helper.getLayoutPosition()) {
            helper.setGone(R.id.view_line, false);
        } else {
            helper.setGone(R.id.view_line, true);
        }
    }

}
