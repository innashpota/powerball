package com.inna.shpota.powerball;

import java.util.List;
import java.util.Map;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableMap;

public class Result {
    private final List<Winner> winners;
    private final Balls winningBalls;
    private final Map<String, Long> totalPrizeByMatch;
    private final int totalPrize;

    public Result(
            List<Winner> winners,
            Balls winningBalls,
            Map<String, Long> totalPrizeByMatch,
            int totalPrize
    ) {
        this.winners = unmodifiableList(winners);
        this.winningBalls = winningBalls;
        this.totalPrizeByMatch = unmodifiableMap(totalPrizeByMatch);
        this.totalPrize = totalPrize;
    }

    public List<Winner> getWinners() {
        return winners;
    }

    public Balls getWinningBalls() {
        return winningBalls;
    }

    public Map<String, Long> getTotalPrizeByMatch() {
        return totalPrizeByMatch;
    }

    public int getTotalPrize() {
        return totalPrize;
    }
}
