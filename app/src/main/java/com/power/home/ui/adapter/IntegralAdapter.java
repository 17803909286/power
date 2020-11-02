package com.power.home.ui.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.power.home.R;
import com.power.home.common.util.UIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhp on 2019/10/25
 */
public class IntegralAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new IntegralViewHolder(LayoutInflater.from(UIUtil.getContext()).inflate(
                R.layout.item_integral, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof IntegralViewHolder) {
            IntegralViewHolder integralViewHolder = (IntegralViewHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public static class IntegralViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_title)
        TextView ivTitle;
        @BindView(R.id.iv_date)
        TextView ivDate;
        @BindView(R.id.tv_count)
        TextView tvCount;

        public IntegralViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
