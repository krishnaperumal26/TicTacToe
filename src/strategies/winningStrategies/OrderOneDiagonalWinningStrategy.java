package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Player;
import models.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderOneDiagonalWinningStrategy implements WinningStrategy {

    private Map<Symbol, Integer> leftDiagMap;
    private Map<Symbol, Integer> rightGiagMap;

    public OrderOneDiagonalWinningStrategy(int size, List<Player> players)
    {
        leftDiagMap = new HashMap<>();
        rightGiagMap = new HashMap<>();

        for(Player player : players)
        {
            leftDiagMap.put(player.getSymbol(), 0);
            rightGiagMap.put(player.getSymbol(), 0);
        }
    }


    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        //left diagnol
        if(row==col)
        {
            leftDiagMap.put(symbol, leftDiagMap.get(symbol)+1);
        }
        //right diagnol
        if(row+col == board.getSize()-1)
        {
            rightGiagMap.put(symbol, rightGiagMap.get(symbol)+1);
        }

        //Check winning
        if(row==col)
        {
            if(leftDiagMap.get(symbol) == board.getSize())
            {
                return true;
            }
        }
        if(row+col == board.getSize()-1)
        {
            if(rightGiagMap.get(symbol) == board.getSize())
            {
                return true;
            }
        }
        return false;
    }
}
