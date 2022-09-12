package com.asal.javaopt;
/**
 * @author A. Salamanis
 * @version 0.2
 * @since 2020-04-25
 *
 * DistanceCalculator class: A class implementing a set of distance functions between particles.
 */
public class DistanceCalculator {
    double[] firstVector;
    double[] secondVector;

    /**
     * Constructor.
     */
    public DistanceCalculator(double[] firstVector, double[] secondVector) {
        this.firstVector = firstVector;
        this.secondVector = secondVector;
    }

    /**
     * Computes the Euclidean distance between two vectors.
     * @return the Euclidean distance.
     */
    public double euclideanDistance() {
        double dist = 0.0;
        if (firstVector.length == secondVector.length) {
            for (int i = 0; i < firstVector.length; ++i) {
                dist += Math.pow(firstVector[i] - secondVector[i], 2);
            }
            dist = Math.sqrt(dist);
            return dist;
        } else {
        	return -1.0; // error value
        }
    }
}