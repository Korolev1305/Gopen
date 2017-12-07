package com.example.ewigkeit.z_3a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static double get_func(double[]x,double[] b)
    {
        double a1=(x[0]*x[0]+x[1]+x[2]-b[0]);
        double a2=(x[0]+x[1]*x[1]+x[2]-b[1]);
        double a3=(x[0]+x[1]+x[2]*x[2]-b[2]);
        return a1*a1+a2*a2+a3*a3;
    }

    public static double[] koord_spusk(ArrayList<DataPoint>[] dataPoints){
        int n=0;
        double[] x_old = new double[3];
        x_old[0]=x_old[1]=x_old[2]=0;
        double[] b = new double[3];
        b[0]=b[1]=b[2]=1;
        double[] x = new double[3];
        x[0]=x[1]=x[2]=0;
        double a1=-10;
        double b1=10;
        double ct=0;
        double p = (1+Math.sqrt(5.0))/2.0;
        int i1=0;
        double x0;
        while(i1<3) {
            //n=0;
            int i = i1;
            double x1 = a1 + (b1 - a1) / 3;
            double x2 = b1 - (b1 - a1) / 3;
            x[i] = x1;
            double k1 = get_func(x, b);
            x[i] = x2;
            double k2 = get_func(x, b);
            if (k2 > k1) b1 = x2;
            else a1 = x1;
            x[i] = (a1 + b1) / 2;
                x1 = x[i];
            dataPoints[i].add(new DataPoint(n,x1));
            n++;

            for(int j =0;j<3;j++)
                x_old[j]=x[j];
            if (b1 - a1 < 1e-7) {
                a1 = -10;
                b1 = 10;
                i1++;
            }
        }
        for(int i=0;i<3;i++)
            System.out.println(x[i]);
        return x;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = (EditText) findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);
        final GraphView graph = (GraphView) findViewById(R.id.graph1);
        ArrayList<DataPoint>[] dataPoints = new ArrayList[3];
        for(int i=0;i<3;i++)
            dataPoints[i] = new ArrayList<DataPoint>();
        double[] x = koord_spusk(dataPoints);
        final DataPoint[][] dataPointsArr = new DataPoint[3][dataPoints[0].size()];
        for(int i=0;i<3;i++)
        for (int j=0;j<dataPoints[i].size();j++)
            dataPointsArr[i][j]=dataPoints[i].get(j);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graph.removeAllSeries();
                int k = Integer.parseInt(editText.getText().toString());
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPointsArr[k-1]);
                PointsGraphSeries<DataPoint> pointsGraphSeries = new PointsGraphSeries<>(dataPointsArr[k-1]);
                graph.addSeries(pointsGraphSeries);
                graph.addSeries(series);
            }
        });

    }
}
