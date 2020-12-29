package com.example.x_etc_1_8.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_1_8.R;
import com.example.x_etc_1_8.bean.CZJL;

import java.util.List;

public class X_zdgl_adapter extends BaseAdapter {
    private List<CZJL> czjlList;
    private Context context;

    public X_zdgl_adapter(List<CZJL> czjlList, Context context) {
        this.context = context;
        this.czjlList = czjlList;
    }

    @Override
    public int getCount() {
        return czjlList.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_zdgl, null);
            holder = new ViewHolder(convertView);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        CZJL czjl = czjlList.get(position);
        holder.number.setText(position+1+"");
        holder.ch.setText(czjl.getNumber()+"");
        holder.jine.setText(czjl.getJine()+"");
        holder.czr.setText(czjl.getCzr()+"");
        holder.time.setText(czjl.getTime()+"");

        return convertView;
    }


    class ViewHolder {
        private TextView number;
        private TextView ch;
        private TextView jine;
        private TextView czr;
        private TextView time;

        public ViewHolder(View view) {
            number = view.findViewById(R.id.number);
            ch = view.findViewById(R.id.ch);
            jine = view.findViewById(R.id.jine);
            czr = view.findViewById(R.id.czr);
            time = view.findViewById(R.id.time);
        }
    }
}
