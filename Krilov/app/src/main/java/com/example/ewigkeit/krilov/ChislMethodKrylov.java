package com.example.ewigkeit.krilov;

import java.util.ArrayList;

/**
 * Created by ewigkeit on 22.11.2017.
 */

public class ChislMethodKrylov {

    static double[][] arrMult(double[][] Arr, double[][] ArrO, int m, int n, int q) //Функция перемножения матриц
    {
        double[][] MultArr;
        //Динамически создаем матрицу - результат произведения
        MultArr = new double[m][];
        for (int i = 0; i < m; i++) {
            MultArr[i] = new double[q];
        }
        ;
        //Вычисляем произведение матриц
        for (int i = 0; i < m; i++)
            for (int j = 0; j < q; j++) {
                MultArr[i][j] = 0;
                for (int k = 0; k < n; k++)
                    MultArr[i][j] += Arr[i][k] * ArrO[k][j];
            }
        return MultArr;
    }

    static double[][] treugArr(double[][] Arr, int n) //Приведение матрицы к треугольному виду
    {
        //Приводим матрицу A к треугольному виду
        for (int i = 0; i < n; i++) {
            //Обнуляем необходимые элементы
            for (int j = i + 1; j < n; j++) {
                double koeff = Arr[j][i] / Arr[i][i]; //Коэффициент, на который будем домножать строку
                for (int k = 0; k < n + 1; k++)
                    Arr[j][k] = Arr[j][k] - Arr[i][k] * koeff;
            }
        }
        return Arr;
    }

    static double[][] checkArr(double[][] Arr){
        double[][] Arr2;
        for(int i=0;i<Arr.length;i++)
                if(Arr[i][i]==0) {
                    Arr2 = new double[Arr.length - 1][Arr.length];
                    for(int k=0;k<Arr.length;k++) {
                        if(k==i) continue;
                        for (int z = 0; z < Arr[0].length - 1; z++)
                            Arr2[k][z] = Arr[k][z];
                    }
                    Arr=new double[Arr2.length][Arr2[0].length];
                    for(int k=0;k<Arr.length;k++)
                        for(int z=0;z<Arr[0].length;z++)
                            Arr[k][z]=Arr2[k][z];
                }
        return Arr;
    }

    static double[] gSolve(double[][] Arr, int n) //Нахождение переменных в матрице, приведенной к треугольному виду
    {
        double[] X;
        X = new double[n];

        //Расчет неизвестных:
        if(Arr[n-2][n-2]!=0)
        X[n-2]=Arr[n-2][n-1]/Arr[n-2][n-2];
        else X[n-2]=0;
        for (int k=n-3;k>=0;k--)
        {
            X[k]=Arr[k][n-1];
            for (int z=k+1;z<=n-2;z++)
                X[k]-=Arr[k][z]*X[z];
            X[k]/=Arr[k][k];
        }

        return X;
    }

    static double halfDiv(double[] P, double eps,int n) //Метод половинного деления
    {
        double a = 0;
        double b = 0;
        double prec;
        double cent;
        double c;

        do {
            a--;
            b++;
            c =urTest4(a,b,P,n);
            //System.out.println(c+" "+!String.valueOf(c).substring(0,1).equals("-"));
        } while (c==0);
        do {
            prec = b - a;
            cent = (a + b) / 2;
            if (urTest4(a, cent, P,n) == 1) b = cent;
            else a = cent;
        } while (Math.abs(prec) > eps);
        return cent;
    }

    static double[] urDiv(double[] P, double a, int n) //Деление уравнения
    {
        P[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i + 1 == n)
                P[i] = -P[i] + a;
            else
                P[i] = -P[i] + P[i + 1] * a;
        }
        for (int i = 0; i < n; i++)
            if (i + 1 == n)
                P[i] = -1;
            else
                P[i] = -P[i + 1];

        return P;
    }

    static double urTest4(double a, double b, double[] P,int n) //Проверка знаков на концах отрезка
    {

        double r1=P[n] * Math.pow(a, n),r2=P[n]*Math.pow(b,n);
        for ( int k=n-1;k>=0;k--) {
            r1 -= P[k] * Math.pow(a, k);
            r2-= P[k]*Math.pow(b,k);
        }
        if (((r1<0)&&(r2>=0))||((r1>=0)&&(r2<0))) return 1;
        else return 0;
    }

    static ArrayList<Double> krylov(double eps,double[][] matrix)
    {
        //Блок нахождения значений C(0), C(1), C(2), C(3), C(4)
        //Создадим массив который будет хранить эти значения
        double[][] mC = new double[matrix.length][];
        for (int i = 0; i < matrix.length; i++)
            mC[i] = new double[matrix.length + 1];
        //В этом массиве за C(0) примем (1, 1, 1, 1)
        mC[0][0]=1;
        for (int i = 1; i < matrix.length; i++)
            mC[i][0] = 0;
        //Создадим массив c0, который будет хранить текущее значение C(1), C(2), C(3), C(4)
        double[][] c0 = new double[matrix.length][];
        for (int i = 0; i < matrix.length; i++)
            c0[i] = new double[1];
        for (int i = 0; i < matrix.length; i++) {
            //Заполним массив c0 текущим значением - C(i)
            for (int j = 0; j < matrix.length; j++)
                c0[j][0] = mC[j][i];
            //Для нахождения C(i+1) умножим массив A на с0
            double[][] tmpArr = arrMult(matrix, c0, matrix.length, matrix.length, 1);
            //Занесем полученные данные в массив mC
            for (int j = 0; j < matrix.length; j++)
                mC[j][i + 1] = tmpArr[j][0];
        }
        System.out.println("  ");
        for(int i=0;i<matrix.length;i++) {
            String s = "";
            for (int j = 0; j < matrix.length + 1; j++)
                s += mC[i][j] + " ";
            System.out.println(s);
        }
        //Запишем систему уравнений для нахождения P1, P2, P3, P4
        treugArr(mC, matrix.length);
        System.out.println("  ");
        for(int i=0;i<matrix.length;i++) {
            String s = "";
            for (int j = 0; j < matrix.length + 1; j++)
                s += mC[i][j] + " ";
            System.out.println(s);
        }
        System.out.println("  ");
        mC=checkArr(mC);
        double [] P = new double[mC.length + 1];
        System.out.println("  ");
        for(int i=0;i<mC.length;i++) {
            String s = "";
            for (int j = 0; j < mC.length + 1; j++)
                s += mC[i][j] + " ";
            System.out.println(s);
        }
        P = gSolve(mC, mC.length+1);

        P[mC.length] = 1;
        String s="";
        for (int i=0;i<matrix.length;i++)
            s+=P[i]+" ";
        System.out.println(s);
        double[]lambda = new double[mC.length];
        //Ищем значения О» методом половинного деления
        double a;
        for (int z = mC.length; z > 0; z--) {
            //Будем решать полученное уравнение методом половинного деления
            a = halfDiv(P, eps,mC.length);
            lambda[z - 1] = a;
            System.out.println(a);
            //Разделим уравнение на полученный корень, чтобы получить кубическое
            P = urDiv(P, a, z);
            if (z-1>0) {
                String str="";
                for (int i=mC.length;i>=0;i--)
                    str+=P[i]+"*l^"+i+" - ";
                str+=" = 0";
                System.out.println(str);
            }
        }

        ArrayList<Double> result = new ArrayList<Double>();
        for(int i=0;i<lambda.length;i++)
        {
            result.add(lambda[i]);
        }
        return result;
    }
}

