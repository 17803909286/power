package com.power.home.interfaces;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ZHP on 2017/7/21.
 */

public abstract class LoadMoreOnScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager mLinearLayoutManager;
    private int lastVisibleItem;


    public LoadMoreOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        int count = recyclerView.getAdapter().getItemCount();
        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 ==
                count && count > 6) {
            onLoadMore();
        }


    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
    }

    public abstract void onLoadMore();
}
