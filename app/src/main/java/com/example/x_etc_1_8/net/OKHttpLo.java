package com.example.x_etc_1_8.net;

import org.json.JSONObject;

import java.io.IOException;

public interface OKHttpLo {
    void onResponse(JSONObject obj);

    void onFailure(IOException obj);
}
