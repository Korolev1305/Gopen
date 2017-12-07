package com.example.ewigkeit.krilov;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView matrixLength,eps;
    EditText TmatrixLength, Teps;
    Button createMatrix,go;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matrixLength = (TextView) findViewById(R.id.textView);
        eps = (TextView) findViewById(R.id.textView4);
        TmatrixLength = (EditText) findViewById(R.id.editText);
        Teps = (EditText) findViewById(R.id.editText2);
        createMatrix = (Button) findViewById(R.id.button2);
        go = (Button)findViewById(R.id.button3);
        createMatrix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridView = (GridView) findViewById(R.id.gridView1);
                try {
                    int n =Integer.parseInt(TmatrixLength.getText().toString());

                    System.out.println(n);
                    gridView.setNumColumns(n);

                    String[] matrix = new String[n*n];
                    for(int i=0; i<matrix.length;i++) {
                        matrix[i] = "0";
                    }
                    TextViewAdapter textViewAdapter = new TextViewAdapter(getWindow().getContext(),matrix);
                    gridView.setAdapter(textViewAdapter);
                } catch (NumberFormatException e) {
                    gridView.setNumColumns(0);
                }

            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double[][] matrix = new double[Integer.parseInt(TmatrixLength.getText().toString())][Integer.parseInt(TmatrixLength.getText().toString())];
                int k=0;
                for (int i=0;i<matrix.length;i++)
                    for(int j=0;j<matrix.length;j++)
                    {
                        matrix[i][j]=Double.parseDouble(gridView.getAdapter().getItem(k).toString());
                        k++;
                    }
                double eps = Double.parseDouble(Teps.getText().toString());

                ArrayList<Double> result=ChislMethodKrylov.krylov(eps,matrix);
                Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra("result",result);
                startActivity(intent);

            }
        });

    }
}
