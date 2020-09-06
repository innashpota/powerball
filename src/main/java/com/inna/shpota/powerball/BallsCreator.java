package com.inna.shpota.powerball;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BallsCreator {

    public Balls randomBalls() {
        List<Integer> whiteBalls = new ArrayList<>(5);
        for (int i = 0; i < 69; i++) {
            int ball = new Random().nextInt(69) + 1;
            if (!whiteBalls.contains(ball)) {
                whiteBalls.add(ball);
            }
            if (whiteBalls.size() == 5) {
                break;
            }
        }
        int powerBall = new Random().nextInt(26) + 1;
        return new Balls(whiteBalls, powerBall);
    }
}
