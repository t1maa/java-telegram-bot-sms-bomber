package info.securedev.telegrambot.controllers;

import info.securedev.telegrambot.settings.BotConfig;
import info.securedev.telegrambot.utils.SessionValue;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.telegrambots.meta.api.objects.Update;

public class FloodCancelCommandController extends BaseCommandController {
    public FloodCancelCommandController(MessageSender sender) {
        super(sender);
    }

    @Override
    public void replyToCommand(Update update) {
        Long telegramId = update.getCallbackQuery().getFrom().getId();
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();

        arrayNumbers = SessionValue.deleteSessionValue(telegramId);

        if (arrayNumbers.isEmpty()) {
            sendMessage(BotConfig.ERROR_TEXT_3, chatId);
            return;
        }

        sendMessage(BotConfig.START_COMMAND_TEXT, chatId);
    }
}
