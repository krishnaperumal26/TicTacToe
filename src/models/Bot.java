package models;

public class Bot extends Player{
    private BotDifficulityLevel botDifficulityLevel;

    public Bot(Symbol symbol, String name, BotDifficulityLevel botDifficulityLevel) {
        super(symbol, name, PlayerType.BOT);
        this.botDifficulityLevel = botDifficulityLevel;
    }

    @Override
    public Cell makeMove()
    {
        return null;
    }

    public BotDifficulityLevel getBotDifficulityLevel() {
        return botDifficulityLevel;
    }

    public void setBotDifficulityLevel(BotDifficulityLevel botDifficulityLevel) {
        this.botDifficulityLevel = botDifficulityLevel;
    }
}
