package com.inna.shpota.powerball;

public class Winner {
    private final Ticket ticket;
    private final int prize;
    private final String match;

    public Winner(Ticket ticket, int prize, String match) {
        this.ticket = ticket;
        this.prize = prize;
        this.match = match;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public int getPrize() {
        return prize;
    }

    public String getMatch() {
        return match;
    }
}
