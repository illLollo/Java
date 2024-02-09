/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.conto.cartacontogui;

import com.carta.model.Banca;
import com.carta.model.Indirizzo;
import com.carta.model.Intestatario;
import com.carta.model.TipoIntestatario;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class LoginController implements Initializable 
{

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private Button loginBTN;
    
    @FXML
    private Label errorLabel;
    
    private final static Banca banca = new Banca("Intesa Sanpaolo", "IT", "03069", "01783");
   
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        banca.registerUser(new Intestatario("lorenzo1", "12345", TipoIntestatario.ADMIN, "GMBLNZ06T16F241E", "Gambaro", "Lorenzo", LocalDate.now(), new Indirizzo("Via Accoppè Fratte", "85 int. 2", "30035", "Mirano", "VE"),"3899369940", "lorygamba06@gmail.com"));

        this.loginBTN.setOnAction((final ActionEvent eh) -> 
        {
            final String usrn = this.username.getText();
            final String passwd = this.password.getText();
            
            if (usrn.trim().isBlank() || passwd.trim().isBlank())
            {
                this.errorLabel.setText("Riempire i campi per eseguire l'accesso!");
                this.errorLabel.setTextFill(Color.RED);
                this.errorLabel.setVisible(true);
                return;
            }
            
            final Intestatario i = banca.login(usrn, passwd);
            
            if (i == null)
            {
                this.username.setText("");
                this.password.setText("");
                this.errorLabel.setText("Dati immessi non corretti, riprova!");
                this.errorLabel.setTextFill(Color.RED);
                this.errorLabel.setVisible(true);
                return;
            }
            
            this.errorLabel.setTextFill(Color.GREEN);
            this.errorLabel.setText("Login effettuato con successo!");
            this.errorLabel.setVisible(true);
            
            FXMLLoader fxmlLoader = new FXMLLoader(SecondaryController.class.getResource("secondary.fxml"));
            
            System.out.println(fxmlLoader.getController().toString());
//            ((SecondaryController) fxmlLoader.getController()).setBanca(banca);
//            try {
//                App.setRoot(fxmlLoader.load());
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
    
            
        });
        this.username.setOnMouseClicked(eh -> this.errorLabel.setVisible(false));
        this.password.setOnMouseClicked(e -> this.errorLabel.setVisible(false));
    }    
    
}
