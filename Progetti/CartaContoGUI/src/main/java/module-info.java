module com.conto.cartacontogui {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.conto.cartacontogui to javafx.fxml;
    exports com.conto.cartacontogui;
}
