package com.example.x_etc_1_8.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class X_wdzh_adapter extends ArrayAdapter<String> {
    public X_wdzh_adapter(@NonNull Context context, String[] resource) {
        super(context, 0,resource);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = View.inflate(getContext(),android.R.layout.simple_list_item_1,null);
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(getItem(position));
        textView.setTextSize(25);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.LEFT);
        return view;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = View.inflate(getContext(),android.R.layout.simple_list_item_1,null);
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(getItem(position));
        textView.setTextSize(25);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.LEFT);
        return view;
    }
}
