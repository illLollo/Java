module com.mycompany.calcolatricegrafica {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.calcolatricegrafica to javafx.fxml;
    exports com.mycompany.calcolatricegrafica;
}
