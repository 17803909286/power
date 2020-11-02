package com.power.home.ui.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.power.home.R;
import com.power.home.common.util.UIUtil;
import com.power.home.interfaces.OnItemClickListener;
import com.power.home.interfaces.OnMultiClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhp on 2019/10/25
 */
public class DiamondsRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new DiamondsRecordViewHolder(LayoutInflater.from(UIUtil.getContext()).inflate(
                R.layout.item_diamonds_record, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof DiamondsRecordViewHolder) {
            DiamondsRecordViewHolder diamondsViewHolder = (DiamondsRecordViewHolder) holder;
            diamondsViewHolder.itemView.setOnClickListener(new OnMultiClickListener() {
                @Override
                public void onMultiClick(View v) {
                    if (null != onItemClickListener) {
                        onItemClickListener.onItemClick(v, position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class DiamondsRecordViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        public DiamondsRecordViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
