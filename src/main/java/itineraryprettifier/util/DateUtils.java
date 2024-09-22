package itineraryprettifier.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    /**
     * Проверяет, является ли строка валидной датой в формате ISO 8601.
     * @param date строка с датой.
     * @return true, если дата валидна, иначе false.
     */
    public static boolean isValidIsoDate(String date) {
        try {
            ZonedDateTime.parse(date, INPUT_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Преобразует временную зону "Z" (Zulu) в формат (+00:00).
     * @param offset временная зона.
     * @return преобразованная строка с временной зоной.
     */
    public static String formatTimeZone(String offset) {
        return offset.equalsIgnoreCase("Z") ? "(+00:00)" : offset;
    }
}
