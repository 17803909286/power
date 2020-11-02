package com.power.home.ui.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.power.home.R;
import com.power.home.common.util.UIUtil;
import com.power.home.ui.widget.UILoadingBar;


/**
 * 加载更多的Holder
 * Created by ZHP on 2017/8/15.
 */
public class FootViewHolder extends RecyclerView.ViewHolder {
    public UILoadingBar mProgressBar;
    public TextView tv_state;
    public TextView tv_line1;
    public TextView tv_line2;
    //是否显示没有数据的提示
    private boolean hasShowEmptyMore = true;

    public static final int EMPTY = 0;
    //正在加载更多
    public static  final int LOADING_MORE = 1;
    //没有更多
    public static  final int NO_MORE = 2;



    public FootViewHolder(View itemView) {
        super(itemView);
        mProgressBar = (UILoadingBar) itemView.findViewById(R.id.progressbar);
        mProgressBar.setColors(new int[]{itemView.getResources().getColor(R.color.colorAccent)});
        tv_state = (TextView) itemView.findViewById(R.id.foot_view_item_tv);
        tv_line1 = (TextView) itemView.findViewById(R.id.tv_line1);
        tv_line2 = (TextView) itemView.findViewById(R.id.tv_line2);
    }

    /**
     * 设置状态
     * @param footer_state
     */
    public void setState(int position,int footer_state){
        if (position == 0) {//如果第一个就是脚布局,,那就让他隐藏
            mProgressBar.setVisibility(View.GONE);
            tv_line1.setVisibility(View.GONE);
            tv_line2.setVisibility(View.GONE);
            tv_state.setText("");
        }
        switch (footer_state) {
            case EMPTY:
                itemView.setVisibility(View.GONE);
                tv_line1.setVisibility(View.GONE);
                tv_line2.setVisibility(View.GONE);
                tv_state.setText("");
                break;
            case LOADING_MORE:
                itemView.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
                tv_line1.setVisibility(View.GONE);
                tv_line2.setVisibility(View.GONE);
                tv_state.setText(UIUtil.getString(R.string.loading_now));
                break;
            case NO_MORE:
                if(hasShowEmptyMore){
                    itemView.setVisibility(View.VISIBLE);
                    mProgressBar.setVisibility(View.GONE);
                    tv_line1.setVisibility(View.VISIBLE);
                    tv_line2.setVisibility(View.VISIBLE);
                    tv_state.setText(UIUtil.getString(R.string.data_empty));
                    tv_state.setVisibility(View.VISIBLE);
                }else{
                    itemView.setVisibility(View.VISIBLE);
                    mProgressBar.setVisibility(View.GONE);
                    tv_line1.setVisibility(View.GONE);
                    tv_line2.setVisibility(View.GONE);
                    tv_state.setVisibility(View.GONE);
                }
                break;
        }
    }

    public void setHasShowEmptyMore(boolean hasShowEmptyMore) {
        this.hasShowEmptyMore = hasShowEmptyMore;
    }
}
