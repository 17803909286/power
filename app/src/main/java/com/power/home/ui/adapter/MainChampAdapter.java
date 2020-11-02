package com.power.home.ui.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.ChampGuideBean;
import com.power.home.ui.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by XWL on 2020/3/4.
 * Description:
 */
public class MainChampAdapter extends BaseQuickAdapter<ChampGuideBean.ContentsBean, BaseViewHolder> {

    public MainChampAdapter(int layoutResId, @Nullable List<ChampGuideBean.ContentsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChampGuideBean.ContentsBean item) {

        RequestOptions requestOptions = RequestOptions.fitCenterTransform().transform(new GlideRoundTransform(4))
                .placeholder(R.drawable.icon_place_holder_64_96)
                .error(R.drawable.icon_place_holder_64_96)
                .fallback(R.drawable.icon_place_holder_64_96);
        Glide.with(mContext).load(item.getDisplayPic()).apply(requestOptions).into((ImageView) helper.getView(R.id.iv_champ));
        helper.setText(R.id.tv_champ_name, item.getTitle());
        helper.setText(R.id.tv_champ_title, item.getSubtitle());
    }

}
