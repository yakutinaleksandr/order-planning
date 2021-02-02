package com.example.orderplanning.util;

import com.example.orderplanning.model.Location;

public class DistanceUtil {

    /**
     * Method for calculation of squared distance between 2 points using Pythagoras theorem.
     * Squared distance is used also to detect nearest warehouse as far as if x^2>y^2, then |x|>|y|
     *
     * @param l1  first location
     * @param l2  second location
     * @return squared distance between locations
     */
    public static Double calculateSquaredDistance(final Location l1, final Location l2) {
        return Math.pow(l1.getX() - l2.getX(), 2) + Math.pow(l1.getY() - l2.getY(), 2);
    }
}
