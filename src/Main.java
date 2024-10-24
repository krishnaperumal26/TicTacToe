import controllers.GameController;
import models.*;
import strategies.winningStrategies.OrderOneColumnWinningStrategy;
import strategies.winningStrategies.OrderOneDiagonalWinningStrategy;
import strategies.winningStrategies.OrderOneRowWinningStrategy;
import strategies.winningStrategies.WinningStrategy;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        GameController gameController = new GameController();
        int boardSize = 3;
        List<Player> players  =  List.of(
                new Player(new Symbol('X'), "KP", PlayerType.HUMAN),
                new Bot(new Symbol('O'), "HP", BotDifficulityLevel.EASY)
        );
        List<WinningStrategy> winningStrategyList = List.of(
                new OrderOneColumnWinningStrategy(boardSize, players),
                new OrderOneRowWinningStrategy(boardSize, players),
                new OrderOneDiagonalWinningStrategy(boardSize,players)
        );
        Game game;
        try
        {
            game = gameController.createGame(boardSize, players, winningStrategyList );
        } catch (Exception e) {
            System.out.println("Some thing went wrong");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("-----------Game is starting-------------");
        while(gameController.getGameStatus(game).equals(GameStatus.INPROGRESS))
        {
            System.out.println("This is how board look like....");
            gameController.displayBoard(game);
            System.out.println("Do you want to undo ?");
            String input = sc.next();
            if(input.equalsIgnoreCase("yes"))
            {
                gameController.undo(game);
            }
            else {
                gameController.makeMove(game);
            }


        }

            gameController.printResult(game);


        }
    }


