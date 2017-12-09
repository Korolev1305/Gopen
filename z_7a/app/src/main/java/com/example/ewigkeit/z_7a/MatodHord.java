package com.example.ewigkeit.z_7a;

import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

/**
 * Created by ewigkeit on 06.12.2017.
 */

public class MatodHord {
    public static ArrayList<DataPoint> method_chord (double x_prev, double x_curr, double e) {
        double x_next = 0;
        double tmp;
        int n=0;
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        do{
            tmp = x_next;
            x_next = x_curr - f(x_curr) * (x_prev - x_curr) / (f(x_prev) - f(x_curr));
            x_prev = x_curr;
            x_curr = tmp;
            n++;
            dataPoints.add(new DataPoint(n,x_next));
        } while (Math.abs(x_next - x_curr) > e);
        return dataPoints;
    }

    public static double f (double x){
        return Math.pow(x, 2) - 5 * x + 6;
    }
}
