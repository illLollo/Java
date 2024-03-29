package com.conto.cartacontogui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("applicationFXML/Main"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException 
    {
        scene.setRoot(loadFXML(fxml));
    }
    @Override
    public void stop()
    {
        
    }
    public static void setRoot(Parent root)
    {
        scene.setRoot(root);
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}