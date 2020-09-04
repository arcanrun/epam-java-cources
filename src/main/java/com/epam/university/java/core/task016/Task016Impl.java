package com.epam.university.java.core.task016;

import java.util.ArrayList;
import java.util.Collection;

public class Task016Impl implements Task016 {
    @Override
    public Collection<Coordinate> getSquaresInsideCircle(int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException();
        }
        Collection<Coordinate> coordinates = new ArrayList<>();
        for (int x = 1; x < radius * 2; x++) {
            for (int y = 1; y < radius * 2; y++) {
                if (Math.pow(x, 2) + Math.pow(y, 2) < Math.pow(radius, 2) * 4) {
                    coordinates.add(new CoordinateImpl(x, y));
                    coordinates.add(new CoordinateImpl(x, -y));
                    coordinates.add(new CoordinateImpl(-x, y));
                    coordinates.add(new CoordinateImpl(-x, -y));
                }
            }
        }
        return coordinates;
    }
}
