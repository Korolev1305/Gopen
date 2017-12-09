package com.example.ewigkeit.z_7a;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.LineGraphSeries;


import java.util.ArrayList;

import static com.example.ewigkeit.z_7a.MatodHord.method_chord;
import static com.example.ewigkeit.z_7a.MetodIteration.method_iteration;
import static com.example.ewigkeit.z_7a.MetodVegstein.method_vegstein;

/**
 * Created by ewigkeit on 06.12.2017.
 */

public class GraphActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        Intent intent = getIntent();
        ArrayList<DataPoint> dataPoints = (ArrayList<DataPoint>) getIntent().getSerializableExtra("data");
        DataPoint[] dataPointsArr = new DataPoint[dataPoints.size()];
        System.out.println(dataPoints);
        for (int i=0;i<dataPoints.size();i++)
            dataPointsArr[i]=dataPoints.get(i);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPointsArr);
        PointsGraphSeries<DataPoint> pointsGraphSeries = new PointsGraphSeries<>(dataPointsArr);
        graph.addSeries(pointsGraphSeries);
        graph.addSeries(series);
    }
}
