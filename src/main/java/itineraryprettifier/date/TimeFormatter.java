package itineraryprettifier.date;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeFormatter {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    // Формат с двоеточиями в часовом поясе
    private static final DateTimeFormatter TIME_12_HOUR_FORMATTER = DateTimeFormatter.ofPattern("hh:mma (XXX)");
    private static final DateTimeFormatter TIME_24_HOUR_FORMATTER = DateTimeFormatter.ofPattern("HH:mm (XXX)");

    /**
     * Преобразует время в 12-часовой формат.
     * @param isoDate строка в формате ISO 8601.
     * @return отформатированная строка или оригинальная строка, если формат неправильный.
     */
    public String formatTime12Hour(String isoDate) {
        try {
            ZonedDateTime dateTime = ZonedDateTime.parse(isoDate, INPUT_FORMATTER);
            // Заменяем Z на +00:00
            String formattedTime = dateTime.format(TIME_12_HOUR_FORMATTER);
            return formattedTime.replace("Z", "+00:00");
        } catch (DateTimeParseException e) {
            // Если формат времени неправильный, возвращаем оригинальную строку
            return isoDate;
        }
    }

    /**
     * Преобразует время в 24-часовой формат.
     * @param isoDate строка в формате ISO 8601.
     * @return отформатированная строка или оригинальная строка, если формат неправильный.
     */
    public String formatTime24Hour(String isoDate) {
        try {
            ZonedDateTime dateTime = ZonedDateTime.parse(isoDate, INPUT_FORMATTER);
            // Заменяем Z на +00:00
            String formattedTime = dateTime.format(TIME_24_HOUR_FORMATTER);
            return formattedTime.replace("Z", "+00:00");
        } catch (DateTimeParseException e) {
            // Если формат времени неправильный, возвращаем оригинальную строку
            return isoDate;
        }
    }
}
