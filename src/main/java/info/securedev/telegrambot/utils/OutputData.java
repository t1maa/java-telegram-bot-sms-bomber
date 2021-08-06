package info.securedev.telegrambot.utils;

import info.securedev.telegrambot.settings.BotConfig;

import java.util.List;

public class OutputData {

    public static StringBuilder getParsedNumbers(List<String> arrayNumbers) {
        StringBuilder outputData = new StringBuilder();

        for (String str: arrayNumbers) {
            if (BotConfig.CHECK_FOR_KZ_FORMAT)
                outputData.append("+7 (").append(str.substring(0, 3)).append(") ").append(str.substring(3, 6))
                        .append("-").append(str.substring(6, 8)).append("-").append(str.substring(8, 10)).append("\n");
            else
                outputData.append(str).append("\n");
        }

        return outputData;
    }
}
