package itineraryprettifier.model;

public class FlightSegment {

    private String departureAirportCode;
    private String arrivalAirportCode;
    private String departureTime;
    private String arrivalTime;
    private String flightNumber;

    public FlightSegment(String departureAirportCode, String arrivalAirportCode, String departureTime, String arrivalTime, String flightNumber) {
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirportCode = arrivalAirportCode;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightNumber = flightNumber;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Представление сегмента рейса в виде строки.
     * @return отформатированная строка сегмента рейса.
     */
    @Override
    public String toString() {
        return String.format("Flight %s from %s to %s departing at %s and arriving at %s",
                flightNumber, departureAirportCode, arrivalAirportCode, departureTime, arrivalTime);
    }
}
