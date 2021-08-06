package info.securedev.telegrambot.controllers;

import info.securedev.telegrambot.settings.BotConfig;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommandController extends BaseCommandController {
    public StartCommandController(MessageSender sender) {
        super(sender);
    }

    @Override
    public void replyToCommand(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        sendMessage(BotConfig.START_COMMAND_TEXT, chatId);
    }
}
