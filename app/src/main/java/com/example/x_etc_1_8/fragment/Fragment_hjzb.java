package com.example.x_etc_1_8.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_1_8.bean.HJZB;
import com.example.x_etc_1_8.R;
import com.example.x_etc_1_8.adapter.X_hjzb_adapter;
import com.example.x_etc_1_8.activity.Activity_ssxs;
import com.example.x_etc_1_8.net.OKHttpLo;
import com.example.x_etc_1_8.net.OKHttpTo;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fragment_hjzb extends Fragment {
    private TextView title;
    private GridView gridview;
    private List<HJZB> hjzbList;
    private HJZB hjzb;
    private X_hjzb_adapter adapter;
    private String time;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_hjzb, null);
        initView(view);
        hjzbList = new ArrayList<>();
        title.setText("环境指标");
        getTime();
        getyuzhi();
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), Activity_ssxs.class)
                        .putExtra("1",position);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.mm.dd HH:mm");
        Date date = new Date(System.currentTimeMillis());
        time = format.format(date);
    }


    private void getyuzhi() {

        /**
         *     "ROWS_DETAIL": [
         *         {
         *             "temperature": 20,
         *             "humidity": 10,
         *             "illumination": 1000,
         *             "co2": 2000,
         *             "pm25": 50,
         *             "path": 3
         */

        new OKHttpTo()
                .setUrl("get_threshold")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OKHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hjzb = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").optJSONObject(0)
                        .toString(),HJZB.class);
                        getOkhttp();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getOkhttp() {
        /**
         * {"UserName":"user1","RoadId":"1"}
         */
        new OKHttpTo()
                .setUrl("get_road_status")
                .setJsonObject("UserName","user1")
                .setJsonObject("RoadId",1)
                .setIsLoop(true)
                .setTime(3000)
                .setOkHttpLo(new OKHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                        int daolu = jsonObject1.optInt("state");
                        get_all_sense(daolu);
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void get_all_sense(final int daolu) {
        new OKHttpTo()
                .setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OKHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        HJZB hjzb = new Gson().fromJson(jsonObject.toString(),HJZB.class);
                        hjzb.setRoadId(daolu);
                        hjzb.setTime(time);
                        hjzbList.add(hjzb);
                        if (hjzbList.size()==21){
                            hjzbList.remove(0);
                        }
                        showList();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showList() {
        adapter = new X_hjzb_adapter(hjzb,hjzbList);
        gridview.setAdapter(adapter);
    }

    private void initView(View view) {
        gridview = view.findViewById(R.id.gridview);
        title = getActivity().findViewById(R.id.title);
    }

}
