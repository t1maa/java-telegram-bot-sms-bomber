package info.securedev.telegrambot;

import info.securedev.telegrambot.controllers.CommandHandlerController;
import info.securedev.telegrambot.settings.BotConfig;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.Reply;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.function.Consumer;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

public class MyBot extends AbilityBot {
    private final CommandHandlerController commandHandlerController;

    public MyBot() {
        super(BotConfig.BOT_TOKEN, BotConfig.BOT_USERNAME);
        commandHandlerController = new CommandHandlerController(sender);
    }

    @Override
    public long creatorId() {
        return BotConfig.CREATOR_ID;
    }

    public Ability replyToStart() {

        return Ability
                .builder()
                .name("start")
                .info("Reply to start command")
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> commandHandlerController.handleCommand(BotConfig.START_BOT_COMMAND, ctx.update()))
                .build();
    }

    public Ability replyToHelp() {

        return Ability
                .builder()
                .name("help")
                .info("Get information about your role")
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> commandHandlerController.handleCommand(BotConfig.HELP_BOT_COMMAND, ctx.update()))
                .build();
    }

    public Ability replyToUnknown() {

        return Ability
                .builder()
                .name(DEFAULT)
                .info("Reply to unknown command")
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> commandHandlerController.handleCommand(BotConfig.UNKNOWN_BOT_COMMAND, ctx.update()))
                .build();
    }

    public Reply replyToFlood() {
        Consumer<Update> action = update -> commandHandlerController.handleCommand(BotConfig.FLOOD_BOT_COMMAND, update);

        return Reply.of(action, update -> update.getMessage().hasText()
                && !update.getMessage().getText().startsWith("/"));
    }

    public Reply replyToInlineButtons() {
        Consumer<Update> action = update -> {
            String inputCommand = update.getCallbackQuery().getData();
            commandHandlerController.handleCommand(inputCommand, update);
        };

        return Reply.of(action, Flag.CALLBACK_QUERY);
    }
}
