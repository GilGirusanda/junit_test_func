package com.mycomp.app;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class AppTest 
{
    App app;
    double deltaArgument = 0.004;
    double deltaFunc = 1e-9;
    double rangeBegin = 0d;
    double rangeEnd = 3d;
    double deltaCountSteps = 1e-9;

    @Before
    public void setUp()
    {
        app = new App();
    }

    @Test
    public void testFuncNegative()
    {
        assertEquals(1, app.func(0-1e-12), deltaFunc);
    }

    @Test
    public void testFuncZero()
    {
        assertEquals(1, app.func(0), deltaFunc);
    }

    @Test
    public void testFuncPositiveButLessThanZeroComaSeven()
    {
        assertEquals(1, app.func(0.7-1e-12), deltaFunc);
    }

    @Test
    public void testFuncEqualsZeroComaSeven()
    {
        assertEquals(1, app.func(0.7), deltaFunc);
    }

    // Steps counter
    @Test
    public void testCountStepsIntsPos()
    {
        assertEquals(100, app.countSteps(0,100, 1), deltaCountSteps);
    }

    @Test
    public void testCountStepsIntsNeg()
    {
        assertEquals(100, app.countSteps(0,-100, -1), deltaCountSteps);
    }

    @Test
    public void testCountStepsDoublesPos()
    {
        assertEquals(100, app.countSteps(0,1, 1e2), deltaCountSteps);
    }

    @Test
    public void testCountStepsDoublesNeg()
    {
        assertEquals(100, app.countSteps(0,-1, -1e2), deltaCountSteps);
    }

    // Arr of values
    @Test
    public void testGenValues()
    {
        double[][] expected = {
                {0.0, 1.0},
                {0.004, 1.0},
                {0.008, 1.0},
                {0.012, 1.0},
                {0.016, 1.0},
                {0.02, 1.0}
        };

        double[][] expected2 = {
                {2.992, 0.21407261363552632},
                {2.996, 0.21416523363573303},
                {3.0, 0.21424294983005993}
        };

        double[][] expected3 = {
                {0.704, 0.08697488929119325},
                {1.24, -0.1653776286495077}
        };

        double[][] result = app.genValues(rangeBegin, rangeEnd, deltaArgument);

        double[][] cutResult = Arrays.stream(result).limit(expected.length).toArray(double[][]::new);
        double[][] cutResult2 = Arrays.stream(result).skip(result.length-3).toArray(double[][]::new);
        double[][] cutResult3 = Arrays.stream(result).filter(e->e[0]==0.704||e[0]==1.24).toArray(double[][]::new);

        assertEquals(expected , cutResult);
        assertEquals(expected2 , cutResult2);
        assertEquals(expected3 , cutResult3);
    }

    @Test
    public void testFindIndexOfMinAndMaxFuncValue()
    {
        double[][] result = app.genValues(rangeBegin, rangeEnd, deltaArgument);

        int indMax = app.findMaxIndex(result);
        assertEquals(174, indMax);

        int indMin = app.findMinIndex(result);
        assertEquals(362, indMin);
    }

    @Test
    public void testPrintMinMax()
    {
        double[][] result = app.genValues(rangeBegin, rangeEnd, deltaArgument);
        int indMax = app.findMaxIndex(result);
        int indMin = app.findMinIndex(result);
        app.printMinMax(indMin, indMax, result);
    }
}
