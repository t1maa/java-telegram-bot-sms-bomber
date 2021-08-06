package info.securedev.telegrambot.controllers;

import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CommandHandlerController {
    private RouteController routeController;
    private Command command;

    public CommandHandlerController(MessageSender sender) {
        routeController = new RouteController(sender);
    }

    public void handleCommand(String inputCommand, Update update) {
        command = routeController.getCommand(inputCommand);
        command.replyToCommand(update);
    }
}
