package info.securedev.telegrambot.controllers;

import info.securedev.telegrambot.flood_services.FloodServiceFactory;
import info.securedev.telegrambot.models.Model;
import info.securedev.telegrambot.settings.BotConfig;
import info.securedev.telegrambot.utils.Message;
import org.telegram.abilitybots.api.sender.MessageSender;

import java.util.List;

public abstract class BaseCommandController implements Command {
    protected FloodServiceFactory floodServicesFactory;
    protected List<String> arrayNumbers;
    protected Model model;
    protected MessageSender sender;

    public BaseCommandController(MessageSender sender) {
        this.sender = sender;
        this.model = new Model();
        this.floodServicesFactory = new FloodServiceFactory();
    }

    protected void sendMessage(String text, String chatId) {
        Message message = new Message.Builder(sender)
                .withMessageText(text)
                .withChatId(chatId)
                .build();

        message.sendMyMessage();
    }

    protected void sendInlineKeyboard(String text, String chatId) {
        Message message = new Message.Builder(sender)
                .withInlineKeyboard()
                .withTwoButtons()
                .withTextInButtons(BotConfig.START_FLOOD_BUTTON_1_TEXT, BotConfig.START_FLOOD_BUTTON_2_TEXT)
                .withCallbacks(BotConfig.START_FLOOD_BUTTON_1_CALLBACK, BotConfig.START_FLOOD_BUTTON_2_CALLBACK)
                .withMessageText(text)
                .withChatId(chatId)
                .build();

        message.sendMyMessage();
    }
}
