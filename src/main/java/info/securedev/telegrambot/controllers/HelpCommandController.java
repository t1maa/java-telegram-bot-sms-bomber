package info.securedev.telegrambot.controllers;

import info.securedev.telegrambot.settings.BotConfig;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelpCommandController extends BaseCommandController {
    public HelpCommandController(MessageSender sender) {
        super(sender);
    }

    @Override
    public void replyToCommand(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        sendMessage(BotConfig.HELP_COMMAND_TEXT, chatId);
    }
}
