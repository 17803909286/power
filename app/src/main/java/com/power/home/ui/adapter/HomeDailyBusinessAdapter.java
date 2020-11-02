package com.power.home.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.power.home.R;
import com.power.home.common.util.FontUtil;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.TimeUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.interfaces.OnItemClickListener;
import com.power.home.ui.adapter.holder.MyViewHolder;
import com.power.home.ui.widget.GlideRoundTransform;
import com.power.home.ui.widget.LoopRecyclerView;

/**
 * Created by XWL on 2020/3/4.
 * Description:
 */
public class HomeDailyBusinessAdapter extends LoopRecyclerView.LoopAdapter<MyViewHolder> {

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_daily_business, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindLoopViewHolder(MyViewHolder holder, int position) {
        ImageView ivCourseIcon = (ImageView) holder.itemView.findViewById(R.id.iv_course_icon);
        TextView tvPlayAmount = (TextView) holder.itemView.findViewById(R.id.tv_play_amount);
        TextView tvCoursePhase = (TextView) holder.itemView.findViewById(R.id.tv_course_phase);
        TextView tvCourseTitle = (TextView) holder.itemView.findViewById(R.id.tv_course_title);
        TextView tvCourseContent = (TextView) holder.itemView.findViewById(R.id.tv_course_content);
        TextView tvCourseTime = (TextView) holder.itemView.findViewById(R.id.tv_course_time);

        tvCoursePhase.setText(datas.get(position).getSort() + "");
        FontUtil.setFont(tvCoursePhase);
        tvCourseTitle.setText(datas.get(position).getTitle());
        tvCourseContent.setText(datas.get(position).getSubtitle());
        String displayCount = (datas.get(position).getDisplayCount() > 9999) ? StringUtil.getPlayCount(datas.get(position).getDisplayCount()) + "万" : datas.get(position).getDisplayCount() + "";
        tvPlayAmount.setText(displayCount);
        if (datas.get(position).getCourseTime() > 3600) {
            tvCourseTime.setText(TimeUtil.dateToString(datas.get(position).getCourseTime() * 1000, TimeUtil.dateFormat_hour2));
        } else {
            tvCourseTime.setText(TimeUtil.dateToString(datas.get(position).getCourseTime() * 1000, TimeUtil.dateFormat_minutes));
        }

        RequestOptions requestOptions = RequestOptions.fitCenterTransform().transform(new GlideRoundTransform(4))
                .placeholder(R.drawable.icon_place_holder_92_138_round)
                .error(R.drawable.icon_place_holder_92_138_round)
                .fallback(R.drawable.icon_place_holder_92_138_round);
        Glide.with(UIUtil.getContext()).load(datas.get(position).getDisplayPic()).apply(requestOptions).into(ivCourseIcon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mOnItemClickListener) {
                    mOnItemClickListener.onItemClick(view, position);
                }
            }
        });
    }
}
