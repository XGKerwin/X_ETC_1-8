package com.example.x_etc_1_8.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_1_8.R;
import com.example.x_etc_1_8.bean.HLD;

import java.util.List;

public class X_hld_adapter extends BaseAdapter {
    private List<HLD> hldList;


    public X_hld_adapter(List<HLD> hldList) {
        this.hldList = hldList;
    }

    @Override
    public int getCount() {
        return hldList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hld, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HLD hld = hldList.get(position);
        holder.lukou.setText(hld.getNumber()+"");
        holder.hong.setText(hld.getRed()+"");
        holder.huang.setText(hld.getYellow()+"");
        holder.lv.setText(hld.getGreen()+"");

        return convertView;
    }


    class ViewHolder {
        private TextView lukou;
        private TextView hong;
        private TextView huang;
        private TextView lv;

        public ViewHolder(View convertView) {
            lukou = convertView.findViewById(R.id.lukou);
            hong = convertView.findViewById(R.id.hong);
            huang = convertView.findViewById(R.id.huang);
            lv = convertView.findViewById(R.id.lv);
        }
    }
}
