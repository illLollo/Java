package com.tris.trisgui;

import com.tris.trisgame.GameTable;
import com.tris.trisgame.InvalidMoveException;
import com.tris.trisgame.Tris;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import com.tris.trisgame.Player;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class GameController implements Initializable {

    @FXML
    private GridPane gamegrid;
   
    private Tris game;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        this.game = new Tris();   
        
        this.game.setP1(new Player<>("Giocatore 1", 'X', this.game));
        this.game.setP2(new Player<>("Giocatore 2", 'O', this.game));

        this.game.startGame();
        
        for (int i = 0; i < this.gamegrid.getChildren().size(); i++) {
            if (this.gamegrid.getChildren().get(i) instanceof Button) 
            {
                final Button current = (Button) this.gamegrid.getChildren().get(i);
                current.setOnAction((final ActionEvent e) -> 
                {
                    final Button clicked = (Button) e.getSource();
                    int row = GridPane.getRowIndex(clicked);
                    int col = GridPane.getColumnIndex(clicked);
                    
                    try 
                    {
                        final Player p = (Player) this.game.getTurn().getCurrent();
                        
                        p.chooseMove(row, col);
                        clicked.setText(this.game.getTable().getCell(row, col).toString());

                        printGameTable(this.game.getTable()); //CLI debug purpuses
                    }
                    catch (final InvalidMoveException exception)
                    {
                        final Alert exc = new Alert(Alert.AlertType.ERROR);
                        exc.setHeaderText("Mossa non valida");
                        exc.setContentText("Riprova!");
                        exc.showAndWait();
                    }
                    
                    final Player winner = this.game.getWinner();
                    
                    final Alert endGame = new Alert(Alert.AlertType.INFORMATION);
                    endGame.setTitle("Gioco Finito");
                    
                    if (!this.game.isStarted())
                    {
                        if (winner != null)
                        {
                            endGame.setHeaderText("Il vincitore è: " + winner.getCode() + ": \"" + winner.getName() + "\"");
                        }
                        else
                            endGame.setHeaderText("Abbiamo un pareggio!");
                        
                        endGame.showAndWait();
                        this.reset();
                        System.out.println("\n\n\n\n\n\n\n\n\n");
                    }
                    
                });
            }
        }
    }
    private static void printGameTable(GameTable t)
    {
        int sum = 0;
        System.out.println();
        
        for (int i = 0; i < t.getNRows() * t.getNCols(); i++)
        {
            if (i > 0 && i % t.getNCols() == 0) 
            {
                System.out.println();
                for (int j = 0; j < sum - 1; j++)
                    System.out.print('_');
                
                System.out.println("\n");
                sum = 0;
            }
            
            String s = t.getCell(i) + " ";
            
            System.out.print(s);
            sum += s.length();
        }
        
        System.out.println();
        for (int i = 0; i < sum - 1; i++)
            System.out.print('_');
        
        System.out.println("\n");
    }
    private void reset()
    {
        for (Node child : this.gamegrid.getChildren()) 
        {
            ((Button) child).setText("");
            this.game.reset();
            this.game.startGame();
        }
    }
    
    
}
