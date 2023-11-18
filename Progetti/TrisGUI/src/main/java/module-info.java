module com.tris.trisgui {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.tris.trisgui to javafx.fxml;
    exports com.tris.trisgui;
}
