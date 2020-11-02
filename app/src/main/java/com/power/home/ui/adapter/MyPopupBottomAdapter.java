package com.power.home.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.power.home.R;

import java.util.List;

public class MyPopupBottomAdapter extends BaseAdapter {
    private Context context ;
    private List<String> list ;

    public MyPopupBottomAdapter(Context context, List<String> list ) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  holder=null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_status, parent, false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.item_tv_status.setText(list.get(position));
        return convertView;
    }
    public class ViewHolder{
        public View rootView;
        public TextView item_tv_status;
        public ViewHolder(View rootView){
            this.rootView = rootView;
            item_tv_status = rootView.findViewById(R.id.tv_status_content);

        }
    }
}
