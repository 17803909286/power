package com.power.home.ui.adapter;

import android.graphics.Typeface;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.LinkageAreaBean;

import java.util.List;

/**
 * @author xwl
 * @description:
 * @date :2020/3/4
 */
public class HomeTabAdapter extends BaseQuickAdapter<LinkageAreaBean.AreasBean, BaseViewHolder> {
    private int selector;

    public int getSelector() {
        return selector;
    }

    public void setSelector(int selector) {
        this.selector = selector;
        notifyDataSetChanged();
    }

    public HomeTabAdapter(int layoutResId, @Nullable List<LinkageAreaBean.AreasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LinkageAreaBean.AreasBean item) {
        helper.setText(R.id.tv_title, item.getAreaName());
        TextView tv_title = helper.getView(R.id.tv_title);
        if (helper.getLayoutPosition() == selector) {
            tv_title.setTextSize(18);
            tv_title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            helper.setVisible(R.id.view_indicator, true);
            helper.setTextColor(R.id.tv_title, mContext.getResources().getColor(R.color.colorBlack1A1F28));
        } else {
            tv_title.setTextSize(14);
            tv_title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            helper.setVisible(R.id.view_indicator, false);
            helper.setTextColor(R.id.tv_title, mContext.getResources().getColor(R.color.colorBlackC2C8D0));

        }
    }

}
