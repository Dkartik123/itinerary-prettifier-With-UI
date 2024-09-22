module org.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    // Открываем пакет itineraryprettifier для javafx.graphics
    opens itineraryprettifier to javafx.graphics;

    exports itineraryprettifier;
}
