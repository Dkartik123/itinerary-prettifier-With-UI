package itineraryprettifier.formatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import itineraryprettifier.airport.AirportLookupService;
import itineraryprettifier.date.DateFormatter;
import itineraryprettifier.date.TimeFormatter;
import itineraryprettifier.util.TextUtils;

public class ItineraryFormatter {

    private AirportLookupService airportLookupService;
    private DateFormatter dateFormatter;
    private TimeFormatter timeFormatter;

    public ItineraryFormatter(AirportLookupService airportLookupService) {
        this.airportLookupService = airportLookupService;
        this.dateFormatter = new DateFormatter();
        this.timeFormatter = new TimeFormatter();
    }

    /**
     * Форматирует маршрут для отображения клиенту.
     * @param itinerary исходный текст маршрута.
     * @return отформатированный текст.
     */
    public String format(String itinerary) {
        // Преобразование кодов аэропортов
        itinerary = formatAirportCodes(itinerary);

        // Преобразование дат и времени
        itinerary = formatDatesAndTimes(itinerary);

        // Очистка от лишних пустых строк
        itinerary = TextUtils.replaceLineBreaks(itinerary);

        return itinerary;
    }

    /**
     * Преобразует коды аэропортов (IATA/ICAO) в названия аэропортов.
     * @param text текст, содержащий коды аэропортов.
     * @return текст с преобразованными кодами аэропортов.
     */
    private String formatAirportCodes(String text) {
        // IATA code pattern (#XXX)
        Pattern iataPattern = Pattern.compile("#([A-Z]{3})");
        Matcher iataMatcher = iataPattern.matcher(text);

        // Замена IATA кодов на название аэропорта
        StringBuffer result = new StringBuffer();
        while (iataMatcher.find()) {
            String iataCode = iataMatcher.group(1);
            String airportName = airportLookupService.getAirportNameByIata(iataCode);
            iataMatcher.appendReplacement(result, airportName != null ? airportName : "#" + iataCode);
        }
        iataMatcher.appendTail(result);

        // ICAO code pattern (##XXXX)
        Pattern icaoPattern = Pattern.compile("##([A-Z]{4})");
        Matcher icaoMatcher = icaoPattern.matcher(result.toString());

        // Замена ICAO кодов на название аэропорта
        result.setLength(0); // Очищаем буфер для нового результата
        while (icaoMatcher.find()) {
            String icaoCode = icaoMatcher.group(1);
            String airportName = airportLookupService.getAirportNameByIcao(icaoCode);
            icaoMatcher.appendReplacement(result, airportName != null ? airportName : "##" + icaoCode);
        }
        icaoMatcher.appendTail(result);

        return result.toString();
    }

    /**
     * Преобразует даты и время в формат, удобный для клиента.
     * @param text текст, содержащий даты и время.
     * @return текст с отформатированными датами и временем.
     */
    private String formatDatesAndTimes(String text) {
        // Форматирование дат (D(2007-04-05T12:30−02:00) -> 05-Apr-2007)
        Pattern datePattern = Pattern.compile("D\\(([^\\)]+)\\)");
        Matcher dateMatcher = datePattern.matcher(text);
        StringBuffer result = new StringBuffer();
        while (dateMatcher.find()) {
            String isoDate = dateMatcher.group(1);
            String formattedDate = dateFormatter.formatDate(isoDate);
            dateMatcher.appendReplacement(result, formattedDate);
        }
        dateMatcher.appendTail(result);

        // Форматирование времени 12-часового (T12(2007-04-05T12:30−02:00) -> 12:30PM (-02:00))
        Pattern time12Pattern = Pattern.compile("T12\\(([^\\)]+)\\)");
        Matcher time12Matcher = time12Pattern.matcher(result.toString());
        result.setLength(0);
        while (time12Matcher.find()) {
            String isoTime = time12Matcher.group(1);
            String formattedTime = timeFormatter.formatTime12Hour(isoTime);
            time12Matcher.appendReplacement(result, formattedTime);
        }
        time12Matcher.appendTail(result);

        // Форматирование времени 24-часового (T24(2007-04-05T12:30−02:00) -> 12:30 (-02:00))
        Pattern time24Pattern = Pattern.compile("T24\\(([^\\)]+)\\)");
        Matcher time24Matcher = time24Pattern.matcher(result.toString());
        result.setLength(0);
        while (time24Matcher.find()) {
            String isoTime = time24Matcher.group(1);
            String formattedTime = timeFormatter.formatTime24Hour(isoTime);
            time24Matcher.appendReplacement(result, formattedTime);
        }
        time24Matcher.appendTail(result);

        return result.toString();
    }
}
