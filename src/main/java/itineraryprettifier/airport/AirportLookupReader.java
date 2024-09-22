package itineraryprettifier.airport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AirportLookupReader {

    public List<AirportLookup> readAirportsFromCsv(String filePath) throws IOException {
        List<AirportLookup> airports = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Чтение заголовка
            if (line == null) {
                throw new IOException("Airport lookup CSV is empty");
            }

            // Разбираем заголовки
            String[] headers = parseCsvLine(line);
            if (!isValidHeader(headers)) {
                throw new IOException("Airport lookup malformed: incorrect headers");
            }

            // Чтение данных построчно
            while ((line = br.readLine()) != null) {
                String[] values = parseCsvLine(line);
                if (values.length != headers.length || containsEmptyField(values)) {
                    System.out.println("Skipping malformed line: " + line); // Логируем пропуск строки
                    continue; // Пропускаем некорректные строки
                }

                // Если строка корректна, создаём объект AirportLookup
                AirportLookup airport = new AirportLookup(
                        values[getColumnIndex(headers, "name")],
                        values[getColumnIndex(headers, "iso_country")],
                        values[getColumnIndex(headers, "municipality")],
                        values[getColumnIndex(headers, "icao_code")],
                        values[getColumnIndex(headers, "iata_code")],
                        values[getColumnIndex(headers, "coordinates")]
                );
                airports.add(airport);
            }
        }
        return airports;
    }

    // Метод для проверки наличия заголовков
    private boolean isValidHeader(String[] headers) {
        return headers.length >= 6 &&
                headers[getColumnIndex(headers, "name")].equals("name") &&
                headers[getColumnIndex(headers, "iso_country")].equals("iso_country") &&
                headers[getColumnIndex(headers, "municipality")].equals("municipality") &&
                headers[getColumnIndex(headers, "icao_code")].equals("icao_code") &&
                headers[getColumnIndex(headers, "iata_code")].equals("iata_code") &&
                headers[getColumnIndex(headers, "coordinates")].equals("coordinates");
    }

    // Проверяем наличие пустых значений в строке
    private boolean containsEmptyField(String[] values) {
        for (String value : values) {
            if (value == null || value.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    // Метод для правильного парсинга строк с учётом кавычек и запятых
    private String[] parseCsvLine(String line) {
        // Используем регулярное выражение, чтобы корректно разделять строки с запятыми внутри кавычек
        return line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }

    // Находим индекс колонки по названию
    private int getColumnIndex(String[] headers, String columnName) {
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].trim().equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Missing column: " + columnName);
    }
}
