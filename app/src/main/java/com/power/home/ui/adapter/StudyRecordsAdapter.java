package com.power.home.ui.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.StudyRecordsBean;

import java.util.List;

/**
 * Created by zhp on 2019-11-20
 */
public class StudyRecordsAdapter extends BaseQuickAdapter<StudyRecordsBean, BaseViewHolder> {
    public StudyRecordsAdapter(int layoutResId, @Nullable List<StudyRecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudyRecordsBean item) {

        if (helper.getAdapterPosition() == 0) {
            helper.setBackgroundRes(R.id.iv_study_color, R.drawable.yuan_dot_accent);
            helper.setTextColor(R.id.tv_study_date, UIUtil.getColor(R.color.colorBlack1A1F28));
        } else {
            helper.setBackgroundRes(R.id.iv_study_color, R.drawable.yuan_dot_gray);
            helper.setTextColor(R.id.tv_study_date, UIUtil.getColor(R.color.colorBlack79808B));
        }
        helper.setText(R.id.tv_study_date, item.getName());
        RecyclerView recycleItemStudyRecords = helper.getView(R.id.recycle_item_study_records);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtil.getContext(), LinearLayoutManager.VERTICAL, false);
        recycleItemStudyRecords.setLayoutManager(linearLayoutManager);
        recycleItemStudyRecords.setNestedScrollingEnabled(false);//禁止滑动
        recycleItemStudyRecords.setHasFixedSize(true);
        recycleItemStudyRecords.setFocusable(false);//禁止获取焦点
        StudyRecordsItemAdapter studyRecordsItemAdapter = new StudyRecordsItemAdapter(R.layout.item_study_records_item, item.getStudyProgresses());
        recycleItemStudyRecords.setAdapter(studyRecordsItemAdapter);

        studyRecordsItemAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转播放单集
                ARouter.getInstance().build("/android/video")
                        .withString(Constant.type, "2")//单集
                        .withString(Constant.id, studyRecordsItemAdapter.getData().get(position).getCourseId())
                        .navigation();
            }
        });
    }

}
