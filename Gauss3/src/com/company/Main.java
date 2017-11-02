package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	int i,j,m,k;
	double max,temp;
        Scanner in = new Scanner(System.in);
        int n;
        System.out.print("Введите количество строк массива: ");
        n = in.nextInt();
        double matrix[][] =new double[n][n+1];
        for (i = 0; i < n; i++) {
            System.out.print("Введите "+i+" строку: ");
            for (j = 0; j < n+1; j++) {
                matrix[i][j] = in.nextDouble();
            }
        }
	double[] x = new double[n+1];
	for (k=0;k<n;k++){
	    max = Math.abs(matrix[k][k]);
	    i=k;
	    for (m=k+1;m<n;m++){
	        if (Math.abs(matrix[m][k])>max) {
                i = m;
                max = Math.abs(matrix[m][k]);
            }
        }
        if (max == 0) {
	        System.out.println("Система не имеет решений");
        }
        if (i != k){
	        for (j=k;j<n+1;j++){
	            temp = matrix[k][j];
	            matrix[k][j] = matrix [i][j];
	            matrix[i][j] = temp;
            }
        }
        max =  matrix[k][k];
        for (j=k+1;j<n+1;j++){
            matrix[k][j] /= max;
        }
        for (i=k+1;i<n;i++){
            temp = matrix[i][k];
            matrix[i][k] = 0;
            if (temp != 0 ){
                for (j=k+1;j<n+1;j++){
                    matrix[i][j] -= temp*matrix[k][j];
                }
            }
        }
    }
    for (i=n-1;i>=0;i--){
	    x[i]=0;
	    max=matrix[i][n];
	    for(j=n;j>i;j--){
	        max-=matrix[i][j]*x[j];
	        x[i]=max;
        }
    }
    System.out.println("Решение системы");
        for (i=0;i<n;i++){
            System.out.println("x["+(i+1)+"]="+x[i]);
        }
    }
}
