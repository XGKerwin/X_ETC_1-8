package com.example.x_etc_1_8.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_1_8.R;
import com.example.x_etc_1_8.adapter.X_zdgl_adapter;
import com.example.x_etc_1_8.adapter.X_wdzh_adapter;
import com.example.x_etc_1_8.bean.CZJL;

import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Fragment_zdgl extends Fragment {


    private Spinner spinner;
    private Button btnCx;
    private ListView listview;
    private X_wdzh_adapter Sadapter;
    private String[] arr = {"时间升序", "时间降序"};
    private X_zdgl_adapter adapter;
    private List<CZJL> czjlList;
    private TextView txtZanwu;
    private LinearLayout lin;
    private int pos = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_zdgl, null);
        initView(view);
        czjlList = LitePal.findAll(CZJL.class);

        Log.d("dddddd", "onCreateView: "+czjlList.size());
        if (czjlList.size() == 0) {
            txtZanwu.setVisibility(View.VISIBLE);
            lin.setVisibility(View.GONE);
        }else {
            txtZanwu.setVisibility(View.GONE);
            lin.setVisibility(View.VISIBLE);
        }

        showspadapter();

        showlist();

        btnCx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showlist();
            }
        });

        return view;
    }

    private void showspadapter() {
        Sadapter = new X_wdzh_adapter(getContext(),arr);
        spinner.setAdapter(Sadapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void showlist() {
        Collections.sort(czjlList, new Comparator<CZJL>() {
            @Override
            public int compare(CZJL o1, CZJL o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy.m.dd HH:mm");
                Date date = null, date1 = null;
                try {
                    date = format.parse(o1.getTime());
                    date1 = format.parse(o2.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (spinner.getSelectedItemId() ==0){
                    return date1.compareTo(date);
                }else {
                    return date.compareTo(date1);
                }
            }
        });


        adapter = new X_zdgl_adapter(czjlList, getContext());
        listview.setAdapter(adapter);
    }

    private void initView(View view) {
        spinner = view.findViewById(R.id.spinner);
        btnCx = view.findViewById(R.id.btn_cx);
        listview = view.findViewById(R.id.listview);
        txtZanwu = view.findViewById(R.id.txt_zanwu);
        lin = view.findViewById(R.id.lin);
    }
}
