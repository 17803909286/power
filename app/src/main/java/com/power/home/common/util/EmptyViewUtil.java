package com.power.home.common.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.home.R;


/**
 * Created by ZHP on 2017/9/13.
 */

public class EmptyViewUtil {
    private View emptyView;
    private TextView tv_empty_title;
    private ImageView iv_empty_message;

    private Context context;
    private View view;
    public EmptyViewUtil(Context context){
        this(context,null);
    }

    public EmptyViewUtil(Context context, View view){
        this.context = context;
        this.view = view;
    }

    public EmptyViewUtil emptyViewMsg(String message){
        initEmptyView();
        tv_empty_title.setText(message);
        return this;
    }

    public EmptyViewUtil emptyViewMsg(int messageId){
        emptyViewMsg(context.getResources().getString(messageId));
        return this;
    }

    public EmptyViewUtil emptyViewImage(int emptyResId){
        initEmptyView();
        iv_empty_message.setImageResource(emptyResId);
        return this;
    }

    public EmptyViewUtil emptyViewShow(){
        initEmptyView();
        emptyView.setVisibility(View.VISIBLE);
        return this;
    }

    public EmptyViewUtil emptyViewHiden(){
        initEmptyView();
        emptyView.setVisibility(View.GONE);
        return this;
    }

    private void initEmptyView(){
        if(null == emptyView){
            if(null != view){
                emptyView = view.findViewById(R.id.rl_empty);
            }else {
                if(null != context){
                    emptyView = ((Activity)context).findViewById(R.id.rl_empty);
                }
            }

        }
        if(null == tv_empty_title){
            tv_empty_title = emptyView.findViewById(R.id.tv_empty_title);
        }

        if(null == iv_empty_message)
        {
            iv_empty_message = emptyView.findViewById(R.id.iv_empty_message);
        }
    }
}
