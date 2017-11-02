package com.company;

import java.util.Scanner;

public class Main {

    static boolean isSimmetrial(double[][] coefficients, int numberOfEquation) {
        boolean result = true;
        int i, j;
        for (i = 0; i < numberOfEquation; i++) {
            for (j = i + 1; j < numberOfEquation; j++) {
                if (coefficients[i][j] != coefficients[j][i]) {
                    result = false;
                    break;
                }
            }
            if (!result) break;
        }
        return result;
    }

    static void wrachenie() {
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
        System.out.println("Решение");
        double[] a = new double[size];
        double sqrt;
        for (i=0;i<a.length;i++)
        {
            sqrt=0;
            for (j=0;j<a.length;j++)
                sqrt+=solution[j][i]*solution[j][i];
            a[i]=Math.sqrt(sqrt);
        }
        for (i = 0; i < size; i++) {
            System.out.println("Собственный вектор k" + (i + 1) + ":");
            for (j = 0; j < size; j++) {
                System.out.println(solution[j][i] / a[i] + " ");
            }
        }
        System.out.println("Собственные значения:");
        for (i = 0; i < size; i++) {
            System.out.println(coefficients[i][i] + " ");
        }
        System.out.println("Количество шагов: ");
        System.out.println(result);
    }

    public static void main(String[] args) {
        int i, j;
        int size;

        /*
        if (!isSimmetrial(coefficients, size)) {
            System.out.println("Матрица не симметрична");
        } else {
            double min[] = new double[size];
            for (i = 0; i < size; i++)
                min[i] = 10000;
            int steps = wrachenie(coefficients, size, solution, precision);
            System.out.println("Решение");
            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    if (Math.abs(solution[j][i]) < min[i] && solution[j][i]!=0) min[i] = Math.abs(solution[j][i]);
                }
            }
            for (i = 0; i < size; i++) {
                System.out.println("Собственный вектор k" + (i + 1) + ":");
                for (j = 0; j < size; j++) {
                    System.out.println(solution[j][i] / min[i] + " ");
                }
            }
            System.out.println("Собственные значения:");
            for (i = 0; i < size; i++) {
                System.out.println(coefficients[i][i] + " ");
            }
            System.out.println(steps);
        }*/

        /*
        double min[] = new double[size];
        for (i = 0; i < size; i++)
            min[i] = 10000;

        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                if (Math.abs(solution[j][i]) < min[i] && solution[j][i]!=0) min[i] = Math.abs(solution[j][i]);
            }
        }
        */
        wrachenie();

    }
}
