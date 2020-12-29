package com.example.x_etc_1_8;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_etc_1_8.fragment.Fragment_clwz;
import com.example.x_etc_1_8.fragment.Fragment_cxgl;
import com.example.x_etc_1_8.fragment.Fragment_hjzb;
import com.example.x_etc_1_8.fragment.Fragment_hld;
import com.example.x_etc_1_8.fragment.Fragment_wdzh;
import com.example.x_etc_1_8.fragment.Fragment_yzsz;
import com.example.x_etc_1_8.fragment.Fragment_zdgl;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ImageView caidan;
    private NavigationView nav;
    private DrawerLayout dra;
    private TextView title;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        title.setText("智能交通");

        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dra.openDrawer(GravityCompat.START);
                nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.wdzh:
                                Fragment1(new Fragment_wdzh());
                                break;
                            case R.id.hldgl:
                                Fragment1(new Fragment_hld());
                                break;
                            case R.id.zdgl:
                                Fragment1(new Fragment_zdgl());
                                break;
                            case R.id.clwz:
                                Fragment1(new Fragment_clwz());
                                break;
                            case R.id.hjzb:
                                Fragment1(new Fragment_hjzb());
                                break;
                            case R.id.yzsz:
                                Fragment1(new Fragment_yzsz());
                                break;
                            case R.id.cxgl:
                                Fragment1(new Fragment_cxgl());
                                break;
                        }
                        dra.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });
            }
        });

    }

    private void Fragment1(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment).commit();
    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        nav = findViewById(R.id.nav);
        dra = findViewById(R.id.dra);
        title = findViewById(R.id.title);
    }
}