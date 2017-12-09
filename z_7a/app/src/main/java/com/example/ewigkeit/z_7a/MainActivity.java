package com.example.ewigkeit.z_7a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

import static com.example.ewigkeit.z_7a.MatodHord.f;
import static com.example.ewigkeit.z_7a.MatodHord.method_chord;
import static com.example.ewigkeit.z_7a.MetodIteration.method_iteration;
import static com.example.ewigkeit.z_7a.MetodVegstein.method_vegstein;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b_h,b_v,b_i;
        b_h = (Button) findViewById(R.id.button_hord);
        b_v = (Button) findViewById(R.id.button_veg);
        b_i = (Button) findViewById(R.id.button_iter);
        final double x0 = -2;
        final double x1 = 10;
        final double e = 0.0001;
        final Intent intent = new Intent(MainActivity.this,GraphActivity.class);
        b_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<DataPoint> x_chord = method_chord(x0, x1, e);
                System.out.println(x_chord.get(x_chord.size()-1));
                intent.putExtra("data",x_chord);
                startActivity(intent);
            }
        });
        b_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<DataPoint> x_vegst=method_vegstein(x0,x1,e);
                System.out.println(x_vegst.get(x_vegst.size()-1));
                intent.putExtra("data",x_vegst);
                startActivity(intent);
            }
        });
        b_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<DataPoint> x_iter=method_iteration(x0,e);
                System.out.println(x_iter.get(x_iter.size()-1));
                intent.putExtra("data",x_iter);
                startActivity(intent);
            }
        });




    }


}
