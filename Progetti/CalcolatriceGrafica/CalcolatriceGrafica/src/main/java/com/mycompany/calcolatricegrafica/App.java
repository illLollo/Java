package com.mycompany.calcolatricegrafica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class App extends Application 
{
    @Override
    public void start(Stage stage) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        
        Controller c = loader.getController();
        
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Calcolatrice");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) { launch(); }

}