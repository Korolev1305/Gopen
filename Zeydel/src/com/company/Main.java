package com.company;

import java.util.Scanner;

public class Main {

        public static void main(String[] args) {




            // Будем хранить матрицу в векторе, состоящем из
            // векторов вещественных чисел
            /*double[][] matrix = {
                    {13.2, 1.9, 2.3, 5.12},
                    {0.8, -7.3, -0.7, 5.2},
                    {0.5, -1.4, -9.6, 1.5}};
                    */

            // Матрица будет иметь размер (size) x (size + 1),
            // c учетом столбца свободных членов
            Scanner in = new Scanner(System.in);
            System.out.print("Введите точность ");
            double eps=in.nextDouble();
            System.out.print("Введите количество строк массива: ");
            int size = in.nextInt();
            double[][] matrix = new double[size][size+1];
            for (int i = 0; i < matrix.length; i++) {
                System.out.print("Введите "+i+" строку и свободный член: ");
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = in.nextDouble();
                }
            }
            in.close();
            // Введем вектор значений неизвестных на предыдущей итерации,
            // размер которого равен числу строк в матрице, т.е. size,
            // причем согласно методу изначально заполняем его нулями
            double[] previousVariableValues = new double[size];
            for (int i = 0; i < size; i++) {
                previousVariableValues[i] = 0.0;
            }
            // Будем выполнять итерационный процесс до тех пор,
            // пока не будет достигнута необходимая точность
            while (true) {
            // Введем вектор значений неизвестных на текущем шаге
            double[] currentVariableValues = new double[size];
            // Посчитаем значения неизвестных на текущей итерации
            // в соответствии с теоретическими формулами
            for (int i = 0; i < size; i++) {
                // Инициализируем i-ую неизвестную значением
                // свободного члена i-ой строки матрицы
                currentVariableValues[i] = matrix[i][size];
                // Вычитаем сумму по всем отличным от i-ой неизвестным
                for (int j = 0; j < size; j++) {
                    // При j < i можем использовать уже посчитанные
                    // на этой итерации значения неизвестных
                    if (j < i) {
                        currentVariableValues[i] -=  matrix[i][j] * currentVariableValues[j];
                    }
                    // При j > i используем значения с прошлой итерации
                    if (j > i) {
                        currentVariableValues[i] -= matrix[i][j] * previousVariableValues[j];
                    }
                }

                // Делим на коэффициент при i-ой неизвестной
                currentVariableValues[i] /= matrix[i][i];
            }
                for (int i = 0; i < size; i++) {
                    System.out.print(currentVariableValues[i] + " ");
                }
            // Посчитаем текущую погрешность относительно предыдущей итерации
            double[] error = new double[size];

            for (int i = 0; i < size; i++) {
                error[i] = Math.abs(currentVariableValues[i] - previousVariableValues[i]);
            }


            double max=-1;
                for (int i = 0; i < size; i++)
                {
                    if (error[i]>max) max  = error[i];
                }
                System.out.println();

                System.out.println(" max "+max+" ");
                System.out.println();
                // Если необходимая точность достигнута, то завершаем процесс
            if (max < eps) {

                break;
            }
                // Переходим к следующей итерации, так
                // что текущие значения неизвестных
                // становятся значениями на предыдущей итерации
                previousVariableValues = currentVariableValues;
        }
            // Выводим найденные значения неизвестных
            for (int i = 0; i < size; i++) {
                System.out.print(previousVariableValues[i] + " ");
            }
            System.out.println();
        }
    }




