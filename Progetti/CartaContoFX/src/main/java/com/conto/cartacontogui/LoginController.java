/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.conto.cartacontogui;

import com.banca.serverbanca.servermodel.LoginInformations;
import com.carta.model.Intestatario;
import com.chat.messanger.ConnectionManager;
import com.chat.messanger.Time;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private Button loginBTN;
    
    @FXML
    private Label errorLabel;
        
    private ConnectionManager c;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.loginBTN.setOnAction((final ActionEvent eh) -> 
        {
            if (this.c != null && this.c.isReciving())
                return;
            
            final String usrn = this.username.getText();
            final String passwd = this.password.getText();
            
            this.username.setText("");
            this.password.setText("");
            
            if (usrn.trim().isBlank() || passwd.trim().isBlank())
            {
                this.errorLabel.setText("Riempire i campi per eseguire l'accesso!");
                this.errorLabel.setTextFill(Color.RED);
                this.errorLabel.setVisible(true);
                return;
            }
                        
            try 
            {   
                this.c = new ConnectionManager(18);
                final Time t = new Time();
                
                this.c.send(Utils.serializeObject(new LoginInformations(usrn, Intestatario.calcolaHash(passwd))), InetAddress.getByName("localhost"), 9999);
                
                this.c.recive((final DatagramPacket packet) -> 
                { 
                    t.stopTimeout();
                    this.c.close();
                    
                    try 
                    {
                        final Object o = Utils.deserializeObject(packet.getData());
                        
                        if (o != null)
                        {
                            final Intestatario i = (Intestatario) o;
                            Platform.runLater(() -> 
                            {
//                                this.errorLabel.setText("Benvenuto nella banca " + i.getUsername() + " " + i.getCf());
//                                this.errorLabel.setTextFill(Color.GREEN);
//                                this.errorLabel.setVisible(true);

                                final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondary.fxml"));
                                try 
                                {
                                    final Parent root = fxmlLoader.load();
                                    final MainPageController c = (MainPageController) fxmlLoader.getController();
                                    c.setIntestatario(i);
                                    c.initialize(url, rb);
                                    
                                    App.setRoot(root);
                                    
                                } catch (final IOException ex) 
                                {
                                    ex.printStackTrace();
                                }
                            });
                        }
                        else
                        {
                            Platform.runLater(() -> {
                                this.errorLabel.setText("Dati immessi non corretti, riprova!");
                                this.errorLabel.setTextFill(Color.RED);
                                this.errorLabel.setVisible(true);
                            });
                        }
                    }
                    catch (IOException | ClassNotFoundException ex) 
                    {
                        Platform.runLater(() -> 
                        {
                            this.errorLabel.setText("Errore nell'acquisitione dei dati, riprova più tardi!");
                            this.errorLabel.setTextFill(Color.RED);
                            this.errorLabel.setVisible(true);
                            ex.printStackTrace();
                        });
                    }
                }, () -> 
                {
                    Platform.runLater(() -> 
                    {
                        this.errorLabel.setText("FORMIDABLE contattare il server della banca, riprovare più tardi!");
                        this.errorLabel.setTextFill(Color.RED);
                        this.errorLabel.setVisible(true);
                    });
                    this.c.close();
                }, 1000);
//                t.setTimeout(() -> 
//                {
//                    this.c.close();
//                    Platform.runLater(() -> 
//                    {
//                        this.errorLabel.setText("FORMIDABLE contattare il server della banca, riprovare più tardi!");
//                        this.errorLabel.setTextFill(Color.RED);
//                        this.errorLabel.setVisible(true);
//                    });
//                    
//                }, 10000);
            } 
            catch (final IOException ex)
            {
//                this.c.close();
                this.errorLabel.setText("Impossibile contattare il server della banca, riprovare più tardi!");
                this.errorLabel.setTextFill(Color.RED);
                this.errorLabel.setVisible(true);
                ex.printStackTrace();
            }            
        });
        this.username.setOnMouseClicked(eh -> this.errorLabel.setVisible(false));
        this.password.setOnMouseClicked(e -> this.errorLabel.setVisible(false));
    }     
}
