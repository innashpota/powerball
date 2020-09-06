package com.inna.shpota.powerball;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

public class Balls {
    private final List<Integer> whiteBalls;
    private final int powerBall;

    public Balls(List<Integer> whiteBalls, int powerBall) {
        validate(whiteBalls, powerBall);
        this.whiteBalls = unmodifiableList(whiteBalls);
        this.powerBall = powerBall;
    }

    public List<Integer> getWhiteBalls() {
        return whiteBalls;
    }

    public int getPowerBall() {
        return powerBall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balls balls = (Balls) o;
        return powerBall == balls.powerBall &&
                whiteBalls.equals(balls.whiteBalls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(whiteBalls, powerBall);
    }

    private void validate(List<Integer> whiteBalls, int powerBall) {
        if (whiteBalls == null || whiteBalls.isEmpty()) {
            throw new IllegalArgumentException("White balls must not be empty");
        }
        if (whiteBalls.size() != 5) {
            throw new IllegalArgumentException("White balls must have 5 number");
        }
        for (int ball : whiteBalls) {
            if (ball < 1 || ball > 69) {
                throw new IllegalArgumentException("White ball must be between 1 and 69");
            }
        }
        if (powerBall < 1 || powerBall > 26) {
            throw new IllegalArgumentException("Power ball must be between 1 and 26");
        }
    }
}
