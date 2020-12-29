package com.example.x_etc_1_8.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_1_8.R;

public class Activity_wztp_1 extends AppCompatActivity {

    private int[] arr = {R.drawable.weizhang1, R.drawable.weizhang2, R.drawable.weizhang8, R.drawable.weizhang9};
    private int pos = 0;
    private ImageView imageview;
    private ImageView caidan;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wztp_1);
        initView();

        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pos = getIntent().getIntExtra("1", 0);
        imageview.setImageResource(arr[pos]);

    }

    private void initView() {
        imageview = findViewById(R.id.imageview);
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
    }
}