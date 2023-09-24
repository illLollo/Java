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
        
        System.out.println(c);
        
        /*        c.getDivide().setOnAction((ActionEvent event) -> {
        System.out.println("button clicked");
        });*/
        
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Calcolatrice");
        stage.show();
    }

    public static void main(String[] args) { launch(); }

}