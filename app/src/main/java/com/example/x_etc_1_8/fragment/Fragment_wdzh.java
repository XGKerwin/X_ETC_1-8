package com.example.x_etc_1_8.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_1_8.R;
import com.example.x_etc_1_8.Util.Tool;
import com.example.x_etc_1_8.adapter.X_wdzh_adapter;
import com.example.x_etc_1_8.bean.CZJL;
import com.example.x_etc_1_8.net.OKHttpLo;
import com.example.x_etc_1_8.net.OKHttpTo;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

public class Fragment_wdzh extends Fragment {

    private TextView txtYue;
    private Spinner spinner;
    private EditText editJine;
    private Button btnChaxun;
    private Button btnChongzhi;
    private X_wdzh_adapter adapter;
    private TextView title;
    private String[] arr = {"1","2","3"};
    private int pos = 1;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_wdzh, null);
        initView(view);

        yanchi();
        title.setText("我的账户");
        spinner1();
        edit();


        btn();
        return view;
    }

    private void yanchi() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("提示");
        progressDialog.setMessage("网络访问中");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
        getOkhttp();
    }

    private void btn() {
        btnChongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editJine.getText().toString().equals("")){
                    Toast.makeText(getContext(),"充值金额不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    setOkHttp();
                }
            }
        });
    }

    private String cph = "";
    private void setOkHttp() {
        /**
         * {"UserName":"user1","number":"1"}
         */
        new OKHttpTo()
                .setUrl("get_plate")
                .setJsonObject("UserName","user1")
                .setJsonObject("number",pos)
                .setOkHttpLo(new OKHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        cph = jsonObject.optString("plate");
                        Log.d("sssss", "onResponse: "+cph);
                        set_balance();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void set_balance() {
        /**
         * {"UserName":"user1","plate":"鲁A10001","balance":"100"}
         */
        new OKHttpTo()
                .setUrl("set_balance")
                .setJsonObject("UserName","user1")
                .setJsonObject("plate",cph)
                .setJsonObject("balance",Integer.parseInt(editJine.getText().toString()))
                .setOkHttpLo(new OKHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        CZJL czjl = new CZJL();
                        czjl.setNumber(pos+"");
                        czjl.setJine(editJine.getText().toString());
                        czjl.setCzr("admin");
                        czjl.setTime(Tool.getTime("yyyy.m.dd HH:mm",new Date()));
                        if (czjl.save()){
                            editJine.setText("");
                            Toast.makeText(getContext(),"充值成功",Toast.LENGTH_SHORT).show();
                            yanchi();
                        }else {
                            Toast.makeText(getContext(),"充值失败",Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void edit() {
        editJine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editJine.getText().toString().equals("0")){
                    editJine.setText("");
                    Toast.makeText(getContext(),"金额不能为0",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void spinner1() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position+1;
                getOkhttp();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        adapter = new X_wdzh_adapter(getContext(),arr);
        spinner.setAdapter(adapter);
    }

    private void getOkhttp() {

        /**
         * {"UserName":"user1","number":"1"}
         */
        Log.i("ddddddddd", "onResponse: "+pos);
        new OKHttpTo()
                .setUrl("get_balance_b")
                .setJsonObject("UserName","user1")
                .setJsonObject("number",pos)
                .setOkHttpLo(new OKHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.i("ddddddddd", "onResponse: "+jsonObject.optString("balance"));
                        txtYue.setText(jsonObject.optString("balance")+"元");
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void initView(View view) {
        txtYue = view.findViewById(R.id.txt_yue);
        spinner = view.findViewById(R.id.spinner);
        editJine = view.findViewById(R.id.edit_jine);
        btnChaxun = view.findViewById(R.id.btn_chaxun);
        btnChongzhi = view.findViewById(R.id.btn_chongzhi);
        title = getActivity().findViewById(R.id.title);
    }
}
