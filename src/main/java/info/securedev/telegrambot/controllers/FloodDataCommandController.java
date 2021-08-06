package info.securedev.telegrambot.controllers;

import info.securedev.telegrambot.settings.BotConfig;
import info.securedev.telegrambot.utils.InputData;
import info.securedev.telegrambot.utils.OutputData;
import info.securedev.telegrambot.utils.SessionValue;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.telegrambots.meta.api.objects.Update;

public class FloodDataCommandController extends BaseCommandController {
    public FloodDataCommandController(MessageSender sender) {
        super(sender);
    }

    @Override
    public void replyToCommand(Update update) {
        Long telegramId = update.getMessage().getFrom().getId();
        String inputData = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();
        StringBuilder outputText;
        boolean inputDataVal;

        arrayNumbers = InputData.getNumbersWithoutSymbols(inputData);
        inputDataVal = InputData.checkInputData(arrayNumbers);

        if (!inputDataVal) {
            sendMessage(BotConfig.ERROR_TEXT_1, chatId);
            return;
        }

        if (BotConfig.CHECK_FOR_KZ_FORMAT) {
            arrayNumbers = InputData.getNumbersInKzFormat(arrayNumbers);

            if (arrayNumbers.isEmpty()) {
                sendMessage(BotConfig.ERROR_TEXT_2, chatId);
                return;
            }
        }

        outputText = OutputData.getParsedNumbers(arrayNumbers);
        SessionValue.setSessionValue(telegramId, arrayNumbers);
        sendInlineKeyboard(String.format(BotConfig.FlOOD_STARTING_TEXT_1, outputText), chatId);
    }
}
