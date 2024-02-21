module com.conto.cartacontogui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.conto.cartacontogui to javafx.fxml;
    exports com.conto.cartacontogui;
    requires org.json;
}
