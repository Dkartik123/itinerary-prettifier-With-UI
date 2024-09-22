package itineraryprettifier.airport;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirportLookupService {
    private Map<String, AirportLookup> iataMap = new HashMap<>();
    private Map<String, AirportLookup> icaoMap = new HashMap<>();

    public AirportLookupService(String csvFilePath) throws IOException {
        AirportLookupReader reader = new AirportLookupReader();
        List<AirportLookup> airports = reader.readAirportsFromCsv(csvFilePath);

        for (AirportLookup airport : airports) {
            if (airport.getIataCode() != null && !airport.getIataCode().isEmpty()) {
                iataMap.put(airport.getIataCode(), airport);
            }
            if (airport.getIcaoCode() != null && !airport.getIcaoCode().isEmpty()) {
                icaoMap.put(airport.getIcaoCode(), airport);
            }
        }
    }

    public String getAirportNameByIata(String iataCode) {
        AirportLookup airport = iataMap.get(iataCode);
        return airport != null ? airport.getName() : null;
    }

    public String getAirportNameByIcao(String icaoCode) {
        AirportLookup airport = icaoMap.get(icaoCode);
        return airport != null ? airport.getName() : null;
    }

    public String getMunicipalityByIata(String iataCode) {
        AirportLookup airport = iataMap.get(iataCode);
        return airport != null ? airport.getMunicipality() : null;
    }

    public String getMunicipalityByIcao(String icaoCode) {
        AirportLookup airport = icaoMap.get(icaoCode);
        return airport != null ? airport.getMunicipality() : null;
    }
}
