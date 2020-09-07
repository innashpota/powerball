Powerball [![build](https://github.com/shpotainna/powerball/workflows/build/badge.svg)](https://github.com/shpotainna/powerball/actions?query=workflow%3Abuild)
===========

A java library implementing rules of 
[Powerball game](https://en.wikipedia.org/wiki/Powerball#Playing_the_game).

## How to use
```java
// initialize a game
List<Ticket> tickets = asList(ticket1, ticket2, ...);
Balls winningBalls = new BallsCreator().randomBalls();
PowerballGame game = new PowerballGame(tickets, winningBalls);

// calculate a result
Result result = game.result();

// get information form the game result
result.getWinners();
result.getWinningBalls();
result.getTotalTicketsByMatch();
result.getTotalPrize();
```
