package com.power.home.ui.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.common.util.StringUtil;
import com.power.home.data.bean.OrderBean;

import java.util.List;

/**
 * Created by zhp on 2019-11-20
 */
public class OrderAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {
    public OrderAdapter(int layoutResId, @Nullable List<OrderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.icon_place_holder_64_96)
                .error(R.drawable.icon_place_holder_64_96)
                .fallback(R.drawable.icon_place_holder_64_96);
        Glide.with(mContext).load(item.getDisplayPic()).apply(requestOptions)
                .into((ImageView) helper.getView(R.id.iv_item_order));
        helper.setText(R.id.iv_item_order_title, item.getTitle());
        helper.setText(R.id.iv_item_order_content, item.getSubtitle());
        helper.setText(R.id.iv_item_order_date, "到期时间:" + item.getTopicEndTime());
        helper.setGone(R.id.iv_item_order_status, item.getTopicExpire());
        helper.addOnClickListener(R.id.iv_item_order_pay);
    }

}
