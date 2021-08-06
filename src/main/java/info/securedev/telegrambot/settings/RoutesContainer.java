package info.securedev.telegrambot.settings;

import info.securedev.telegrambot.controllers.*;
import org.telegram.abilitybots.api.sender.MessageSender;

import java.util.HashMap;
import java.util.Map;

public class RoutesContainer {
    private final Map<String, Command> routes;

    public RoutesContainer(MessageSender sender) {
        this.routes = new HashMap<>();

        routes.put(BotConfig.START_BOT_COMMAND, new StartCommandController(sender));
        routes.put(BotConfig.HELP_BOT_COMMAND, new HelpCommandController(sender));
        routes.put(BotConfig.UNKNOWN_BOT_COMMAND, new UnknownCommandController(sender));
        routes.put(BotConfig.FLOOD_BOT_COMMAND, new FloodDataCommandController(sender));
        routes.put(BotConfig.START_FLOOD_BUTTON_1_CALLBACK, new FloodStartCommandController(sender));
        routes.put(BotConfig.START_FLOOD_BUTTON_2_CALLBACK, new FloodCancelCommandController(sender));
    }

    public Map<String, Command> getRoutes() {
        return routes;
    }
}
