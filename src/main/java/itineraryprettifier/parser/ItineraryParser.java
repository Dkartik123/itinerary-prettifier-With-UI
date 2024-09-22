package itineraryprettifier.parser;

import itineraryprettifier.model.FlightSegment;
import itineraryprettifier.model.Itinerary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItineraryParser {

    private AirportCodeParser airportCodeParser;

    public ItineraryParser(AirportCodeParser airportCodeParser) {
        this.airportCodeParser = airportCodeParser;
    }

    /**
     * Парсит текст маршрута и создает объект Itinerary.
     * @param itineraryText исходный текст маршрута.
     * @return объект Itinerary с сегментами рейса.
     */
    public Itinerary parse(String itineraryText) {
        Itinerary itinerary = new Itinerary();

        // Регулярное выражение для парсинга сегмента рейса
        Pattern flightSegmentPattern = Pattern.compile("Flight (\\w+) from (#[A-Z]{3}|##[A-Z]{4}) to (#[A-Z]{3}|##[A-Z]{4}) departing at ([^ ]+) and arriving at ([^ ]+)");
        Matcher matcher = flightSegmentPattern.matcher(itineraryText);

        while (matcher.find()) {
            String flightNumber = matcher.group(1);
            String departureCode = matcher.group(2);
            String arrivalCode = matcher.group(3);
            String departureTime = matcher.group(4);
            String arrivalTime = matcher.group(5);

            // Преобразование кодов аэропортов
            String departureAirport = airportCodeParser.parseAirportCode(departureCode);
            String arrivalAirport = airportCodeParser.parseAirportCode(arrivalCode);

            FlightSegment segment = new FlightSegment(departureAirport, arrivalAirport, departureTime, arrivalTime, flightNumber);
            itinerary.addSegment(segment);
        }

        return itinerary;
    }
}
