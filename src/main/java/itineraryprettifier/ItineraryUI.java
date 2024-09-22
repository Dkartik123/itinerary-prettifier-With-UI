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
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
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
    private void animateBackgroundColor(TextArea textArea, Color startColor, Color endColor) {
        // Create a timeline for changing the background color
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(textArea.backgroundProperty(),
                        new Background(new BackgroundFill(startColor, CornerRadii.EMPTY, null)))),
                new KeyFrame(Duration.seconds(1), new KeyValue(textArea.backgroundProperty(),
                        new Background(new BackgroundFill(endColor, CornerRadii.EMPTY, null))))
        );

        // Play the animation
        timeline.play();
    }
    private void processItinerary() {
        String inputFilePath = inputFileField.getText();
        String outputFilePath = outputFileField.getText();
        String airportLookupFilePath = airportLookupFileField.getText();

        try {
            // Create an instance of the Prettifier class
            Prettifier prettifier = new Prettifier();

            // Process the itinerary and get the result
            String result = prettifier.processItinerary(inputFilePath, outputFilePath, airportLookupFilePath);

            // Display the result in the output area
            outputArea.setText(result);
            if (result.startsWith("An error occurred:")) {
                animateBackgroundColor(outputArea, Color.WHITE, Color.LIGHTCORAL); // Error color
            } else {
                animateBackgroundColor(outputArea, Color.WHITE, Color.LIGHTGREEN); // Success color
            }


        } catch (Exception e) {
            // If an error occurs, display the error message in the output area
            outputArea.setText("Error occurred: " + e.getMessage());
            animateBackgroundColor(outputArea, Color.WHITE, Color.LIGHTCORAL);
        }
    }}