package info.securedev.telegrambot.utils;

import java.util.*;

public class SessionValue {

    private static final Map<Long, List<String>> sessionValues = new HashMap<>();

    public static void setSessionValue(Long telegramId, List<String> arrayNumbers) {
        sessionValues.put(telegramId, arrayNumbers);
    }

    public static List<String> getSessionValue(Long telegramId) {
        return sessionValues.getOrDefault(telegramId, Collections.emptyList());

    }

    public static List<String> deleteSessionValue(Long telegramId) {
        if (sessionValues.containsKey(telegramId))
            return sessionValues.remove(telegramId);
        else
            return Collections.emptyList();
    }
}
