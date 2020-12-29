package com.example.x_etc_1_8.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_1_8.R;
import com.example.x_etc_1_8.bean.Yuzhi;
import com.example.x_etc_1_8.net.OKHttpLo;
import com.example.x_etc_1_8.net.OKHttpTo;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_yzsz extends Fragment {

    private TextView txtKaiguan;
    private Switch Switch;
    private EditText edWendu;
    private EditText edShidu;
    private EditText edGuangzhao;
    private EditText edCo2;
    private EditText edPm;
    private EditText edDaolu;
    private Button btnBaocun;
    private boolean isLoop = false;
    private List<Yuzhi> yuzhiList;
    private String S_wendu,S_shidu,S_guangzhao,S_co2,S_pm;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            getData();
            return false;
        }
    });



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_yzsz, null);
        initView(view);
        getOkHttp();
        getkaiguan();
        btn();

        return view;
    }


    private void getData() {

        /**
         *     "temperature": 24,
         *     "humidity": 1,
         *     "illumination": 1633,
         *     "co2": 5232,
         *     "pm25": 20,
         */
        if (yuzhiList == null){
            yuzhiList = new ArrayList<>();
        }else {
            yuzhiList.clear();
        }
        new OKHttpTo()
                .setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OKHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        yuzhiList.add(new Gson().fromJson(jsonObject.toString(),Yuzhi.class));
                        setdata();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void setdata() {

    }

    private void btn() {
        btnBaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtKaiguan.getText().toString().equals("开")){
                    Toast.makeText(getContext(),"请关闭报警后设置",Toast.LENGTH_SHORT).show();
                }else {
                    if (edWendu.getText().toString().equals("")){
                        Toast.makeText(getContext(),"温度不能为空",Toast.LENGTH_SHORT).show();
                    }else if (edShidu.getText().toString().equals("")){
                        Toast.makeText(getContext(),"湿度不能为空",Toast.LENGTH_SHORT).show();
                    }else if (edGuangzhao.getText().toString().equals("")){
                        Toast.makeText(getContext(),"光照不能为空",Toast.LENGTH_SHORT).show();
                    }else if (edCo2.getText().toString().equals("")){
                        Toast.makeText(getContext(),"co2不能为空",Toast.LENGTH_SHORT).show();
                    }else if (edPm.getText().toString().equals("")){
                        Toast.makeText(getContext(),"pm2.5不能为空",Toast.LENGTH_SHORT).show();
                    }else if (edDaolu.getText().toString().equals("")){
                        Toast.makeText(getContext(),"道路状态不能为空",Toast.LENGTH_SHORT).show();
                    }else {
                        setOkHttp();
                    }
                }
            }
        });
    }

    private void setOkHttp() {
        /**
         * {"UserName":"user1",
         * "temperature":"30",
         * "humidity":"15",
         * "illumination":"100","
         * co2":"1000",
         * "pm25":"100",
         * "path":"7"}
         */

        new OKHttpTo()
                .setUrl("set_threshold")
                .setJsonObject("UserName","user1")
                .setJsonObject("temperature",edWendu.getText().toString())
                .setJsonObject("humidity",edShidu.getText().toString())
                .setJsonObject("illumination",edGuangzhao.getText().toString())
                .setJsonObject("co2",edCo2.getText().toString())
                .setJsonObject("pm25",edPm.getText().toString())
                .setJsonObject("path",edDaolu.getText().toString())
                .setOkHttpLo(new OKHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"设置成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();



    }

    private void getkaiguan() {
        Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    txtKaiguan.setText("开");
                    edWendu.setEnabled(false);
                    edShidu.setEnabled(false);
                    edGuangzhao.setEnabled(false);
                    edCo2.setEnabled(false);
                    edPm.setEnabled(false);
                    edDaolu.setEnabled(false);
                    isLoop = true;

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            do {
                                Log.d("ddddddd", "run: ");
                                handler.sendEmptyMessage(0);
                                try {
                                    Thread.sleep(10000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }while (isLoop);

                        }
                    }).start();

                }else {
                    isLoop = false;
                    txtKaiguan.setText("关");
                    edWendu.setEnabled(true);
                    edShidu.setEnabled(true);
                    edGuangzhao.setEnabled(true);
                    edCo2.setEnabled(true);
                    edPm.setEnabled(true);
                    edDaolu.setEnabled(true);
                }
            }
        });

    }

    private void getOkHttp() {
        /**
         *     "ROWS_DETAIL": [
         *         {
         *             "temperature": 20,
         *             "humidity": 10,
         *             "illumination": 1000,
         *             "co2": 2000,
         *             "pm25": 50,
         *             "path": 3
         *         }
         */

        new OKHttpTo()
                .setUrl("get_threshold")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OKHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                        S_wendu = jsonObject1.optString("temperature");
                        S_shidu = jsonObject1.optString("humidity");
                        S_guangzhao = jsonObject1.optString("illumination");
                        S_pm = jsonObject1.optString("pm25");
                        S_co2 = jsonObject1.optString("co2");
                        edDaolu.setText(jsonObject1.optString("path"));
                        edWendu.setText(S_wendu);
                        edShidu.setText(S_shidu);
                        edGuangzhao.setText(S_guangzhao);
                        edCo2.setText(S_co2);
                        edPm.setText(S_pm);
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        txtKaiguan = view.findViewById(R.id.txt_kaiguan);
        Switch = view.findViewById(R.id.Switch);
        edWendu = view.findViewById(R.id.ed_wendu);
        edShidu = view.findViewById(R.id.ed_shidu);
        edGuangzhao = view.findViewById(R.id.ed_guangzhao);
        edCo2 = view.findViewById(R.id.ed_co2);
        edPm = view.findViewById(R.id.ed_pm);
        edDaolu = view.findViewById(R.id.ed_daolu);
        btnBaocun = view.findViewById(R.id.btn_baocun);
    }
}
