package com.power.home.ui.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.CourseAlbumBean;

import java.util.List;

/**
 * Created by zhp on 2019/10/25
 */
public class HomeAdapter extends BaseMultiItemQuickAdapter<CourseAlbumBean, BaseViewHolder> {

    public final int CHANG = 0;
    public final int ZHENG = 1;
    private List<CourseAlbumBean> mData;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeAdapter(List<CourseAlbumBean> data) {
        super(data);
        addItemType(CHANG, R.layout.item_home_chang);
        addItemType(ZHENG, R.layout.item_home_zheng);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseAlbumBean item) {

        /*ViewGroup.LayoutParams layoutParams = helper.itemView.getLayoutParams();
        if (holder.getItemViewType() == CHANG) {
            layoutParams.height = UIUtil.dip2px(280);
        } else {
            layoutParams.height = UIUtil.dip2px(240);
        }
         holder.itemView.setLayoutParams(layoutParams);*/
        switch (helper.getItemViewType()) {
            case CHANG:
                break;
            case ZHENG:
                break;
            default:
                break;
        }
        helper.setText(R.id.tv_item_title, item.getTitle());

    }

    @Override
    protected int getDefItemViewType(int position) {
//        return mData.get(position).getItemType();
        if (position % 3 == 0) {
            return CHANG;
        } else {
            return ZHENG;
        }
    }

}
