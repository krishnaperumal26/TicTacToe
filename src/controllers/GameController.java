package controllers;

import models.Game;
import models.GameStatus;
import models.Player;
import strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> players , List<WinningStrategy> winningStrategyList)
    {
        Game game = Game.getBuilder()
                .setDimension(dimension)
                .setWinningStrategies(winningStrategyList)
                .setPlayers(players).build();
        return game;
    }

    public void displayBoard(Game game)
    {
        game.printBoard();
    }

    public void undo(Game game)
    {

    }

    public void makeMove(Game game)
    {

    }

    public GameStatus getGameStatus(Game game)
    {
        return game.getGameStatus();
    }


    public void printResult(Game game)
    {
        game.printResult();
    }
}
