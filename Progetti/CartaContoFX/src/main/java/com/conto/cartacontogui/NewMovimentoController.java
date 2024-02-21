/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.conto.cartacontogui;

import com.carta.model.Iban;
import com.carta.model.Intestatario;
import com.carta.model.TipoMovimento;
import com.chat.messanger.ConnectionManager;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.LocalDateStringConverter;

/**
 *
 * @author Administrator
 */
public class NewMovimentoController implements Initializable
{
    @FXML
    private ChoiceBox typeCB;
    
    @FXML
    private DatePicker execDate;
    
    @FXML
    private DatePicker emitDate;
    
    @FXML
    private TextField causal;
    
    @FXML
    private TextField iban;
    
    @FXML
    private TextField amount;
    
    @FXML
    private Button requestBTN;
    
    @FXML
    private TextField operationCostTF;
    
    @FXML
    private Label errorLBL;
    
    private Intestatario user;
    public void setUser(final Intestatario i)
    {
        this.user = Objects.requireNonNull(i);
    }

    private final ChangeListener<? super String> listener = (final ObservableValue<? extends String> observable, final String old, final String current) -> {
            if (current.isBlank() || current.isEmpty())
                return;
            if (!current.matches("^[0-9]+(.[0-9]+)?$"))
            {
                this.amount.textProperty().removeListener(this.listener);
                this.amount.setText(old);
                this.amount.textProperty().addListener(this.listener);
            }
        };;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        if (this.user == null)
            return;
        
        try 
        {
            final ConnectionManager c = new ConnectionManager(19);
            
            c.send(new byte[2], InetAddress.getByName("localhost"), 9998);
            c.recive((final DatagramPacket packet) -> 
            {
                try 
                {
                    final Map<String, TipoMovimento> tipi = (Map<String, TipoMovimento>) Utils.deserializeObject(packet.getData());
                    
                    Platform.runLater(() -> 
                    {
                        for (final String key : tipi.keySet()) 
                            this.typeCB.getItems().add(key);

                        if (!this.typeCB.getItems().isEmpty())
                        {
                            this.typeCB.setValue(this.typeCB.getItems().get(0));
                            this.operationCostTF.setText(String.valueOf(tipi.get((String) this.typeCB.getValue()).getCost()));
                        }
                        
                        this.typeCB.setOnAction(event -> 
                        {
                            this.operationCostTF.setText(String.valueOf(tipi.get((String) this.typeCB.getValue()).getCost()));
                            this.errorLBL.setVisible(false);
                        });
                    });
                } 
                catch (IOException | ClassNotFoundException ex)
                {
                    
                }
            }, () -> 
            {
                System.err.println("Errore nella richiesta dei dati al server!");
            }, 5000);
            
            
        } 
        catch (final SocketException ex) 
        {}
        catch (final UnknownHostException ex)
        {}
        catch (final IOException ex) 
        {}
        
                
        this.amount.textProperty().addListener(this.listener);
        
        
        this.requestBTN.setOnAction(e -> 
        {
            if (!Iban.isValidIban(this.iban.getText().trim()))
            {
                this.errorLBL.setText("Iban inserito non valido!");
                this.iban.setText("");
                this.errorLBL.setVisible(true);
                return;
            }
            try
            {
                LocalDate.parse(this.execDate.getEditor().getText());
            }
            catch (final Exception ex)
            {
                this.errorLBL.setText("Data di esecuzione inserita non valida!");
                this.errorLBL.setVisible(true);
                return;
            }
            try 
            {
                LocalDate.parse(this.emitDate.getEditor().getText());
            }
            catch (final Exception ex)
            {
                this.errorLBL.setText("Data di emissione inserita non valida!");
                this.errorLBL.setVisible(true);
                return;
            }
            
            if (this.emitDate.getValue().isBefore(this.execDate.getValue()))
            {
                this.errorLBL.setText("La data di emissione non può essere prima o quella di esecuzione!");
                this.errorLBL.setVisible(true);
                return;
            }
            
            
            try 
            {
                //request here
                final ConnectionManager c = new ConnectionManager(1088);
                
//                c.send
                
            } catch (final SocketException ex) 
            {
                ex.printStackTrace();
            }
            
                
        });
        final EventHandler<? super MouseEvent> unshowError = (event) -> 
        {
            this.errorLBL.setVisible(false);
        };
        
        this.execDate.addEventHandler(MouseEvent.MOUSE_CLICKED, unshowError);
        this.emitDate.addEventHandler(MouseEvent.MOUSE_CLICKED, unshowError);
        this.causal.addEventHandler(MouseEvent.MOUSE_CLICKED, unshowError);
        this.iban.addEventHandler(MouseEvent.MOUSE_CLICKED, unshowError);
        this.amount.addEventHandler(MouseEvent.MOUSE_CLICKED, unshowError);
        this.execDate.getEditor().addEventHandler(MouseEvent.MOUSE_CLICKED, unshowError);
        this.emitDate.getEditor().addEventHandler(MouseEvent.MOUSE_CLICKED, unshowError);
    }
}
