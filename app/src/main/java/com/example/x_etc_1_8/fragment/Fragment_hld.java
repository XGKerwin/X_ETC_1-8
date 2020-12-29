package com.example.x_etc_1_8.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_1_8.bean.HLD;
import com.example.x_etc_1_8.R;
import com.example.x_etc_1_8.adapter.X_hld_adapter;
import com.example.x_etc_1_8.adapter.X_wdzh_adapter;
import com.example.x_etc_1_8.net.OKHttpLo;
import com.example.x_etc_1_8.net.OKHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Fragment_hld extends Fragment {

    private Spinner spinner;
    private Button btnCx;
    private ListView listview;
    private String[] arr = {"路口升序","路口降序","红灯升序","红灯降序","绿灯升序","绿灯降序","黄灯升序","黄灯降序"};
    private X_wdzh_adapter Sadapter;
    private int pos = 0;
    private List<HLD> hldList;
    private X_hld_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_hld, null);
        initView(view);
        getfor();
        getSpinner();

        btnCx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getpaixu();
            }
        });

        return view;
    }

    private void getfor() {
        if (hldList == null){
            hldList = new ArrayList<>();
        }else {
            hldList.clear();
        }
        for (int i=1;i<=5;i++){
            getOkhttp(i);
        }
    }

    private void getOkhttp(int i) {
        /**
         * {"UserName":"user1","number":"1"}
         */
        new OKHttpTo()
                .setUrl("get_traffic_light")
                .setJsonObject("UserName","user1")
                .setJsonObject("number",i)
                .setOkHttpLo(new OKHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hldList.addAll((Collection<? extends HLD>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                        , new TypeToken<List<HLD>>(){}.getType()));
                        if (hldList.size()==5){
                            Collections.sort(hldList, new Comparator<HLD>() {
                                @Override
                                public int compare(HLD o1, HLD o2) {
                                    return o1.getNumber() - o2.getNumber();
                                }
                            });
                            adapter = new X_hld_adapter(hldList);
                            listview.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getSpinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Sadapter = new X_wdzh_adapter(getContext(),arr);
        spinner.setAdapter(Sadapter);

    }

    private void getpaixu() {
        Collections.sort(hldList, new Comparator<HLD>() {
            @Override
            public int compare(HLD o1, HLD o2) {
                switch (pos){
                    case 0:
                        return o1.getNumber() - o2.getNumber();
                    case 1:
                        return o2.getNumber() - o1.getNumber();
                    case 2:
                        return o1.getRed() - o2.getRed();
                    case 3:
                        return o2.getRed() - o1.getRed();
                    case 4:
                        return o1.getGreen() - o2.getGreen();
                    case 5:
                        return o2.getGreen() - o1.getGreen();
                    case 6:
                        return o1.getYellow() - o2.getYellow();
                    case 7:
                        return o2.getYellow() - o1.getYellow();
                }
                return 0;
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void initView(View view) {
        spinner = view.findViewById(R.id.spinner);
        btnCx = view.findViewById(R.id.btn_cx);
        listview = view.findViewById(R.id.listview);
    }
}
