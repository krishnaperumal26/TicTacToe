package models;

import strategies.botPlayingStrategies.BotPlayingStrategy;
import strategies.botPlayingStrategies.BotPlayingStrategyFactory;

public class Bot extends Player{
    private BotDifficulityLevel botDifficulityLevel;
    private BotPlayingStrategy botPlayingStrategy;
    public Bot(Symbol symbol, String name, BotDifficulityLevel botDifficulityLevel) {
        super(symbol, name, PlayerType.BOT);
        this.botDifficulityLevel = botDifficulityLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyForDifficulityLevel(botDifficulityLevel);
    }

    @Override
    public Cell makeMove(Board board)
    {
        return botPlayingStrategy.makeMove(board);
    }

    public BotDifficulityLevel getBotDifficulityLevel() {
        return botDifficulityLevel;
    }

    public void setBotDifficulityLevel(BotDifficulityLevel botDifficulityLevel) {
        this.botDifficulityLevel = botDifficulityLevel;
    }
}
