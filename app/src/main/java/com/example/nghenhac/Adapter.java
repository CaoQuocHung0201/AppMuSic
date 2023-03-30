package com.example.nghenhac;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private ArrayList<Song> arr;
    private Activity activity;
    public Adapter(Activity activity,ArrayList<Song> arr){
        this.activity=activity;
        this.arr=arr;
    }
    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        convertView=inflater.inflate(R.layout.row,null);
        TextView tvname=(TextView) convertView.findViewById(R.id.rowtenbaihat);
        tvname.setText(arr.get(i).getTilte());
        return convertView;
    }
}