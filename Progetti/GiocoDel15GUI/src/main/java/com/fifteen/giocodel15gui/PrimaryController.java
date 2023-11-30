package com.fifteen.giocodel15gui;

import com.fifteen.giocodel15.Card;
import com.fifteen.giocodel15.FifteenGame;
import com.fifteen.giocodel15.GameTable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class PrimaryController implements Initializable
{
    @FXML
    private GridPane gamegrid;
    private FifteenGame game;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        this.gamegrid.getChildren().clear();
        this.game = new FifteenGame();
        this.game.randomInitialize(1000);
        
        for (int row = 0; row < this.game.getTable().getRows(); row++)
            for (int col = 0; col < this.game.getTable().getCols(); col++)
            {
                if (this.game.getTable().getCell(row, col).getValue() != 0)
                {
                    final Button btn = new Button(this.game.getTable().getCell(row, col).getValue() + "");
                    btn.setStyle("-fx-font: 15 arial");
                    this.gamegrid.add(btn, col, row);
                    
                    btn.setOnAction((final ActionEvent e) -> 
                    {
                        final Button current = (Button) e.getSource();
                        
                        
                        int btnRow = GridPane.getRowIndex(current);
                        int btnCol = GridPane.getColumnIndex(current);
                        
                        if (this.game.tryMove(btnRow, btnCol))
                        {
                            final Card empty = this.game.getTable().getEmpty();
                            
                            this.game.makeMove(btnRow, btnCol);
                            this.gamegrid.getChildren().remove(current);
                            this.gamegrid.add(current, empty.getCol(), empty.getRow());
                            
                            printGameMatrix(this.game.getTable());
                        }
                        
                        if (!this.game.isStarted())
                        {
                            getAlert(Alert.AlertType.INFORMATION, "Hai Finito", "Hai Finito!", "Hai completato con successo il gioco!").showAndWait();
                            this.initialize(url, rb);
                        }
                        
                    });
                }
                
            }
        this.game.start();
        printGameMatrix(this.game.getTable());
    }
    private static Alert getAlert(final Alert.AlertType type, final String hText, final String wTitle, final String cText) 
    {
        final Alert alert = new Alert(type);
        alert.setHeaderText(hText);
        alert.setTitle(wTitle);
        alert.setContentText(cText);

        return alert;
    }
    private static void printGameMatrix(final GameTable g)
    {
        for (int row = 0; row < g.getNRows(); row++)
        {
            for (int col = 0; col < g.getNCols(); col++)
                if (g.getCell(row, col) != null)
                    System.out.print(" | " + g.getCell(row, col).getValue());
            System.out.print(" | ");
            System.out.println("\n");
        }
    }
    
}
