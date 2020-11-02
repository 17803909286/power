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
public class FindListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new FindListViewHolder(LayoutInflater.from(UIUtil.getContext()).inflate(
                R.layout.item_find_list, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof FindListViewHolder) {
            FindListViewHolder findListViewHolder = (FindListViewHolder) holder;
            if (position == 0) {
                findListViewHolder.tvNumber.setText("");
                findListViewHolder.tvNumber.setBackgroundResource(R.drawable.icon_find_one);
            } else if ((position == 1)) {
                findListViewHolder.tvNumber.setText("");
                findListViewHolder.tvNumber.setBackgroundResource(R.drawable.icon_find_two);
            } else if ((position == 2)) {
                findListViewHolder.tvNumber.setText("");
                findListViewHolder.tvNumber.setBackgroundResource(R.drawable.icon_find_three);
            } else {
                findListViewHolder.tvNumber.setText(String.valueOf(position + 1));
                findListViewHolder.tvNumber.setBackground(null);
            }
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class FindListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.iv_head)
        TextView ivHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_fen)
        TextView tvFen;

        public FindListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
