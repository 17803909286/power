package com.power.home.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.power.home.data.bean.DailyBusinessBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZHP on 2020/7/14 0014.
 */
public class LoopRecyclerView extends RecyclerView {

    public LoopRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoopRecyclerView(Context context) {
        super(context);
    }

    public LoopRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initView();
    }

    @Override
    public LoopAdapter getAdapter() {
        return (LoopAdapter) super.getAdapter();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (!(adapter instanceof LoopAdapter)) {
            throw new IllegalArgumentException("adapter must  instanceof LoopAdapter!");
        }
        super.setAdapter(adapter);
        //开始时的偏移量 涉及到网络数据加载后才会有数量，因此初始化adapter的时候，无效
        scrollToPosition(getAdapter().getItemRawCount() * 10000);
    }

    private void initView() {
        new RPagerSnapHelper().setOnPageListener(new RPagerSnapHelper.OnPageListener() {
            @Override
            public void onPageSelector(int position) {
                Log.e("", "onPageSelector: " + position % getAdapter().getItemRawCount());
            }
        }).attachToRecyclerView(this);
    }

    public static abstract class LoopAdapter<T extends ViewHolder> extends Adapter<T> {

        public List<DailyBusinessBean.ContentsBean> datas = new ArrayList<>();

        public void setDatas(List<DailyBusinessBean.ContentsBean> datas) {
            this.datas = datas;
            notifyDataSetChanged();
        }

        public List<DailyBusinessBean.ContentsBean> getData() {
            return datas;
        }

        /**
         * 真实数据的大小
         */
        public int getItemRawCount() {
            return datas == null ? 0 : datas.size();
        }

        @Override
        final public int getItemViewType(int position) {
            return getLoopItemViewType(position % getItemRawCount());
        }

        protected int getLoopItemViewType(int position) {
            return 0;
        }

        @Override
        final public void onBindViewHolder(T holder, int position) {
            onBindLoopViewHolder(holder, position % getItemRawCount());
        }

        public abstract void onBindLoopViewHolder(T holder, int position);

        @Override
        final public int getItemCount() {
            int rawCount = getItemRawCount();
            if (rawCount > 0) {
                return Integer.MAX_VALUE;
            }
            return 0;
        }
    }
}
