package com.example.ewigkeit.z_7b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class MainActivity extends AppCompatActivity {

    public static double get_func(double x) {
        return (Math.sin(x) + 3) * 100;
    }

    public static ArrayList<Double> get_ans(ArrayList<Double> x, ArrayList<Double> y) {
        ArrayList<Double> h = new ArrayList<>();
        ArrayList<Double> a = new ArrayList<>();
        ArrayList<Double> b = new ArrayList<>();
        ArrayList<Double> c = new ArrayList<>();
        ArrayList<Double> s = new ArrayList<>();
        for (int i = 0; i < x.size() - 1; i++) {
            h.add(x.get(i + 1) - x.get(i));
        }
        b.add(0.0);
        for (int i = 1; i <= x.size() - 1; i++) {
            if (h.get(i-1) != 0)
                b.add(2 * (y.get(i) - y.get(i - 1)) / h.get(i - 1) - b.get(i - 1));
            else break;
        }
        for (int j = 0; j <= x.size() - 1; j++)
            a.add(y.get(j));
        for (int j = 0; j <= x.size() - 2; j++) {
            c.add((y.get(j+1) - y.get(j)) / Math.pow(h.get(j), 2) - b.get(j) / h.get(j));
        }
        for(int i=0;i<x.size();i++)
        s.add(0.0);
        s.set(0,a.get(0));
        for (int i = 0; i < x.size() - 1; i++)
            s.set(i+1,(a.get(i) + h.get(i) * b.get(i) + Math.pow(h.get(i), 2) * c.get(i)));

        return s;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GraphView graphView = (GraphView) findViewById(R.id.graph1);
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        ArrayList<DataPoint> dataPoints2 = new ArrayList<>();
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        ArrayList<Double> s = new ArrayList<>();
        for(double i=0;i<10;i+=0.02)
        {
            x.add(i);
            y.add(get_func(i));
        }
        for (int i = 0; i < x.size(); i++)
            System.out.println(x.get(i) + " " + y.get(i));
        //s = get_ans(x, y);
        for (int i = 0; i < x.size(); i++) {
            System.out.println(x.get(i) + " " + y.get(i) + " " + s.get(i));


            dataPoints.add(new DataPoint(x.get(i), y.get(i)));
            dataPoints2.add(new DataPoint(x.get(i), s.get(i)));
        }
        DataPoint[] dataPointsArr = new DataPoint[dataPoints.size()];
        DataPoint[] dataPointsArr2 = new DataPoint[dataPoints2.size()];
        for (int j = 0; j < dataPoints.size(); j++) {
            dataPointsArr[j] = dataPoints.get(j);
        }
        for (int j = 0; j < dataPoints2.size(); j++) {
            dataPointsArr2[j] = dataPoints2.get(j);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPointsArr);
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(dataPointsArr2);
        graphView.addSeries(series);
        graphView.addSeries(series2);
    }
}
