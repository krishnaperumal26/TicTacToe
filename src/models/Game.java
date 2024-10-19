package models;

import strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Move> moves;
    private Board board;
    private List<Player> players;
    private int currentMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;
    private GameStatus gameStatus;
    private Player winner;

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies)
    {
        this.moves = new ArrayList<Move>();
        this.board = new Board(dimension);
        this.players = players;
        this.currentMovePlayerIndex = 0;
        this.winningStrategies = winningStrategies;
        this.gameStatus  = GameStatus.INPROGRESS;
//        this.winner = null;

    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getCurrentMovePlayerIndex() {
        return currentMovePlayerIndex;
    }

    public void setCurrentMovePlayerIndex(int currentMovePlayerIndex) {
        this.currentMovePlayerIndex = currentMovePlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }



    public void printResult()
    {
        if(gameStatus.equals(GameStatus.END))
        {
            System.out.println("Game has ended...");
            System.out.println("Winner is "+winner.getName());
        }
        else {
            System.out.println("Game is draw");
        }
    }
    public void printBoard()
    {
        this.board.print();
    }

    private boolean validateMove(Cell cell)
    {
        int row = cell.getRow();
        int col = cell.getCol();

        if(row<0 || col<0 || row >=board.getSize() || col >=board.getSize())
        {
            return false;
        }
        if(board.getBoard().get(row).get(col).getCellStatus().equals(CellStatus.EMPTY))
        {
            return true;
        }
        return false;
    }

    public void makeMove()
    {
        Player currentPlayer = players.get(currentMovePlayerIndex);

        Cell proposedCell = currentPlayer.makeMove();
        if(!validateMove(proposedCell))
        {
            return;
        }
        Cell cellInBoard = board.getBoard().get(proposedCell.getRow()).get(proposedCell.getCol());
        cellInBoard.setCellStatus(CellStatus.FILLED);
        cellInBoard.setPlayer(currentPlayer);
        Move move = new Move(currentPlayer, cellInBoard);
        moves.add(move);
        //Check winner
        for(WinningStrategy winningStrategy : winningStrategies)
        {
            if(winningStrategy.checkWinner(board,move))
            {
                gameStatus = GameStatus.END;
                winner = currentPlayer;
                return;

            }
        }
        //Check draw
        if(moves.size() == board.getSize()*board.getSize())
        {
            gameStatus = GameStatus.DRAW;
        }
        currentMovePlayerIndex+=1;
        currentMovePlayerIndex%=players.size();

    }
    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder
    {
        private List<WinningStrategy> winningStrategies;
        private int dimension;
        private List<Player> players;

        private Builder(){}

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        public Game build()
        {
            if(!valid())
            {
                throw new RuntimeException("Invalid parameter for game");
            }
            return new Game(dimension, players, winningStrategies);
        }

        private boolean valid()
        {
            if(this.players.size() <2)
            {
                return false;
            }
            if(this.players.size()!=this.dimension-1)
                return false;

            int botCount = 0;
            for(Player player : players)
            {
                if(player.getPlayerType().equals(PlayerType.BOT))
                    botCount+=1;
            }
            if(botCount>=2)
            {
                return false;
            }

            Set<Character> existingSymbols = new HashSet<Character>();
            for(Player player : players)
            {
                if(existingSymbols.contains(player.getSymbol().getaChar()))
                {
                    return false;
                }
                existingSymbols.add(player.getSymbol().getaChar());
            }
            return true;
        }
    }
}
