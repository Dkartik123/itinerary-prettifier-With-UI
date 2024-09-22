package itineraryprettifier.parser;

import itineraryprettifier.airport.AirportLookupService;

public class AirportCodeParser {

    private AirportLookupService airportLookupService;

    public AirportCodeParser(AirportLookupService airportLookupService) {
        this.airportLookupService = airportLookupService;
    }

    /**
     * Парсит код аэропорта (IATA или ICAO) и возвращает его название.
     * @param code код аэропорта в формате #XXX или ##XXXX.
     * @return название аэропорта или исходный код, если аэропорт не найден.
     */
    public String parseAirportCode(String code) {
        if (code.startsWith("##")) {
            // Обработка ICAO кодов (4 буквы)
            String icaoCode = code.substring(2);
            String airportName = airportLookupService.getAirportNameByIcao(icaoCode);
            return airportName != null ? airportName : code;
        } else if (code.startsWith("#")) {
            // Обработка IATA кодов (3 буквы)
            String iataCode = code.substring(1);
            String airportName = airportLookupService.getAirportNameByIata(iataCode);
            return airportName != null ? airportName : code;
        }
        return code; // Возвращаем код как есть, если он не совпадает с шаблоном
    }
}
