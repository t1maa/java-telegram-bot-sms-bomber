package info.securedev.telegrambot.settings;

public class BotConfig {
    // Initialization (set your values)
    public static final String BOT_USERNAME = "your_bot_username";
    public static final String BOT_TOKEN = "your_bot_token";
    public static final long CREATOR_ID = your_creater_id_numbers;

    // Turn on/off validation numbers for KZ format
    public static final boolean CHECK_FOR_KZ_FORMAT = false;

    // Bot Commands
    public static final String START_BOT_COMMAND = "/start";
    public static final String HELP_BOT_COMMAND = "/help";
    public static final String UNKNOWN_BOT_COMMAND = "unknown";
    public static final String FLOOD_BOT_COMMAND = "floodData";

    // Flood time in minute
    public static final int FLOOD_TIME = 3;

    // Text in messages
    public static final String START_COMMAND_TEXT = "Введите номер или номера на которые хотите запустить флуд!";
    public static final String HELP_COMMAND_TEXT = "Бот создан в ознакомительных целях. " +
            "Введите номер или номера на которые хотите запустить флуд!";
    public static final String UNKNOWN_COMMAND_TEXT = "Прошу Вас ввести номер или номера для осуществления флуда❗";
    public static final String FlOOD_STARTING_TEXT_1 = "Ваш номер или номера, на которые будет запущен flood:\n%s" +
            "Убедитесь в корректности и запустите флуд❗";
    public static final String FlOOD_STARTING_TEXT_2 = "На нижеуказанный номер или номера, был запущен flood:\n%s" +
            "Flood прекратиться в течении " + String.valueOf(FLOOD_TIME) + " минут \uD83D\uDE09";
    public static final String ERROR_TEXT_1 = "Вы ввели некорректный номер или номера. Пожалуйста, убедитесь в " +
            "корректности и введите заново❗";
    public static final String ERROR_TEXT_2 = "Вы ввели номер или номера в некорректном KZ формате. Пожалуйста, " +
            "введите заново❗";
    public static final String ERROR_TEXT_3 = "Для начало, нужно ввести номер или номера на которые хотите " +
            "запустить Flood❗";
    public static final String FLOOD_END_SUCCESS = "На нижеуказанный номер или номера:\n%sFlood был завершен!";
    public static final String FLOOD_END_ERROR = "На нижеуказанный номер или номера:\n%произошла  ошибка в запуске flood-а";

    // Text in inline buttons
    public static final String START_FLOOD_BUTTON_1_TEXT = "Запустить";
    public static final String START_FLOOD_BUTTON_2_TEXT = "Отменить";

    // Callback data in inline buttons
    public static final String START_FLOOD_BUTTON_1_CALLBACK = "StartFlood";
    public static final String START_FLOOD_BUTTON_2_CALLBACK = "CancelFlood";

    // Static headers
    public static final String USER_AGENT_HEADER = "Mozilla/5.0 (Windows NT 10.0; Win64; x64AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36";
    public static final String APPLICATION_JSON = "application/json; charset=UTF-8";
    public static final String APPLICATION_URLENCODED = "application/x-www-form-urlencoded; charset=UTF-8";
}
