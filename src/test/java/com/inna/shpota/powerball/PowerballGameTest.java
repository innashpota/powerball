package com.inna.shpota.powerball;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

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
        Map<String, Long> totalTicketsByMatch = new HashMap<>();
        totalTicketsByMatch.put("2 + 1", 2L);
        totalTicketsByMatch.put("4 + 0", 1L);
        totalTicketsByMatch.put("4 + 1", 1L);

        Result result = game.result();

        assertNotNull(result);
        assertEquals(4, result.getWinners().size());
        assertEquals(winningBalls, result.getWinningBalls());
        assertEquals(totalTicketsByMatch, result.getTotalTicketsByMatch());
        assertEquals(50114, result.getTotalPrize());
    }

    @Test
    public void shouldCheckProbability() {
        Balls winningBalls = new BallsCreator().create();
        List<Ticket> randomTickets = getRandomTickets();
        PowerballGame game = new PowerballGame(randomTickets, winningBalls);

        Result result = game.result();

        Map<String, Long> totalTicketsByMatch = result.getTotalTicketsByMatch();
        Set<String> matches = totalTicketsByMatch.keySet();
        matches
                .forEach(match -> {
                    Long totalTicketByMatch = totalTicketsByMatch.get(match);
                    float experimentalProbability = (float) totalTicketByMatch / randomTickets.size();
                    assertTrue(
                            experimentalProbability - getTheoreticalProbability(match) < (float) 1 / 1000
                    );
                });
    }

    private float getTheoreticalProbability(String match) {
        float theoreticalProbability = 0;
        if ("0 + 1".equals(match)) {
            theoreticalProbability = (float) (1 / 38.32);
        } else if ("1 + 1".equals(match)) {
            theoreticalProbability = (float) (1 / 91.98);
        } else if ("2 + 1".equals(match)) {
            theoreticalProbability = (float) (1 / 701.33);
        } else if ("3 + 0".equals(match)) {
            theoreticalProbability = (float) (1 / 579.76);
        } else if ("3 + 1".equals(match)) {
            theoreticalProbability = (float) (1 / 14494.11);
        } else if ("4 + 0".equals(match)) {
            theoreticalProbability = (float) (1 / 36525.17);
        } else if ("4 + 1".equals(match)) {
            theoreticalProbability = (float) (1 / 913129.18);
        } else if ("5 + 0".equals(match)) {
            theoreticalProbability = (float) (1 / 11688053.52);
        } else if ("5 + 1".equals(match)) {
            theoreticalProbability = (float) 1 / 292201338;
        }
        return theoreticalProbability;
    }

    private List<Ticket> getRandomTickets() {
        List<Ticket> tickets = new ArrayList<>();
        String owner = "Sam Jayson";
        for (int i = 0; i < 1000000; i++) {
            Balls balls = new BallsCreator().create();
            Ticket ticket = new Ticket(owner + balls.toString(), balls);
            tickets.add(ticket);
        }
        return tickets;
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