package com.power.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.data.bean.SearchBean;
import com.power.home.di.component.AppComponent;
import com.power.home.ui.adapter.SearchChildAdapter;
import com.power.home.ui.widget.MyTitleBar;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAllAlbumActivity extends BaseActivity {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.recycle_album)
    RecyclerView recycleAlbum;
    private SearchChildAdapter searchChildAdapter;
    private SearchBean searchBean;

    @Override
    public int setLayout() {
        return R.layout.activity_search_all_album;
    }

    @Override
    public void getExtras() {
        Intent intent = getIntent();
        searchBean = (SearchBean) intent.getSerializableExtra("search");
    }

    @Override
    public boolean init() {
        initRecycler();
        return false;
    }

    private void initRecycler() {
        LinearLayoutManager albumManager = new LinearLayoutManager(this);
        recycleAlbum.setLayoutManager(albumManager);
        searchChildAdapter = new SearchChildAdapter(searchBean.getSearchResults(),"2");
        recycleAlbum.setAdapter(searchChildAdapter);
    }

    @Override
    public void setListener() {
        backListener(titleBar);

        searchChildAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SearchBean.SearchResultsBean searchResultsBean = searchChildAdapter.getData().get(position);
                ARouter.getInstance().build("/android/video" )
                        .withString(Constant.type, String.valueOf(searchResultsBean.getType()))
                        .withString(Constant.id, searchResultsBean.getId())
                        .navigation();
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }


}
