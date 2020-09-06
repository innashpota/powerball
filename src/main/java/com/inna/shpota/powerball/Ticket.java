package com.inna.shpota.powerball;

import java.util.Objects;

public class Ticket {
    private final String owner;
    private final Balls balls;

    public Ticket(String owner, Balls balls) {
        validate(owner, balls);
        this.owner = owner;
        this.balls = balls;
    }

    public String getOwner() {
        return owner;
    }

    public Balls getBalls() {
        return balls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return owner.equals(ticket.owner) &&
                balls.equals(ticket.balls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, balls);
    }

    private void validate(String owner, Balls balls) {
        if (owner == null || owner.isEmpty()) {
            throw new IllegalArgumentException("Owner must not be empty");
        }
        if (balls == null) {
            throw new IllegalArgumentException("Balls must not be empty");
        }
    }
}
