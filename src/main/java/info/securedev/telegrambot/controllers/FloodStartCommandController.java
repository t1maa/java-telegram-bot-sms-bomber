package info.securedev.telegrambot.controllers;

import info.securedev.telegrambot.flood_services.Flood;
import info.securedev.telegrambot.settings.BotConfig;
import info.securedev.telegrambot.utils.OutputData;
import info.securedev.telegrambot.utils.SessionValue;
import info.securedev.telegrambot.view.StartFlood;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class FloodStartCommandController extends BaseCommandController {
    public FloodStartCommandController(MessageSender sender) {
        super(sender);
    }

    @Override
    public void replyToCommand(Update update) {
        Long telegramId = update.getCallbackQuery().getFrom().getId();
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        List<Flood> floodServices = floodServicesFactory.getFloodServices();
        StringBuilder outputText;

        arrayNumbers = SessionValue.getSessionValue(telegramId);

        if (arrayNumbers.isEmpty()) {
            sendMessage(BotConfig.ERROR_TEXT_3, chatId);
            return;
        }

        outputText = OutputData.getParsedNumbers(arrayNumbers);
        sendMessage(String.format(BotConfig.FlOOD_STARTING_TEXT_2, outputText), chatId);

        model.setToFloodServicesContainer(floodServices);
        model.setToArrayNumbersContainer(arrayNumbers);

        // Temporarily print stack trace. In future it will be write in log file
        try {
            String floodResult = StartFlood.execute(model, BotConfig.FLOOD_TIME);
            sendMessage(String.format(floodResult, outputText), chatId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
