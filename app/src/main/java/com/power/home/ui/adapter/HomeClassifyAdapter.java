package com.power.home.ui.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.LinkageAreaBean;

import java.util.List;

/**
 * Created by XWL on 2020/3/4.
 * Description:
 */
public class HomeClassifyAdapter extends BaseQuickAdapter<LinkageAreaBean.AreasBean, BaseViewHolder> {
    private onItemClick click;

    public void setClick(onItemClick click) {
        this.click = click;
    }

    public HomeClassifyAdapter(int layoutResId, @Nullable List<LinkageAreaBean.AreasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LinkageAreaBean.AreasBean item) {
        helper.setText(R.id.tv_classify_title, item.getAreaName());
        RecyclerView recycle_classify_child = helper.getView(R.id.recycle_classify_child);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recycle_classify_child.setLayoutManager(layoutManager);
        recycle_classify_child.setNestedScrollingEnabled(false);//禁止滑动
        recycle_classify_child.setHasFixedSize(true);
        recycle_classify_child.setFocusable(false);//禁止获取焦点
        HomeClassifyChildAdapter adapter = new HomeClassifyChildAdapter(R.layout.item_home_course_child_list, item.getContents());
        recycle_classify_child.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                click.childClick(helper.getLayoutPosition(), position);
            }
        });
        helper.addOnClickListener(R.id.ll_item_class_more);
    }

    public interface onItemClick {
        void childClick(int position, int childPosition);
    }
}
