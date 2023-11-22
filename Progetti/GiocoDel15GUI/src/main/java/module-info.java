module com.fifteen.giocodel15gui {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.fifteen.giocodel15gui to javafx.fxml;
    exports com.fifteen.giocodel15gui;
}
