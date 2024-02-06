package com.mycomp.app;

import java.util.stream.IntStream;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    double func(double x) {
        double a = -0.5;
        double b = 2;

        if(x <= 0.7) return 1;
        if(x > 1.4) return Math.exp(a*x)*Math.cos(b*x);
        return a*x*x*Math.log(x);
    }

    double countSteps(double begin, double end, double h) {
        return (begin - end)/h;
    }

    double[][] genValues(double a, double b, double h){
        int size = Math.abs((int)countSteps(a, b, h))+1;

        double[][] arr = new double[size][2];
        for(int i = 0; i < size; i++) {
            double x = a+i*h;
            arr[i][0] = x;// X
            arr[i][1] = func(x);// Y
        }
        return arr;
    }

    public int findMaxIndex(double[][] array) {
        return IntStream.range(0, array.length)
                .reduce((i, j) -> (array[i][1] > array[j][1]) ? i : j)
                .orElse(-1);
    }

    public int findMinIndex(double[][] array) {
        return IntStream.range(0, array.length)
                .reduce((i, j) -> (array[i][1] < array[j][1]) ? i : j)
                .orElse(-1);
    }

    public void printMinMax(int indexMin, int indexMax, double[][] values) {
        System.out.println("Min: " + values[indexMin][1]);
        System.out.println("Max: " + values[indexMax][1]);
    }
}
