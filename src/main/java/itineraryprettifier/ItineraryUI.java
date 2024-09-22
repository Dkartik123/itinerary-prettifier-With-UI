package itineraryprettifier;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

import java.io.File;

public class ItineraryUI extends Application {

    private TextField inputFileField;
    private TextField outputFileField;
    private TextField airportLookupFileField;
    private TextArea outputArea;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Itinerary Prettifier");

        // Поля для выбора файлов
        inputFileField = new TextField();
        outputFileField = new TextField();
        airportLookupFileField = new TextField();

        Button inputFileButton = new Button("Choose Input File");
        Button outputFileButton = new Button("Choose Output File");
        Button airportLookupFileButton = new Button("Choose Airport Lookup File");

        // Обработчики кнопок для выбора файлов
        inputFileButton.setOnAction(e -> chooseFile(primaryStage, inputFileField));
        outputFileButton.setOnAction(e -> chooseFile(primaryStage, outputFileField));
        airportLookupFileButton.setOnAction(e -> chooseFile(primaryStage, airportLookupFileField));

        // Кнопка для запуска процесса
        Button processButton = new Button("Process Itinerary");
        processButton.setOnAction(e -> processItinerary());

        outputArea = new TextArea();
        outputArea.setEditable(false);


        // Компоновка интерфейса
        VBox vbox = new VBox(10,
                new Label("Input File:"), inputFileField, inputFileButton,
                new Label("Output File:"), outputFileField, outputFileButton,
                new Label("Airport Lookup File:"), airportLookupFileField, airportLookupFileButton,
                processButton,
                new Label("Output:"), outputArea

        );
        Scene scene = new Scene(vbox, 600, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void chooseFile(Stage stage, TextField textField) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            textField.setText(file.getAbsolutePath());
        }
    }

    private void processItinerary() {
        String inputFilePath = inputFileField.getText();
        String outputFilePath = outputFileField.getText();
        String airportLookupFilePath = airportLookupFileField.getText();

        Prettifier prettifier = new Prettifier();

        // Используем метод processItineraryWithResult для получения результата
        String result = prettifier.processItinerary(inputFilePath, outputFilePath, airportLookupFilePath);

        // Отображаем результат в TextArea
        outputArea.setText(result);
    }
}