package com.example.ewigkeit.z_7a;

import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

/**
 * Created by ewigkeit on 06.12.2017.
 */

public class MetodVegstein {
    public static ArrayList<DataPoint> method_vegstein(double x_prev,double x_curr,double e)
    {
        int n=0;
        double x=0.0;
        double xa=x_prev,xb=x_curr;
        double b;
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        do{
            x = xb -(xb-f(xb))/(1-(f(xb)-f(xa))/(xb-xa));
            b=Math.abs(x-xb);
            xa=xb;
            xb=x;
            n++;
            dataPoints.add(new DataPoint(n,x));
        } while(b>e);
        System.out.println(n);
        return dataPoints;
    }
    public static double f (double x) {
        //return Math.pow(x, 3) - 18 * x - 83;
        //return 83/(Math.pow(x,2)-18);
        return -6 / (x - 5);
    }
}
