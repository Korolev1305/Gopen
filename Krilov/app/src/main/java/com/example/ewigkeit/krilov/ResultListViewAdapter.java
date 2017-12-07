package com.example.ewigkeit.krilov;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ewigkeit on 22.11.2017.
 */

public class ResultListViewAdapter extends ArrayAdapter {
    Activity activity;
    int resource;
    List<Double> ownsNumbers;

    public ResultListViewAdapter(Context context, int resource, List<Double> objects){
        super(context, resource, objects);
        this.activity = (Activity) context;
        this.resource = resource;
        this.ownsNumbers = objects;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ResultListViewAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_item, null);
            viewHolder = new ResultListViewAdapter.ViewHolder();
            viewHolder.number = (TextView) convertView.findViewById(R.id.tv_number);
            viewHolder.result = (TextView) convertView.findViewById(R.id.tv_result);
            viewHolder.number.setText("Собственное значение "+(position+1)+":");
            viewHolder.result.setText("     "+ ownsNumbers.get(position).toString());

        }
        return convertView;

    }
    class ViewHolder{
        TextView number;
        TextView result;
    }
}
