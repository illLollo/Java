/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.conto.cartacontogui;

import com.carta.model.Conto;
import com.carta.model.Iban;
import com.carta.model.Intestatario;
import com.carta.model.Movimento;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Administrator
 */
public class MainPageController implements Initializable 
{
    @FXML
    private Label saldoLBL;
    
    @FXML
    private Button newmovimentoBTN;
    
    @FXML
    private TableView<Movimento> movimentiTABLE;
    
    @FXML
    private ChoiceBox contiSelector;
    
    private Intestatario intestatario;
    
    private Conto selectedConto;
    
    public void setIntestatario(final Intestatario i)
    {
        this.intestatario = Objects.requireNonNull(i);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        if (this.intestatario == null)
            return;
        
        for (final Conto temp : this.intestatario.getAllConti())
            this.contiSelector.getItems().add(temp.getIban().getCompleteIban());
        
        this.contiSelector.setValue(this.contiSelector.getItems().get(0));
        this.selectedConto = (Conto) this.intestatario.getConto(new Iban((String) this.contiSelector.getValue()));
        this.saldoLBL.setText("Saldo del conto: €" + this.selectedConto.saldo());

        this.contiSelector.setOnAction(event -> 
        {
            this.selectedConto = (Conto) this.intestatario.getConto(new Iban((String) this.contiSelector.getValue()));
            this.saldoLBL.setText("Saldo del conto: €" + this.selectedConto.saldo());
            fillTable(this.selectedConto);
        });
        
        this.newmovimentoBTN.setOnAction(e -> {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("newmovimento.fxml"));
            try 
            {
                final Parent p = loader.load();
                final NewMovimentoController c = loader.getController();
                c.setUser(this.intestatario);
                c.initialize(url, rb);
                
                App.setRoot(p);
                
                
            } catch (IOException ex) 
            {
                ex.printStackTrace();
            }
            
        });
    }
    private void fillTable(final Conto c)
    {
        final TableColumn<Movimento, String> colEntrata = new TableColumn<>("Entrata: ");
        colEntrata.setCellValueFactory(new PropertyValueFactory<>("descr"));
        final TableColumn<Movimento, String> colUscita = new TableColumn<>("Uscita: ");
        colUscita.setCellValueFactory(new PropertyValueFactory<>("descr"));
        
        final ObservableList<Movimento> movimentiEntrata = FXCollections.observableArrayList();
        final ObservableList<Movimento> movimentiUscita = FXCollections.observableArrayList();
        
        for (final Movimento temp : c.getOperazioni())
        {
            if (temp.getType().getAmount() > 0)
                movimentiEntrata.add(temp);
            else
                movimentiUscita.add(temp);
        }
        
        this.movimentiTABLE.getColumns().removeAll();
        
        this.movimentiTABLE.getColumns().addAll(colEntrata, colUscita);
        this.movimentiTABLE.setItems(movimentiEntrata);
    }
    
    
}
