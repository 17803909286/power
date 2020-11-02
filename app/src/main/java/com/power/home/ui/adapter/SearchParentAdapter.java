package com.power.home.ui.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.SearchBean;

import java.util.List;

/**
 * Created by XWL on 2020/3/17.
 * Description:
 */
public class SearchParentAdapter extends BaseQuickAdapter<SearchBean, BaseViewHolder> {

    private SearchChildAdapter searchChildAdapter;
    private Click click;

    public void setClick(Click click) {
        this.click = click;
    }

    public SearchParentAdapter(int layoutResId, @Nullable List<SearchBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchBean item) {
        helper.setText(R.id.tv_title,item.getTypeName());
        helper.addOnClickListener(R.id.tv_all);
        switch (item.getCourseType()){
            case "1":
                if (getData().size()>1){
                    if (item.getSearchResults().size()>3) {
                        helper.setGone(R.id.tv_all,true);
                    }else {
                        helper.setGone(R.id.tv_all,false);
                    }
                    if (getData().size()>1){
                        helper.setGone(R.id.view_bg,true);
                    }else {
                        helper.setGone(R.id.view_bg,false);
                    }
                }else {
                    helper.setGone(R.id.tv_all,false);
                    helper.setGone(R.id.view_bg,false);
                }


                break;
            case "2":
                helper.setGone(R.id.tv_all,false);
                helper.setGone(R.id.view_bg,false);
                break;
        }
        RecyclerView recycle_child = helper.getView(R.id.recycle_child);

        LinearLayoutManager courseManager = new LinearLayoutManager(mContext);
        recycle_child.setLayoutManager(courseManager);
        recycle_child.setNestedScrollingEnabled(false);//禁止滑动
        recycle_child.setHasFixedSize(true);
        recycle_child.setFocusable(false);//禁止获取焦点
        if (getData().size()>1) {
            searchChildAdapter = new SearchChildAdapter(item.getSearchResults(),item.getCourseType());
        }else {
            searchChildAdapter = new SearchChildAdapter(item.getSearchResults(),"2");

        }
        recycle_child.setAdapter(searchChildAdapter);
        searchChildAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                click.childClick(helper.getLayoutPosition(),position);
            }
        });

    }
    public interface Click{
        void childClick(int parent,int child);
    }
}
