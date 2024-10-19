package strategies.botPlayingStrategies;

import models.BotDifficulityLevel;
import models.DifficulityLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForDifficulityLevel(BotDifficulityLevel difficultyLevel) {
        return switch (difficultyLevel)
        {
            case EASY -> new EasyBotPlayingStrategy();
            case MEDIUM -> new MediumBotPlayingStrategy();
            case HARD -> new HardBotPlayingStrategy();
        };
    }
}
