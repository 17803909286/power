package com.power.home.ui.adapter;

import android.graphics.Typeface;
import androidx.annotation.Nullable;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.data.bean.AllCourseBean;

import java.util.List;

/**
 * Created by XWL on 2020/3/5.
 * Description:
 */
public class AllCourseClassifyAdapter extends BaseQuickAdapter<AllCourseBean, BaseViewHolder> {
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        notifyDataSetChanged();
    }

    public AllCourseClassifyAdapter(int layoutResId, @Nullable List<AllCourseBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllCourseBean item) {
        helper.setText(R.id.tv_course_title,item.getModuleName());
        if (helper.getLayoutPosition()==position){
            helper.setTextColor(R.id.tv_course_title,mContext.getResources().getColor(R.color.colorBlack1A1F28));
            helper.setVisible(R.id.view_line,true);
            TextView tv_course_title = helper.getView(R.id.tv_course_title);
            tv_course_title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            helper.setBackgroundColor(R.id.ll_bg,mContext.getResources().getColor(R.color.colorWhite));
        }else {
            helper.setTextColor(R.id.tv_course_title,mContext.getResources().getColor(R.color.colorBlack79808B));
            helper.setVisible(R.id.view_line,false);
            TextView tv_course_title = helper.getView(R.id.tv_course_title);
            tv_course_title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            helper.setBackgroundColor(R.id.ll_bg,mContext.getResources().getColor(R.color.colorWhiteF5F7FA));
        }
    }
}
