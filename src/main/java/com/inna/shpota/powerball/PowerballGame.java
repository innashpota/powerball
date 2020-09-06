package com.inna.shpota.powerball;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PowerballGame {
    private final List<Ticket> tickets;
    private final Balls winningBalls;

    public PowerballGame(List<Ticket> tickets, Balls winningBalls) {
        if (tickets == null) {
            throw new IllegalArgumentException("Tickets must not be empty");
        }
        this.tickets = tickets;
        if (winningBalls == null) {
            throw new IllegalArgumentException("Winning balls must not be empty");
        }
        this.winningBalls = winningBalls;
    }

    public Result result() {
        List<Winner> winners = getWinners();
        Map<String, Long> totalTicketsByMatch = calculateTotalTicketsByMatch(winners);
        int totalPrize = calculateTotalPrize(winners);
        return new Result(winners, winningBalls, totalTicketsByMatch, totalPrize);
    }

    private List<Winner> getWinners() {
        List<Winner> winners = new ArrayList<>();
        for (Ticket ticket : tickets) {
            Balls balls = ticket.getBalls();
            List<Integer> whiteBalls = balls.getWhiteBalls();
            long countOfMatchedWhiteBalls = whiteBalls.stream()
                    .filter(whiteBall -> winningBalls.getWhiteBalls().contains(whiteBall))
                    .count();
            int powerBall = balls.getPowerBall();
            boolean isPowerBallMatched = winningBalls.getPowerBall() == powerBall;
            String match = countOfMatchedWhiteBalls + " + " + (isPowerBallMatched ? 1 : 0);
            int prize = calculatePrize(countOfMatchedWhiteBalls, isPowerBallMatched);
            if (prize != 0) {
                winners.add(new Winner(ticket, prize, match));
            }
        }
        return winners;
    }

    private Map<String, Long> calculateTotalTicketsByMatch(List<Winner> winners) {
        Map<String, Long> totalTicketsByMatch = new HashMap<>();
        winners.stream()
                .map(Winner::getMatch)
                .distinct()
                .forEach(match -> {
                    long count = winners.stream()
                            .filter(winner -> winner.getMatch().equals(match))
                            .count();
                    totalTicketsByMatch.put(match, count);
                });
        return totalTicketsByMatch;
    }

    private int calculateTotalPrize(List<Winner> winners) {
        return winners.stream()
                .mapToInt(Winner::getPrize)
                .sum();
    }

    private int calculatePrize(long matchedWhiteBalls, boolean isPBMatched) {
        int prize = 0;
        if ((matchedWhiteBalls == 0 || matchedWhiteBalls == 1) && isPBMatched) {
            prize = 4;
        } else if (matchedWhiteBalls == 2 && isPBMatched || matchedWhiteBalls == 3 && !isPBMatched) {
            prize = 7;
        } else if (matchedWhiteBalls == 3 && isPBMatched || matchedWhiteBalls == 4 && !isPBMatched) {
            prize = 100;
        } else if (matchedWhiteBalls == 4 && isPBMatched) {
            prize = 50000;
        } else if (matchedWhiteBalls == 5 && !isPBMatched) {
            prize = 1000000;
        } else if (matchedWhiteBalls == 5 && isPBMatched) {
            prize = 2000000;
        }
        return prize;
    }
}
