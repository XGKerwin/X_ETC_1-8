package com.example.x_etc_1_8.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.x_etc_1_8.R;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fragment_cxgl extends Fragment {

    private TextView txtTime;
    private TextView txtChe;
    private TextView txt1;
    private Switch Switch1;
    private TextView txt2;
    private Switch Switch2;
    private TextView txt3;
    private Switch Switch3;
    private ImageView imgHld;
    private String S_time;
    private int day;
    private boolean isLoop = false;
    private TimePickerView timePickerView;
    private Date date1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_cxgl, null);
        initView(view);

        AnimationDrawable animationDrawable = (AnimationDrawable) imgHld.getDrawable();
        animationDrawable.start();

        getTime();
        getchuxing();

        getSwitch();

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                try {
                    calendar.setTime(simpleDateFormat.parse(txtTime.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                timePickerView = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        int nian,yue;
                        date1 = date;
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date1);
                        nian = calendar.get(Calendar.YEAR);
                        yue = calendar.get(Calendar.MONTH)+1;
                        day = calendar.get(Calendar.DAY_OF_MONTH);
                        txtTime.setText(nian+"年"+yue+"月"+day+"日");
                        getSwitch();
                    }
                }).setDate(calendar).setType(new boolean[]{true, true, true, false, false, false}).isDialog(true).build();
                timePickerView.show();

            }
        });



        return view;
    }

    private void getSwitch() {
        if (day%2==1) {
            txtChe.setText("单号出行车辆1、3");
            txt1.setText("开");
            txt2.setText("关");
            txt3.setText("开");
            Switch1.setChecked(!isLoop);
            Switch2.setChecked(isLoop);
            Switch3.setChecked(!isLoop);
        } else {
            txtChe.setText("双号出行车辆2");
            txt1.setText("关");
            txt2.setText("开");
            txt3.setText("关");
            Switch1.setChecked(isLoop);
            Switch2.setChecked(!isLoop);
            Switch3.setChecked(isLoop);
        }

        Switch1.setEnabled(false);
        Switch2.setEnabled(false);
        Switch3.setEnabled(false);
    }

    private void getchuxing() {
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date(System.currentTimeMillis());
        S_time = format.format(date);
        txtTime.setText(S_time);
    }

    private void initView(View view) {
        txtTime = view.findViewById(R.id.txt_time);
        txtChe = view.findViewById(R.id.txt_che);
        txt1 = view.findViewById(R.id.txt1);
        Switch1 = view.findViewById(R.id.Switch1);
        txt2 = view.findViewById(R.id.txt2);
        Switch2 = view.findViewById(R.id.Switch2);
        txt3 = view.findViewById(R.id.txt3);
        Switch3 = view.findViewById(R.id.Switch3);
        imgHld = view.findViewById(R.id.img_hld);
    }
}
