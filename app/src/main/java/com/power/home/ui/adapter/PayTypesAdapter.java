package com.power.home.ui.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.power.home.common.util.UIUtil;
import com.power.home.interfaces.OnItemClickListener;

import butterknife.ButterKnife;

/**
 * Created by S on 2018/4/16.
 */

public class PayTypesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String payType;

    private OnItemClickListener onItemClickListener;

    private OnItemClickListener onSetListener;

    private OnItemClickListener onDeleteListener;

    private OnItemClickListener onAutoListener;


    public void setPayType(String payType) {
        this.payType = payType;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnSetListener(OnItemClickListener onSetListener) {
        this.onSetListener = onSetListener;
    }

    public void setOnDeleteListener(OnItemClickListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public void setOnAutoListener(OnItemClickListener onAutoListener) {
        this.onAutoListener = onAutoListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PayTypesHolder(LayoutInflater.from(UIUtil.getContext()).inflate(
                0, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

    }



    @Override
    public int getItemCount() {
        return  0;
    }

    public class PayTypesHolder extends RecyclerView.ViewHolder {



        public PayTypesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
