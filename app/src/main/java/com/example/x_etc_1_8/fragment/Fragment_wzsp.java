package com.example.x_etc_1_8.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_1_8.R;
import com.example.x_etc_1_8.adapter.X_wzsp1_adapter;
import com.example.x_etc_1_8.activity.Activity_wzsp_1;

public class Fragment_wzsp extends Fragment {
    private GridView gridview;
    private X_wzsp1_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_clwz_1, null);
        initView(view);

        adapter = new X_wzsp1_adapter();
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), Activity_wzsp_1.class)
                        .putExtra("1",position);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initView(View view) {
        gridview = view.findViewById(R.id.gridview);
    }
}
