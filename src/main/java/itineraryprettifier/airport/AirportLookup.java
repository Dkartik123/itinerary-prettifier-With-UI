package itineraryprettifier.airport;

public class AirportLookup {
    private String name;
    private String isoCountry;
    private String municipality;
    private String icaoCode;
    private String iataCode;
    private String coordinates;

    public AirportLookup(String name, String isoCountry, String municipality, String icaoCode, String iataCode, String coordinates) {
        this.name = name;
        this.isoCountry = isoCountry;
        this.municipality = municipality;
        this.icaoCode = icaoCode;
        this.iataCode = iataCode;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public String getIsoCountry() {
        return isoCountry;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getIcaoCode() {
        return icaoCode;
    }

    public String getIataCode() {
        return iataCode;
    }

    public String getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return name + " (" + municipality + ", " + isoCountry + ")";
    }
}
