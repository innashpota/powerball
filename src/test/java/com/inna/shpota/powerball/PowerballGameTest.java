package com.inna.shpota.powerball;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PowerballGameTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldFailToConstructGivenEmptyTickets() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Tickets must not be empty");

        List<Ticket> tickets = null;
        BallsCreator ballsCreator = new BallsCreator();
        new PowerballGame(tickets, ballsCreator.create());
    }

    @Test
    public void shouldFailToConstructGivenEmptyBalls() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Winning balls must not be empty");

        List<Ticket> tickets = getTickets();
        Balls balls = null;
        new PowerballGame(tickets, balls);
    }

    @Test
    public void shouldCheckResult() {
        List<Ticket> tickets = getTickets();
        Balls winningBalls = new Balls(asList(45, 3, 8, 34, 68), 13);
        PowerballGame game = new PowerballGame(tickets, winningBalls);
        Map<String, Long> totalPrizeByMatch = new HashMap<>();
        totalPrizeByMatch.put("2 + 1", 2L);
        totalPrizeByMatch.put("4 + 0", 1L);
        totalPrizeByMatch.put("4 + 1", 1L);

        Result result = game.result();

        assertNotNull(result);
        assertEquals(4, result.getWinners().size());
        assertEquals(winningBalls, result.getWinningBalls());
        assertEquals(totalPrizeByMatch, result.getTotalPrizeByMatch());
        assertEquals(50114, result.getTotalPrize());
    }

    private List<Ticket> getTickets() {
        return asList(
                getTicket(getFirstBalls()),
                getTicket(getSecondBalls()),
                getTicket(getThirdBalls()),
                getTicket(getFourthBalls()),
                getTicket(getFifthBalls())
        );
    }

    private Ticket getTicket(Balls balls) {
        String owner = "Sam Jayson";
        return new Ticket(owner, balls);
    }

    private Balls getFirstBalls() {
        List<Integer> balls = asList(1, 3, 5, 34, 67);
        return new Balls(balls, 13);
    }

    private Balls getSecondBalls() {
        List<Integer> balls = asList(45, 3, 25, 34, 68);
        return new Balls(balls, 16);
    }

    private Balls getThirdBalls() {
        List<Integer> balls = asList(45, 3, 25, 34, 8);
        return new Balls(balls, 13);
    }

    private Balls getFourthBalls() {
        List<Integer> balls = asList(45, 3, 5, 7, 67);
        return new Balls(balls, 13);
    }

    private Balls getFifthBalls() {
        List<Integer> balls = asList(2, 4, 5, 7, 67);
        return new Balls(balls, 25);
    }
}