package com.example.ewigkeit.z_3b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static void get_coef(ArrayList<Double[]> x,ArrayList<Double> b,ArrayList<Double[]> coef)
    {
        Double[] d = new Double[2];
        d[0]=0.0;
        d[1]=0.0;
        coef.set(0,d);
        ArrayList<ArrayList<Double>> p = new ArrayList<>();
        p.add(new ArrayList<Double>());
        p.add(new ArrayList<Double>());
        double k = x.size();
        Double z=0.0;
        for(int i=0;i<x.size();i++)
        {
            z+=x.get(i)[1]/x.size();
            b.set(0, z);
            p.get(0).add(1.0);
            p.get(1).add(1.0);
        }
        //System.out.println(b);
        for (int i=0;i<coef.size()-1;i++) {
            for (int j = 0; j < x.size(); j++) {
                coef.get(i+1)[0]+=x.get(j)[0]*p.get(0).get(j)*p.get(0).get(j);
                coef.get(i)[1]+=p.get(0).get(j)*p.get(0).get(j)/k;
                //System.out.println(coef.get(i+1)[0]+" "+coef.get(i)[1]);
            }
            k=coef.get(i)[1]*k;
            coef.get(i+1)[0]/=k;
            for(int j=0;j<x.size();j++)
            {
                p.get(1).set(j,(x.get(j)[0]-coef.get(i+1)[0])*p.get(0).get(j)
                        -(i!=0 ? coef.get(i)[1]*p.get(1).get(j):0));
            }
            double qv=0,v=0;
            for (int j=0;j<x.size();j++){
                v+=x.get(j)[1]*p.get(1).get(j);
                b.set(i+1,v);
                qv+=p.get(1).get(j)*p.get(1).get(j);
            }
            b.set(i+1,b.get(i+1)/qv);
            double[] sw = new double[p.get(1).size()];
            for (int j=0;j<sw.length;j++) {
                sw[j]=p.get(1).get(j);
            }
            for (int j=0;j<sw.length;j++) {
                p.get(1).set(j,p.get(0).get(j));
            }
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
        ArrayList<Double[]> x = new ArrayList<>();
        for(int i=0;i<22;i++) {
            b.add(0.0);
            coef.add(new Double[2]);
            coef.get(i)[0]=0.0;
            coef.get(i)[1]=0.0;
        }
        /*
        for(int i=0;i<coef.size();i++) {
            for (int j = 0; j < 2; j++)
                System.out.print(coef.get(i)[j]+" ");
            System.out.println();
        }*/
        for(double i=0;i<10;i=i+0.01)
            {
                Double[] z = new Double[2];
                z[0]=i;
                z[1]=get_func(i);
                x.add(z);
            }
        get_coef(x,b,coef);
        System.out.println(b);
        int k=0;
        GraphView graph = (GraphView) findViewById(R.id.graph1);
        ArrayList<DataPoint> dataPoints = new ArrayList();
        //ArrayList<DataPoint> dataPoints2 = new ArrayList();
        for(double i=0;i<50;i+=0.02)
        {
            if(i>k){
                System.out.println(i+" "+get_func(i)+" "+get_ans(i,b,coef));
                k++;
            }
            dataPoints.add(new DataPoint(i,get_func(i)));
            //dataPoints2.add(new DataPoint(i,get_ans(i,b,coef)));
        }
        DataPoint[] dataPointsArr = new DataPoint[dataPoints.size()];
        //DataPoint[] dataPointsArr2 = new DataPoint[dataPoints2.size()];
            for (int j=0;j<dataPoints.size();j++) {
                dataPointsArr[j] = dataPoints.get(j);
                //dataPointsArr2[j] = dataPoints2.get(j);
            }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPointsArr);
        //LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(dataPointsArr2);
        graph.addSeries(series);
        //graph.addSeries(series2);
        }
    }

