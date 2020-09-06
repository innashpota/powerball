package com.inna.shpota.powerball;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class TicketTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldCreate() {
        Balls balls = getBalls();
        String owner = "Sam Jayson";
        Ticket ticket = new Ticket(owner, balls);

        assertEquals(balls, ticket.getBalls());
        assertEquals(owner, ticket.getOwner());
    }

    @Test
    public void shouldFailToConstructGivenEmptyOwner() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Owner must not be empty");

        Balls balls = getBalls();
        String owner = null;
        new Ticket(owner, balls);
    }

    @Test
    public void shouldFailToConstructGivenEmptyBalls() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Balls must not be empty");

        Balls balls = null;
        String owner = "Sam Jayson";
        new Ticket(owner, balls);
    }

    private Balls getBalls() {
        List<Integer> whiteBalls = asList(1, 5, 23, 36, 59);
        int powerBall = 25;
        return new Balls(whiteBalls, powerBall);
    }
}