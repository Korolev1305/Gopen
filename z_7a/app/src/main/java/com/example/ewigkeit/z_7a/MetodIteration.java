package com.example.ewigkeit.z_7a;

import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

/**
 * Created by ewigkeit on 06.12.2017.
 */

public class MetodIteration {
    public static ArrayList<DataPoint> method_iteration(double x_prev, double e) {
        double x = x_prev;
        double b, y = 0.0;
        int n = 0;
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        do {
            y = f(x);

            b = Math.abs(y - x);
            x = y;
            n++;
            dataPoints.add(new DataPoint(n,x));
        } while (b > e);
        System.out.println(n);
        return dataPoints;
    }

    public static double f(double x) {
        //return Math.pow(x, 3) - 18 * x - 83;
        //return 83/(Math.pow(x,2)-18);
        return -6 / (x - 5);
    }
}
