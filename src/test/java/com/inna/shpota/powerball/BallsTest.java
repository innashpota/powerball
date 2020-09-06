package com.inna.shpota.powerball;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class BallsTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldCreate() {
        List<Integer> whiteBalls = asList(1, 5, 23, 36, 59);
        int powerBall = 25;
        Balls balls = new Balls(whiteBalls, powerBall);

        assertEquals(5, balls.getWhiteBalls().size());
        assertEquals(whiteBalls, balls.getWhiteBalls());
        assertEquals(powerBall, balls.getPowerBall());
    }

    @Test
    public void shouldFailToConstructGivenEmptyBalls() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("White balls must not be empty");

        List<Integer> whiteBalls = null;
        int powerBall = 25;
        new Balls(whiteBalls, powerBall);
    }

    @Test
    public void shouldFailToConstructGivenBallsWithWrongSize() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("White balls must have 5 number");

        List<Integer> whiteBalls = asList(1, 5, 23);
        int powerBall = 25;
        new Balls(whiteBalls, powerBall);
    }

    @Test
    public void shouldFailToConstructGivenBallsWithWrongElement() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("White ball must be between 1 and 69");

        List<Integer> whiteBalls = asList(1, 5, 156, 7, 11);
        int powerBall = 25;
        new Balls(whiteBalls, powerBall);
    }

    @Test
    public void shouldFailToConstructGivenWrongPB() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Power ball must be between 1 and 26");

        List<Integer> whiteBalls = asList(1, 5, 23, 36, 59);
        int powerBall = 888;
        new Balls(whiteBalls, powerBall);
    }
}