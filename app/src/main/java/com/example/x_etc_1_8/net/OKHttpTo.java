package com.example.x_etc_1_8.net;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpTo extends Thread{

    private String Url = "http://192.168.155.205:8080/traffic/";
    private JSONObject jsonObject = new JSONObject();
    private int Time;
    private boolean isLoop;
    private OKHttpLo okHttpLo;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (message.what == 1){
                okHttpLo.onResponse((JSONObject) message.obj);
            }else if (message.what == 2){
                okHttpLo.onFailure((IOException) message.obj);
            }
            return false;
        }
    });


    public OKHttpTo setUrl(String url){
        Url += url;
        return this;
    }
    public OKHttpTo setJsonObject(String k,Object v){
        try {
            jsonObject.put(k,v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }
    public OKHttpTo setTime(int time){
        Time = time;
        return this;
    }
    public OKHttpTo setIsLoop(boolean isLoop){
        this.isLoop = isLoop;
        return this;
    }
    public OKHttpTo setOkHttpLo(OKHttpLo okHttpLo){
        this.okHttpLo = okHttpLo;
        return this;
    }

    @Override
    public void run() {
        super.run();
        do {
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(MediaType.get("application/json;charset=utf-8"),jsonObject.toString());
            Request request = new Request.Builder()
                    .url(Url)
                    .post(requestBody)
                    .build();
            okHttpClient.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Message message = new Message();
                            message.what = 2;
                            message.obj = e;
                            handler.sendMessage(message);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            try {
                                Message message = new Message();
                                message.what = 1;
                                message.obj = new JSONObject(response.body().string());
                                handler.sendMessage(message);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            try {
                Thread.sleep(Time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (isLoop);

    }
}
