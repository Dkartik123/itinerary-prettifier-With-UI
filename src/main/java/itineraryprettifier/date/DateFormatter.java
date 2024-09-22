package itineraryprettifier.date;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateFormatter {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);

    /**
     * Преобразует дату в формат DD-Mmm-YYYY.
     * @param isoDate строка в формате ISO 8601.
     * @return отформатированная строка или оригинальная строка, если формат неправильный.
     */
    public String formatDate(String isoDate) {
        try {
            ZonedDateTime dateTime = ZonedDateTime.parse(isoDate, INPUT_FORMATTER);
            return dateTime.format(OUTPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            // Если формат даты неправильный, возвращаем оригинальную строку
            return isoDate;
        }
    }
}
