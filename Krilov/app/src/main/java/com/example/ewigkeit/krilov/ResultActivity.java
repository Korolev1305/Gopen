package com.example.ewigkeit.krilov;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ewigkeit on 22.11.2017.
 */

public class ResultActivity extends AppCompatActivity{
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        listView = (ListView) findViewById(R.id.list_view1);
        Intent intent = getIntent();
        ArrayList<Double> result = (ArrayList<Double>) getIntent().getSerializableExtra("result");
        ResultListViewAdapter resultListViewAdapter = new ResultListViewAdapter(getWindow().getContext(),0,result);
        listView.setAdapter(resultListViewAdapter);
    }

}
