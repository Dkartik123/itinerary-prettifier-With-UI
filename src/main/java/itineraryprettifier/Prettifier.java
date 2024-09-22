package itineraryprettifier;

import itineraryprettifier.airport.AirportLookupService;
import itineraryprettifier.file.FileReaderService;
import itineraryprettifier.file.FileWriterService;
import itineraryprettifier.formatter.ItineraryFormatter;
import itineraryprettifier.parser.AirportCodeParser;
import itineraryprettifier.parser.ItineraryParser;

import java.io.File;
import java.io.IOException;

public class Prettifier {
    public static void main(String[] args) {
        // Проверка флага -h
        if (args.length > 0 && args[0].equals("-h")) {
            printUsage();
            return;
        }

        // Проверка, что переданы все три аргумента
        if (args.length < 3) {
            System.out.println("Too few arguments provided.");
            printUsage();
            return;
        }

        processItinerary(args[0], args[1], args[2]);
    }

    public static void printUsage() {
        System.out.println("Usage:");
        System.out.println("java -jar itinerary-prettifier.jar <input file> <output file> <airport lookup file>");
        System.out.println();
        System.out.println("Arguments:");
        System.out.println("  <input file>        Path to the input text file containing the itinerary details.");
        System.out.println("  <output file>       Path where the formatted itinerary will be saved.");
        System.out.println("  <airport lookup file> Path to the CSV file containing airport codes and their details.");
        System.out.println();
        System.out.println("Example:");
        System.out.println("  java -jar itinerary-prettifier.jar ./input.txt ./output.txt ./airports_lookup.csv");
        System.out.println();
        System.out.println("Optional flags:");
        System.out.println("  -h                  Show this help message.");
    }

    public static String processItinerary(String inputFilePath, String outputFilePath, String airportLookupFilePath) {
        try {
            // Проверка существования файлов
            validateFileExists(inputFilePath, "Input");
            validateFileExists(airportLookupFilePath, "Airport lookup");

            // Чтение файлов
            FileReaderService fileReaderService = new FileReaderService();
            String inputText = fileReaderService.readFile(inputFilePath);

            // Чтение базы данных аэропортов
            AirportLookupService airportLookupService = new AirportLookupService(airportLookupFilePath);
            AirportCodeParser airportCodeParser = new AirportCodeParser(airportLookupService);

            // Парсинг маршрута
            ItineraryParser itineraryParser = new ItineraryParser(airportCodeParser);
            var itinerary = itineraryParser.parse(inputText);

            // Форматирование маршрута
            ItineraryFormatter itineraryFormatter = new ItineraryFormatter(airportLookupService);
            String formattedText = itineraryFormatter.format(inputText);

            // Запись в файл
            FileWriterService fileWriterService = new FileWriterService();
            fileWriterService.writeFile(outputFilePath, formattedText);

            System.out.println("Itinerary successfully processed and saved to " + outputFilePath);
            return formattedText;
        } catch (IOException e) {
            return "An error occurred: " + e.getMessage();
        }
    }

    private static void validateFileExists(String filePath, String fileType) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException(fileType + " file not found: " + filePath);
        } else if (!file.canRead()) {
            throw new IOException(fileType + " file cannot be read: " + filePath);
        }
    }
}
