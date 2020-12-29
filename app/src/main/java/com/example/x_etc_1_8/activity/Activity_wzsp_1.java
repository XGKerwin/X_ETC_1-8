package com.example.x_etc_1_8.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_1_8.R;

public class Activity_wzsp_1 extends AppCompatActivity {

    private VideoView video;
    private int pos;
    private ImageView caidan;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wzsp_1);
        initView();
        title.setText("违章视频");
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pos = getIntent().getIntExtra("1", 0);
        Log.d("ssssss", "onCreate: " + pos);

        switch (pos){
            case 0:
                video.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.car1));
                video.start();
                break;
            case 1:
                video.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.car2));
                video.start();
                break;
            case 2:
                video.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.car3));
                video.start();
                break;
            case 3:
                video.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.car4));
                video.start();
                break;
            case 4:
                video.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.car1));
                video.start();
                break;

        }


    }

    private void initView() {
        video = findViewById(R.id.video);
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}