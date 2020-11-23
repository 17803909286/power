package com.power.home.ui.adapter;

import android.widget.ImageView;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.MainGridBean;

import java.util.List;

/**
 * Created by XWL on 2020/3/5.
 * Description:
 */
public class HomeGridAdapter extends BaseQuickAdapter<MainGridBean.ContentsBean, BaseViewHolder> {
    public HomeGridAdapter(int layoutResId, @Nullable List<MainGridBean.ContentsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainGridBean.ContentsBean item) {
        helper.setText(R.id.tv_course_title, item.getTitle());
        ImageView courseIcon = helper.getView(R.id.iv_course_icon);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.icon_place_holder_40_40)
                .error(R.drawable.icon_place_holder_40_40)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.NORMAL);
        Glide.with(mContext).asBitmap().load(item.getDisplayPic()).apply(requestOptions).into(courseIcon);
    }
}
