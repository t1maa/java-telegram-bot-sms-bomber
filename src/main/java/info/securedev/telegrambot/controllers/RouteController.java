package info.securedev.telegrambot.controllers;

import info.securedev.telegrambot.settings.RoutesContainer;
import org.telegram.abilitybots.api.sender.MessageSender;

public class RouteController {
    private MessageSender sender;
    private RoutesContainer routesContainer;

    public RouteController(MessageSender sender) {
        this.sender = sender;
        routesContainer = new RoutesContainer(sender);
    }

    public Command getCommand(String command) {
        if (command != null && routesContainer.getRoutes() != null && !routesContainer.getRoutes().isEmpty())
            if (routesContainer.getRoutes().containsKey(command))
                return routesContainer.getRoutes().get(command);

        return new UnknownCommandController(sender);
    }
}
