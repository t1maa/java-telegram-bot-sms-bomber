package info.securedev.telegrambot.controllers;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    void replyToCommand(Update update);
}
