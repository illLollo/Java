/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.conto.cartacontogui;

import com.carta.model.Banca;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class SecondaryController implements Initializable {


    @FXML
    private Button logoutBTN;
    
    private Banca b;
    
    public void setBanca(final Banca b)
    {
        this.b = b;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        this.logoutBTN.setOnAction((final ActionEvent e) -> {
            try {
                App.setRoot("login");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }    
    
}
