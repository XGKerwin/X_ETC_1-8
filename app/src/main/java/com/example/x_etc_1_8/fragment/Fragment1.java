package com.example.x_etc_1_8.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_1_8.R;
import com.example.x_etc_1_8.bean.SSXS;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fragment1 extends Fragment {

    private List<SSXS> ssxsList;
    private TextView txtTitle;
    private LineChart lineChart;
    private List<Entry> entries;
    private List<String> strings;
    private String S_time;

    public Fragment1(List<SSXS> ssxsList) {
        this.ssxsList = ssxsList;
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            getdata();
            return false;
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_ssxs_1, null);
         initView(view);
        txtTitle.setText("温度");

        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    getTime();
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }while (true);
            }
        }).start();

        return view;
    }

    private void getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        Date date = new Date(System.currentTimeMillis());
        S_time = simpleDateFormat.format(date);
    }

    private void getdata() {
        if (ssxsList.size() == 0){
            return;
        }

        if (entries == null){
            entries = new ArrayList<>();
            strings = new ArrayList<>();
        }else {
            entries.clear();
        }

        strings.add(S_time);
        if (strings.size()==21){
            strings.remove(0);
        }

        for (int i=0;i<ssxsList.size();i++){
            SSXS ssxs = ssxsList.get(i);
            entries.add(new Entry(i,ssxs.getTemperature()));
        }

        LineDataSet dataSet = new LineDataSet(entries,"");
        dataSet.setCircleColor(Color.GRAY);
        dataSet.setCircleHoleRadius(3f);
        dataSet.setColor(Color.GRAY);
        dataSet.setValueTextSize(20);
        dataSet.setLineWidth(4f);
        dataSet.setDrawCircleHole(false);
        LineData data = new LineData(dataSet);

        YAxis yAxis = lineChart.getAxisRight();
        yAxis.setEnabled(false);
        YAxis yAxis1 = lineChart.getAxisLeft();
        yAxis1.setAxisMinimum(0);
        yAxis1.setAxisMaximum(45);
        yAxis1.setTextSize(20);
        yAxis1.setDrawAxisLine(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        xAxis.setLabelRotationAngle(90);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(strings.size());
        xAxis.setGranularity(1f);
        xAxis.setTextSize(20);

        lineChart.setData(data);
        lineChart.getLegend().setEnabled(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.invalidate();

    }

    private void initView(View view) {
        txtTitle = view.findViewById(R.id.txt_title);
        lineChart = view.findViewById(R.id.lineChart);
    }
}
