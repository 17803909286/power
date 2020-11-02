package com.power.home.ui.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.common.util.StringUtil;
import com.power.home.data.bean.StudyRecordsItemBean;

import java.text.DecimalFormat;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by zhp on 2019-11-20
 */
public class StudyRecordsItemAdapter extends BaseQuickAdapter<StudyRecordsItemBean, BaseViewHolder> {
    public StudyRecordsItemAdapter(int layoutResId, @Nullable List<StudyRecordsItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudyRecordsItemBean item) {
        helper.setText(R.id.tv_study_records_item_title, item.getCourseTitle());
        TextView tv_study_records_item_progress = helper.getView(R.id.tv_study_records_item_progress);
        double d = (double) item.getTopProcess() / (double) item.getCourseTime();
        DecimalFormat df = new DecimalFormat("0%");
        String format = df.format(d);
        if (d > 0.01) {
            tv_study_records_item_progress.setText("已学：" + format);
        } else {
            tv_study_records_item_progress.setText("已学：0%");
        }
    }
}
