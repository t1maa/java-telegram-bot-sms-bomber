package info.securedev.telegrambot.view;

import info.securedev.telegrambot.flood_services.Flood;
import info.securedev.telegrambot.models.Model;
import info.securedev.telegrambot.settings.BotConfig;

public class StartFlood  {

    public static String execute(Model model, int loop) throws Exception {

        for (int i = 1; i <= loop; i++) {

            for (Flood floodService : model.getFloodServices()) {
                for (String number : model.getArrayNumbers()) {
                    floodService.execute(number);
                }

                Thread.sleep(1200);
            }

            if (i == loop)
                return BotConfig.FLOOD_END_SUCCESS;
        }

        return BotConfig.FLOOD_END_ERROR;
    }
}
