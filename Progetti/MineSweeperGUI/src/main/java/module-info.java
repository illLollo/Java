module com.tris.minesweepergui {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.ms.minesweepergui to javafx.fxml;
    exports com.ms.minesweepergui;
}
