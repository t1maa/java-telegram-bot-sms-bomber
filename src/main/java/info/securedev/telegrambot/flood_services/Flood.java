package info.securedev.telegrambot.flood_services;

import java.io.IOException;

public interface Flood {
    void execute(String number) throws IOException;
}
