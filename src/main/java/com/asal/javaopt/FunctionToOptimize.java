package com.asal.javaopt;

/**
 * @author A. Salamanis
 * @version 0.2
 * @since 2020-04-25
 *
 * FunctionToOptimize class: The class representing a function to be optimized.
 */
public class FunctionToOptimize {
    /**
     * Variable to distinguish functions from each other.
     */
    int type;

    /**
     * Constructor.
     * @param the type of the function
     */
    public FunctionToOptimize(int type) {
        this.type = type;
    }

    /**
     * Implements a mathematical multidimensional real-valued function optimized by an optimization algorithm.
     * @param x the input of the function
     * @return output of the function
     */
    double evaluate(double[] x) {
        double result = 0.0;
        if (type == 1) { // Sphere function
            for (int i = 0; i < x.length; ++i){
                result += Math.pow(x[i], 2);
            }
            return result;
        } else if (type == 2) { // Rosenbrock function
            for (int i = 0; i < (x.length - 1); ++i) {
                result += (100 * Math.pow(x[i + 1] - Math.pow(x[i], 2), 2) + Math.pow(1 - x[i], 2));
            }
            return result;
        }
        else { // TODO: Add more optimization test functions
            return -1.0;
        }
    }
}