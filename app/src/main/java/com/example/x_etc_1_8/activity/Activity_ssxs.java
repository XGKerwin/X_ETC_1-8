package com.example.x_etc_1_8.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.x_etc_1_8.R;
import com.example.x_etc_1_8.bean.SSXS;
import com.example.x_etc_1_8.fragment.Fragment1;
import com.example.x_etc_1_8.fragment.Fragment2;
import com.example.x_etc_1_8.fragment.Fragment3;
import com.example.x_etc_1_8.fragment.Fragment4;
import com.example.x_etc_1_8.fragment.Fragment5;
import com.example.x_etc_1_8.net.OKHttpLo;
import com.example.x_etc_1_8.net.OKHttpTo;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Activity_ssxs extends AppCompatActivity {
    private int pos;
    private ViewPager viewpager;
    private LinearLayout lin;
    private List<SSXS> ssxsList;
    private List<Fragment> fragments;
    private ImageView caidan;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssxs);
        ssxsList = new ArrayList<>();

        pos = getIntent().getIntExtra("1", 0);

        getOkHttp();
        initView();
        getpos();
    }

    private void getOkHttp() {
        new OKHttpTo().setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setTime(3000)
                .setIsLoop(true)
                .setOkHttpLo(new OKHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        ssxsList.add(new Gson().fromJson(jsonObject.toString(),SSXS.class));
                        if (ssxsList.size() == 21){
                            ssxsList.remove(0);
                        }

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getpos() {
        fragments = new ArrayList<>();
        fragments.add(new Fragment1(ssxsList));
        fragments.add(new Fragment2(ssxsList));
        fragments.add(new Fragment3(ssxsList));
        fragments.add(new Fragment4(ssxsList));
        fragments.add(new Fragment5(ssxsList));
        viewpager.setAdapter(new Myfragmentadapter(getSupportFragmentManager()));

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i=0;i<lin.getChildCount();i++){
                    ImageView imageView = (ImageView) lin.getChildAt(i);
                    if (i == position){
                        imageView.setImageResource(R.drawable.yuan3);
                    }else {
                        imageView.setImageResource(R.drawable.yuan4);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        viewpager.setCurrentItem(pos);

        for (int i=0;i<fragments.size();i++){
            ImageView imageView = new ImageView(this);
            if (i == pos){
                imageView.setImageResource(R.drawable.yuan4);
            }else {
                imageView.setImageResource(R.drawable.yuan3);
            }
            imageView.setLayoutParams(new ViewGroup.LayoutParams(70,70));
            lin.addView(imageView);
        }

    }

    private void initView() {
        viewpager = findViewById(R.id.viewpager);
        lin = findViewById(R.id.lin);
        title = findViewById(R.id.title);
        title.setText("环境指标");
        caidan = findViewById(R.id.caidan);
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    class Myfragmentadapter extends FragmentPagerAdapter{


        public Myfragmentadapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}