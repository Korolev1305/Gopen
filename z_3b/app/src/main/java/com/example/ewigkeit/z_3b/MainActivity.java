package com.example.ewigkeit.z_3b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static void get_coef(double[][] x,ArrayList<Double> b,ArrayList<Double[]> coef)
    {
        ArrayList<ArrayList<Double>> p = new ArrayList<>();
        p.add(new ArrayList<Double>());
        p.add(new ArrayList<Double>());
        double k = x.length;
        double z=0;
        for(int i=0;i<x.length;i++)
        {
            z+=x[i][1]/x.length;
            b.add(z);
            p.get(0).add(1.0);
            p.get(1).add(1.0);
        }
        for (int i=0;i<coef.size()-1;i++) {
            for (int j = 0; j < x.length; j++) {
                coef.get(i+1)[0]+=x[j][0]*p.get(0).get(j)*p.get(0).get(j);
                coef.get(i)[1]+=p.get(0).get(j)*p.get(0).get(j)/k;
            }
            k=coef.get(i)[1]*k;
            coef.get(i+1)[0]/=k;
            for(int j=0;j<x.length;j++)
            {
                p.get(1).set(j,(x[j][0]-coef.get(i)[0]*p.get(0).get(j)
                        -(i!=0 ? coef.get(i)[1]*p.get(1).get(j):0)));
            }
            double qv=0,v=0;
            for (int j=0;j<x.length;j++){
                v+=x[j][1]*p.get(1).get(j);
                b.set(i+1,v);
                qv+=p.get(1).get(j)*p.get(1).get(j);
            }
            b.set(i+1,b.get(i+1)/qv);
            double[] sw = new double[p.get(1).size()];
            for (int j=0;j<sw.length;j++) {
                sw[j]=p.get(1).get(j);
            }
            p.set(1,p.get(0));
            for (int j=0;j<sw.length;j++) {
                p.get(0).set(j,sw[j]);
            }
        }
    }
    public static double get_ans(double x,ArrayList<Double> b,ArrayList<Double[]> coef) {
        double[] ak = new double[2];
        ak[0]=ak[1]=0.0;
        double ans=0.0;
        ans+= b.get(0);
        ak[0]=1;
        for(int i=0;i<coef.size()-1;i++) {
            ak[1]=(x-coef.get(i+1)[0])*ak[0]-(i!=0?ak[1]*coef.get(i)[1]:0);
            ans+=b.get(i+1)*ak[1];
            double sw=ak[1];
            ak[1]=ak[0];
            ak[0]=sw;
        }
        return ans;
    }
    public static double get_func(double x)
    {
        return (Math.sin(x)+3)*100;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Double> b = new ArrayList<>();
        ArrayList<Double[]> coef = new ArrayList<>();
        double[][] x = new double[1000][2];
        for(int i=0;i<22;i++) {
            b.add(0.0);
            Double[] d = new Double[2];
            d[0] = 0.0;
            d[1] = 0.0;
            coef.add(d);
        }
        for(double i=0;i<10;i=i+0.01)
            {
                int i0=(int)i*100;
                x[i0][0]=i;
                x[i0][1]=get_func(i);
            }
        get_coef(x,b,coef);
        int k=0;
        GraphView graph = (GraphView) findViewById(R.id.graph1);
        ArrayList<DataPoint> dataPoints = new ArrayList();

        for(double i=0;i<50;i+=0.02)
        {
            if(i>k){
                k++;
            }
            dataPoints.add(new DataPoint(i,get_func(i)));
        }
        DataPoint[] dataPointsArr = new DataPoint[dataPoints.size()];
            for (int j=0;j<dataPoints.size();j++)
                dataPointsArr[j]=dataPoints.get(j);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPointsArr);
        graph.addSeries(series);
        }
    }

