package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static double[] solution(ArrayList<Double> b,int n,double[][] l,double[][] u){
        double[] solution = new double[n];
        double[] y = new double[n];
        for (int j = 0; j < n; j++) {
            y[j] = b.get(j);
            for (int i = 0; i < j; i++) {
                y[j] -= l[j][i] * y[i];
            }
        }
        for (int j = n - 1; j >= 0; j--) {
            solution[j] = y[j];
            for (int i = j + 1; i < n; i++) {
                solution[j] -= u[j][i] * solution[i];
            }
            solution[j] /= u[j][j];
        }
        return solution;
    }
    public static void LU (){
        Scanner in = new Scanner(System.in);
        int n;
        System.out.print("Введите количество строк массива: ");
        n = in.nextInt();
        double matrix[][] =new double[n][n];
        for (int i = 0; i < n; i++) {
            System.out.print("Введите "+i+" строку: ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextDouble();
            }
        }
        in.close();
        double[][] l = new double[n][n];
        double[][] u = new double[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
            {
                u[0][i]=matrix[0][i];
                l[i][0]=matrix[i][0]/u[0][0];
                double sum=0;
                for(int k=0;k<i;k++)
                {
                    sum+=l[i][k]*u[k][j];
                }
                u[i][j]=matrix[i][j]-sum;
                if(i>j)
                {
                    l[j][i]=0;
                }
                else
                {
                    sum=0;
                    for(int k=0;k<i;k++)
                    {
                        sum+=l[j][k]*u[k][i];
                    }
                    l[j][i]=(matrix[j][i]-sum)/u[i][i];
                }
            }
        }
        System.out.println("L:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(l[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("U:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(u[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();


        double[] solution=new double[n];
        double[][] inverseMatrix = new double[n][n];
        ArrayList<Double> b = new ArrayList<Double>();
        for (int i=0;i<n;i++) {
            b.clear();
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    b.add(1.0);
                }
                else {
                    b.add(0.0);
                }
            }
            solution = solution(b,n,l,u);
            for(int j=0;j<n;j++){
                inverseMatrix[j][i]=solution[j];
            }
        }
        System.out.println("Inverse:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(inverseMatrix[i][j]+" ");
            }
            System.out.println();
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                solution[j] += matrix[i][j] * b.get(i);
            }
        }
        System.out.println("Решение: ");
        for(int i=0;i<n;i++)
        {
            System.out.print(solution[i]+" ");
        }
        System.out.println();
    }
    static void pow_() {
        int result = 1;
        int i, j, k;
        int maxI = 0, maxJ = 0;
        double max, fi;
        double[][] coefficients, solution;
        double precision;
        int size;

        Scanner in = new Scanner(System.in);
        System.out.print("Введите точность ");
        precision=in.nextDouble();
        System.out.print("Введите количество строк массива: ");
        size = in.nextInt();
        solution = new double[size][size];
        coefficients = new double[size][size];
        for (i = 0; i < coefficients.length; i++) {
            System.out.print("Введите "+i+" строку: ");
            for (j = 0; j < coefficients[i].length; j++) {
                coefficients[i][j] = in.nextInt();
            }
        }
        in.close();

        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                solution[i][j] = 0;
            }
            solution[i][i] = 1;
        }
        double[][] matrixTurn = new double[size][size];
        double[][] temp = new double[size][size];
        double fault = 0.0;
        for (i = 0; i < size; i++) {
            for (j = i + 1; j < size; j++) {
                fault += coefficients[i][j] * coefficients[i][j];
            }
        }
        fault = Math.sqrt(2 * fault);
        while (fault > precision) {
            max = 0.0;
            for (i = 0; i < size; i++) {
                for (j = i + 1; j < size; j++) {
                    if (coefficients[i][j] > 0 && coefficients[i][j] > max) {
                        max = coefficients[i][j];
                        maxI = i;
                        maxJ = j;
                    } else if (coefficients[i][j] < 0 && Math.abs(coefficients[i][j]) > max) {
                        max = Math.abs(coefficients[i][j]);
                        maxI = i;
                        maxJ = j;
                    }
                }
            }
            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    matrixTurn[i][j] = 0;
                }
                matrixTurn[i][i] = 1;
            }
            if (coefficients[maxI][maxI] == coefficients[maxJ][maxJ]) {
                matrixTurn[maxI][maxI] = matrixTurn[maxJ][maxJ] =
                        matrixTurn[maxJ][maxI] = Math.sqrt(2.0) / 2.0;
                matrixTurn[maxI][maxJ] = -Math.sqrt(2.0) / 2.0;
            } else {
                fi = 0.5 * Math.atan((2.0 * coefficients[maxI][maxJ]) /
                        (coefficients[maxI][maxI] - coefficients[maxJ][maxJ]));
                matrixTurn[maxI][maxI] = matrixTurn[maxJ][maxJ] = Math.cos(fi);
                matrixTurn[maxI][maxJ] = -Math.sin(fi);
                matrixTurn[maxJ][maxI] = Math.sin(fi);
            }
            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    temp[i][j] = 0.0;
                }
            }
            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    for (k = 0; k < size; k++) {
                        temp[i][j] += matrixTurn[k][i] * coefficients[k][j];
                    }
                }
            }
            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    coefficients[i][j] = 0.0;
                }
            }
            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    for (k = 0; k < size; k++) {
                        coefficients[i][j] += temp[i][k] * matrixTurn[k][j];
                    }
                }
            }
            fault = 0.0;
            for (i = 0; i < size; i++) {
                for (j = i + 1; j < size; j++) {
                    fault += coefficients[i][j] * coefficients[i][j];
                }
            }
            fault = Math.sqrt(2 * fault);
            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    temp[i][j] = 0.0;
                }
            }
            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    for (k = 0; k < size; k++) {
                        temp[i][j] += solution[i][k] * matrixTurn[k][j];
                    }
                }
            }
            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    solution[i][j] = temp[i][j];
                }
            }
            result++;
        }


        System.out.println("Минимальное собственное значение: ");
        double min = coefficients[0][0];
        int minI=0;
        for(i =0;i<size;i++) {
            if (Math.abs(coefficients[i][i])<Math.abs(min)) {
                min=coefficients[i][i];
                minI=i;
            }

        }
        System.out.println(coefficients[minI][minI] + " ");
        System.out.println("Собственный вектор: ");
        for (j = 0; j < size; j++) {
            System.out.println(solution[j][minI] / solution[size-1][minI]  + " ");

        }
    }

    public static void squareRoot(){
        Scanner in = new Scanner(System.in);
        int n;
        System.out.print("Введите количество строк массива: ");
        n = in.nextInt();
        double matrix[][] =new double[n][n];
        double s[][] =new double[n][n];
        double x[]= new double[n];
        double y[]= new double[n];
        double b[]= new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s[i][j]=0;
            }
        }
        for (int i = 0; i < n; i++) {
            x[i]=0;
            y[i]=0;
        }
        for (int i = 0; i < n; i++) {
            System.out.print("Введите "+i+" строку: ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextDouble();
            }
        }
        System.out.print("Введиет массив b: ");
        for (int i = 0; i < n; i++) {
            b[i]=in.nextDouble();
        }
        in.close();
        double temp;
        for (int i=0;i<n;i++) {
            temp = 0;
            for (int k = 0; k < i; k++)
                temp += s[k][i]*s[k][i];
            s[i][i]=Math.sqrt(matrix[i][i]-temp);
            for (int j=i;j<n;j++){
                temp=0;
                for (int k=0;k<i;k++)
                    temp += s[k][i] * s[k][j];
                s[i][j] = (matrix[i][j] - temp) / s[i][i];
            }
        }

        System.out.println("Матрица LT");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(s[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println("Матрица L");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(s[j][i]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

        for (int i=0;i<n;i++){
            temp=0;
            for(int k=0;k<i;k++)
                temp+=s[k][i]*y[k];
            y[i]=(b[i]-temp)/s[i][i];

        }


        for (int i=0;i<n;i++) {
            System.out.print(y[i] + " ");
        }
        System.out.println();


        for (int i=n-1;i>=0;i--){
          temp=0;
          for(int k=i;k<n;k++)
              temp+=s[i][k]*x[k];
          x[i]=(y[i]-temp)/s[i][i];
        }


        for (int i=0;i<n;i++){
            System.out.println("x("+i+")="+x[i]);
        }
    }




    public static void pow(){
        Scanner in = new Scanner(System.in);
        int n;
        double precision;
        System.out.print("Введите количество строк массива: ");
        n = in.nextInt();
        System.out.print("Введите точность ");
        precision=in.nextDouble();
        double matrix[][] =new double[n][n];
        for (int i = 0; i < n; i++) {
            System.out.print("Введите "+i+" строку: ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextDouble();
            }
        }
        in.close();
        double[] x = new double[n];

        double[] y = new double[n];
        double eigan_max_value=0;
        double clarity_max=1;
        double[] eigan_value = new double[n];
        double[] eigan_value1 = new double[n];
        for (int i = 0; i < n; i ++){
            eigan_value[i] = 0.0;
            x[i] = 1.0 / n;
            y[i] = 1;
        }

        while (clarity_max > precision)
        {
            double norma=0;
            for (int i=0;i<n;i++)
            {
                y[i] = 0;
                for (int j = 0; j < n; j++)
                    y[i] += matrix[i][j]*x[j];
            }
            for (int i = 0; i < n; i ++){
                norma += y[i] * y[i];
            }
            for (int i = 0; i < n; i ++){
                eigan_value1[i] = y[i] / x[i];
            }
            for (int i=0;i<n;i++)
                x[i] = y[i] / norma;
            clarity_max = 0.0;
            for (int i = 0; i < n; i ++){
                if (clarity_max < Math.abs(eigan_value[i] - eigan_value1[i])){
                    clarity_max = Math.abs(eigan_value[i] - eigan_value1[i]);
                }
            }
            eigan_max_value = 0;
            for (int i = 0; i < n; i ++){
                eigan_value[i] = eigan_value1[i];
                eigan_max_value += eigan_value[i];
            }
            eigan_max_value /= n;

        }

        System.out.println(eigan_max_value);
        for (int i=0;i<n;i++)
        {
            System.out.print(x[i]/x[0]+" ");
        }
        System.out.println();

        System.out.println();

        for (int i=0;i<n;i++)
        {
            matrix[i][i]-=eigan_max_value;
        }
        y=new double[n];
        clarity_max=1;
        double[] x2= new double[n];

        double eigan_max_value2=0;
        eigan_value = new double[n];
        eigan_value1 = new double[n];
        for (int i = 0; i < n; i ++){
            eigan_value[i] = 0.0;
            x2[i] = 1.0 / n;
            y[i] = 1;
        }

        while (clarity_max > precision)
        {
            double norma=0;
            for (int i=0;i<n;i++)
            {
                y[i] = 0;
                for (int j = 0; j < n; j++)
                    y[i] += matrix[i] [j]*x2[j];
            }
            for (int i = 0; i < n; i ++){
                norma += y[i] * y[i];
            }
            for (int i = 0; i < n; i ++){
                eigan_value1[i] = y[i] / x2[i];
            }
            for (int i=0;i<n;i++)
                x2[i] = y[i] / norma;
            clarity_max = 0.0;
            for (int i = 0; i < n; i ++){
                if (clarity_max < Math.abs(eigan_value[i] - eigan_value1[i])){
                    clarity_max = Math.abs(eigan_value[i] - eigan_value1[i]);
                }
            }
            eigan_max_value2 = 0;
            for (int i = 0; i < n; i ++){
                eigan_value[i] = eigan_value1[i];
                eigan_max_value2 += eigan_value[i];
            }
            eigan_max_value2 /= n;

        }
        eigan_max_value2+=eigan_max_value;
        System.out.println("Минимальное собственное значение: ");
        System.out.println(eigan_max_value2);
        System.out.println("Собственный вектор: ");
        for (int i=0;i<n;i++)
        {
            System.out.print(x2[i]/x2[2]+" ");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n;
        System.out.print("Выберите задачу: 1 - LU,2 - squareRoot, 3 - pow  ");
        n = in.nextInt();
        switch (n) {
           case 1: LU();
           break;
            case 2:squareRoot();
            break;
            case 3:pow();
            break;
        }
        in.close();

    }
}
