package info.securedev.telegrambot.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputData {
    private static final String SPACE_REGEX = "\\s";
    private static final String SYMBOLS_REGEX = "[)(+-]+";
    private static final String NUMBERS_REGEX = "\\d+";
    private static final String KZ_OPERATORS = "700|701|702|703|704|705|706|707|708|709|747|750|751|760|761|762|763" +
            "|764|771|775|776|777|778";

    public static List<String> getNumbersWithoutSymbols(String inputData) {
        List<String> arrayNumbers = new ArrayList<>();

        String filteredData = inputData.trim()
                .replaceAll(SPACE_REGEX, "")
                .replaceAll(SYMBOLS_REGEX, "");

        if (!filteredData.contains(",")) {
            arrayNumbers.add(filteredData);
        } else {
            String[] array = filteredData.split(",");
            arrayNumbers.addAll(Arrays.asList(array));
        }

        return arrayNumbers;
    }

    public static boolean checkInputData(List<String> arrayWithoutSymbols) {
        for (String str: arrayWithoutSymbols)
            if (!str.matches(NUMBERS_REGEX))
                return false;

        return true;
    }

    public static List<String> getNumbersInKzFormat(List<String> arrayWithoutSymbols) {
        List<String> arrayNumbersInKzFormat = new ArrayList<>();

        for (String str: arrayWithoutSymbols) {
            if (str.length() == 11 && str.substring(1, 4).matches(KZ_OPERATORS) &&
                    (str.startsWith("8") || str.startsWith("7"))) {
                arrayNumbersInKzFormat.add(str.substring(1));
            } else if (str.length() == 10 && str.substring(0, 3).matches(KZ_OPERATORS)) {
                arrayNumbersInKzFormat.add(str);
            } else {
                arrayNumbersInKzFormat.clear();
                return arrayNumbersInKzFormat;
            }
        }

        return arrayNumbersInKzFormat;
    }
}
