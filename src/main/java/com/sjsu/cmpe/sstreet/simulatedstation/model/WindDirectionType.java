package com.sjsu.cmpe.sstreet.simulatedstation.model;

import java.util.Random;

public enum WindDirectionType {
    S, SE, SW, SSE, SSW, N, NE, NW, NNE, NNW, W, WNW, WSW, E, ENE, ESE;

    private static final WindDirectionType[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static WindDirectionType getRandomDirection()  {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
