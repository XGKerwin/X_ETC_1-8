package com.example.x_etc_1_8.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.x_etc_1_8.R;
import com.example.x_etc_1_8.bean.HJZB;

import java.util.List;

public class X_hjzb_adapter extends BaseAdapter {
    private HJZB hjzb;
    private List<HJZB> hjzbList;
    private String[] arr = {"温度","湿度","光照","CQ2","PM2.5","道路状态"};

    public X_hjzb_adapter(HJZB hjzb, List<HJZB> hjzbList) {
        this.hjzb = hjzb;
        this.hjzbList = hjzbList;
    }

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
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hjzb, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        int max = 0,min = 0;
        HJZB hjzb1 = hjzbList.get(hjzbList.size()-1);
        holder.txtTitle.setText(arr[position]);
        switch (position){
            case 0:
                max = hjzb.getTemperature();
                min = hjzb1.getTemperature();
                break;
            case 1:
                max = hjzb.getHumidity();
                min = hjzb1.getHumidity();
                break;
            case 2:
                max = hjzb.getIllumination();
                min = hjzb1.getIllumination();
                break;
            case 3:
                max = hjzb.getCo2();
                min = hjzb1.getCo2();
                break;
            case 4:
                max = hjzb.getPm25();
                min = hjzb1.getPm25();
                break;
            case 5:
                max = hjzb.getRoadId();
                min = hjzb1.getRoadId();
                break;
            default:
                break;
        }
        if (min>max){
            holder.rela.setBackgroundResource(R.drawable.lin_hong);
        }else {
            holder.rela.setBackgroundResource(R.drawable.lin_lv);
        }
        holder.txtZhi.setText(min+"");


        return convertView;
    }

    class ViewHolder {

        private RelativeLayout rela;
        private TextView txtTitle;
        private TextView txtZhi;

        public ViewHolder(View view) {
            rela = view.findViewById(R.id.rela);
            txtTitle = view.findViewById(R.id.txt_title);
            txtZhi = view.findViewById(R.id.txt_zhi);
        }
    }
}
