
# Itinerary Prettifier

Itinerary Prettifier is a simple Java application with a graphical user interface (JavaFX), which takes an itinerary input file and formats the text according to specified rules. It also converts airport codes (IATA/ICAO) into airport names using a provided lookup CSV file and makes ISO date and time entries more readable.

**If you want to run the application without the UI**, you can use the `Prettifier` class directly. See the section [Running without UI](#running-without-ui) below for details.

## Features

- **Date Formatting**: Converts ISO date and time entries to a more human-readable format.
- **Airport Code Conversion**: Converts IATA and ICAO codes into airport names based on a provided lookup file.
- **Whitespace Management**: Removes excessive whitespace and ensures proper formatting of vertical white spaces.
- **File Processing**: Reads from an input text file, formats the content, and writes it to an output file.

## Prerequisites

Before running the application, ensure you have the following installed on your machine:

- **Java 21** or higher
- **Maven** (for building and running the application)

## Getting Started

### Clone the Repository

Clone the project from your repository:

```bash
git clone https://gitea.kood.tech/denislomakin/itinerary.git
cd itinerary
```

### Building the Project

To build the project, run the following Maven command:

```bash
mvn clean install
```

### Running the Application

You can run the application either from Maven or directly from your IDE.

#### Running via Maven (with UI):

```bash
mvn javafx:run
```

#### Running without UI

If you prefer to run the application without the graphical user interface (GUI), you can directly run the `Prettifier` class from the command line or your IDE. This allows you to process itinerary files without using the UI.

**Command Line Example:**

```bash
java -cp target/itinerary-1.0-SNAPSHOT.jar itineraryprettifier.Prettifier ./input.txt ./output.txt ./airports_lookup.csv
```

Replace the paths to `input.txt`, `output.txt`, and `airports_lookup.csv` with your actual file paths.

### Using the Application with UI

1. When the application starts, you'll see a graphical interface with fields for selecting input and output files and the airport lookup file.
2. Choose the input text file, the output file path, and the airport lookup CSV file using the "Choose File" buttons.
3. Press the "Process Itinerary" button to format the itinerary and convert airport codes.

### Input File Format

The input file should contain itinerary data including dates and airport codes. Here's an example:

```
D(2022-05-09T08:07Z)
Your flight departs from #HAJ, and your destination is ##EDDW.
```

### Output Example

Given an input like:

```
D(2022-05-09T08:07Z)
Your flight departs from #HAJ, and your destination is ##EDDW.
```

The formatted output will be:

```
09 May 2022
Your flight departs from Hannover Airport, and your destination is Bremen Airport.
```

### File Requirements

1. **Input File**: A text file containing itinerary information (dates, airport codes).
2. **Output File**: The location where the formatted output will be written.
3. **Airport Lookup File**: A CSV file that maps IATA/ICAO airport codes to airport names.

The CSV file should have the following columns:

```
name,iso_country,municipality,icao_code,iata_code,coordinates
```

### Example of the `airports_lookup.csv` File:

```csv
name,iso_country,municipality,icao_code,iata_code,coordinates
Hannover Airport,DE,Hannover,EDDV,HAJ,52.4611,9.6851
Bremen Airport,DE,Bremen,EDDW,BRE,53.0475,8.7867
```

## Error Handling

The application checks for common errors and displays corresponding messages in the UI:

- **Too few arguments**: If insufficient files are provided, the application will display a message about missing arguments.
- **File not found**: If the input or lookup files are missing, an error message like "Input not found" or "Airport lookup not found" will appear.
- **Malformed airport lookup**: If the CSV file is not properly formatted, an error message "Airport lookup malformed" will be displayed.
- **Other errors**: General errors will be shown as "An error occurred: <error message>".

## Troubleshooting

- Ensure all file paths are correct.
- Make sure the airport lookup CSV file is correctly formatted with all necessary columns.
- If JavaFX runtime components are missing, make sure you have the necessary JavaFX libraries configured in your environment.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

This README now includes information for both running the application with and without the UI.
>>>>>>> 1e635f8 (Initial Commit)
