package com.power.home.ui.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.power.home.common.util.UIUtil;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by zhp on 2019/5/8
 */
public class AutoPollAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int state = 0;

    private List<String> lefts;

    public void setState(int state) {
        this.state = state;
    }

    public void setLefts(List<String> lefts) {
        this.lefts = lefts;
    }

    @Override
    public int getItemViewType(int position) {
        return state;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new LeftViewHolder(LayoutInflater.from(UIUtil.getContext()).inflate(0, parent, false));
        } else if (viewType == 1) {
            return new RightViewHolder(LayoutInflater.from(UIUtil.getContext()).inflate(0, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof LeftViewHolder) {
            LeftViewHolder leftViewHolder = (LeftViewHolder) holder;

        } else if (holder instanceof RightViewHolder) {
            RightViewHolder rightViewHolder = (RightViewHolder) holder;

        }
    }

    @Override
    public int getItemCount() {
//        return 30;
        return Integer.MAX_VALUE;
    }

    class LeftViewHolder extends RecyclerView.ViewHolder {



        public LeftViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class RightViewHolder extends RecyclerView.ViewHolder {


        public RightViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
