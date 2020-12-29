package com.example.x_etc_1_8.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.x_etc_1_8.R;

public class X_wztp1_adapter extends BaseAdapter {

    private int[] arr ={R.drawable.weizhang1,R.drawable.weizhang2,R.drawable.weizhang8,R.drawable.weizhang9};

    @Override
    public int getCount() {
        return arr.length;
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
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_wztp,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource(arr[position]);


        return convertView;
    }
    class ViewHolder{
        private ImageView imageView;

        public ViewHolder(View convertView) {
            imageView = convertView.findViewById(R.id.img);
        }
    }
}
