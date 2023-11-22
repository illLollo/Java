module com.giocodel15 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.giocodel15 to javafx.fxml;
    exports com.giocodel15;
}
