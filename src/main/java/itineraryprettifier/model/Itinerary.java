package itineraryprettifier.model;

import java.util.ArrayList;
import java.util.List;

public class Itinerary {

    private List<FlightSegment> segments;

    public Itinerary() {
        this.segments = new ArrayList<>();
    }

    /**
     * Добавляет сегмент рейса к маршруту.
     * @param segment объект FlightSegment.
     */
    public void addSegment(FlightSegment segment) {
        this.segments.add(segment);
    }

    /**
     * Возвращает список сегментов рейса.
     * @return список сегментов рейса.
     */
    public List<FlightSegment> getSegments() {
        return segments;
    }

    /**
     * Представление маршрута в виде строки.
     * @return отформатированный маршрут.
     */
    @Override
    public String toString() {
        StringBuilder itineraryString = new StringBuilder();
        for (FlightSegment segment : segments) {
            itineraryString.append(segment.toString()).append("\n");
        }
        return itineraryString.toString();
    }
}
