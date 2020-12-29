package com.example.x_etc_1_8.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_etc_1_8.R;

public class Fragment_clwz extends Fragment {

    private TextView txtWzsp;
    private TextView txtWztp;
    private FragmentTransaction fragmentTransaction;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_clwz, null);
        initView(view);

        Fragment1(new Fragment_wzsp());

        btn();

        return view;
    }

    private void btn() {
        txtWzsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtWzsp.setBackgroundResource(R.drawable.txt_baise_hei1);
                txtWztp.setBackgroundResource(R.drawable.txt_huise_hei1);
                Fragment1(new Fragment_wzsp());
            }
        });

        txtWztp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtWztp.setBackgroundResource(R.drawable.txt_baise_hei1);
                txtWzsp.setBackgroundResource(R.drawable.txt_huise_hei1);
                Fragment1(new Fragment_wztp());
            }
        });

    }

    private void Fragment1(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment4,fragment).commit();
    }

    private void initView(View view) {
        txtWzsp = view.findViewById(R.id.txt_wzsp);
        txtWztp = view.findViewById(R.id.txt_wztp);
    }
}
