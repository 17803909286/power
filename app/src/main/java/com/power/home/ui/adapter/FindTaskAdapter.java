package com.power.home.ui.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.home.R;
import com.power.home.common.util.UIUtil;
import com.power.home.interfaces.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhp on 2019/10/25
 */
public class FindTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private int TITLE = 0;
    private int CONTENT = 1;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0 || position == 5) {
            return TITLE;
        } else {
            return CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TITLE) {
            return new FindTaskTitleViewHolder(LayoutInflater.from(UIUtil.getContext()).inflate(
                    R.layout.item_find_task_title, parent, false));
        } else {
            return new FindTaskContentViewHolder(LayoutInflater.from(UIUtil.getContext()).inflate(
                    R.layout.item_find_task_content, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TITLE) {
            FindTaskTitleViewHolder findTaskTitleViewHolder = (FindTaskTitleViewHolder) holder;
            if (position == 0) {
                findTaskTitleViewHolder.tvTaskBigTitle.setText("每日任务（1/4）");
            } else {
                findTaskTitleViewHolder.tvTaskBigTitle.setText("新手任务（1/3）");
            }
        } else {
            FindTaskContentViewHolder findTaskContentViewHolder = (FindTaskContentViewHolder) holder;
            if (position == 1) {
                findTaskContentViewHolder.ivTaskIcon.setImageResource(R.drawable.icon_find_day);
                findTaskContentViewHolder.tvTaskTitle.setText("每日签到 0/4");
                findTaskContentViewHolder.tvTaskContent.setText("10积分");
            } else if (position == 2) {
                findTaskContentViewHolder.ivTaskIcon.setImageResource(R.drawable.icon_find_hudong);
                findTaskContentViewHolder.tvTaskTitle.setText("发表一条互动 0/4");
                findTaskContentViewHolder.tvTaskContent.setText("10积分");
            }else {
                findTaskContentViewHolder.ivTaskIcon.setImageResource(R.drawable.icon_find_hudong);
                findTaskContentViewHolder.tvTaskTitle.setText("发表一条互动 0/4");
                findTaskContentViewHolder.tvTaskContent.setText("10积分");
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnItemClickListener) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public static class FindTaskTitleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_task_big_title)
        TextView tvTaskBigTitle;

        public FindTaskTitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class FindTaskContentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_task_icon)
        ImageView ivTaskIcon;
        @BindView(R.id.tv_task_title)
        TextView tvTaskTitle;
        @BindView(R.id.tv_task_content)
        TextView tvTaskContent;
        @BindView(R.id.button_finish)
        Button buttonFinish;

        public FindTaskContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
