package com.ms.minesweepergui;

import com.ms.game.BombFoundException;
import com.ms.game.Cell;
import com.ms.game.Field;
import com.ms.game.MineSweeper;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class PrimaryController implements Initializable {

    @FXML
    private GridPane gamegrid;
    private MineSweeper gameIstance;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        this.gameIstance = new MineSweeper(10, 10);
        
      
       for (int row = 0; row < this.gameIstance.getField().getNRows(); row++)
           for (int col = 0; col < this.gameIstance.getField().getNCols(); col++)
           {
               final Button current = new Button("       ");
               this.gamegrid.add(current, row, col);
               
               current.setOnMousePressed(event ->  
               {
                   final Button selected = (Button) event.getSource();
                   
                   int buttonRow = GridPane.getRowIndex(selected);
                   int buttonCol = GridPane.getColumnIndex(selected);
                   
                   final Cell c = this.gameIstance.getField().getCell(buttonRow, buttonCol);
                   
                   if (event.isPrimaryButtonDown() && !c.isFlag())
                   {
                        try
                        {
                            if (this.gameIstance.tryCell(buttonRow, buttonCol))
                            {
                               this.gameIstance.viewCell(buttonRow, buttonCol);
                               this.updateGrid();
                            }
                        }
                        catch (final BombFoundException e)
                        {
                            this.showAllBombs();
                            final Alert exc = new Alert(Alert.AlertType.WARNING);
                            exc.setHeaderText("Hai perso");
                            exc.setContentText("Sei esploso!");
                            exc.showAndWait();
                            
                            this.gamegrid.getChildren().clear();
                            this.initialize(url, rb);
                        }
                   }
                   else if (event.isSecondaryButtonDown())
                   {
                       this.gameIstance.setFlag(buttonRow, buttonCol);
                       selected.setText(this.gameIstance.getField().getCell(buttonRow, buttonCol).isFlag() ? "   F   " : "       ");
                       selected.setTextFill(Color.GREEN);
                   }
                   printField(this.gameIstance.getField());
                   System.out.println("\n\n");
                   
               });
           }
       this.gameIstance.start();
//       printField(this.gameIstance.getField());
  }
    private void updateGrid()
    {
        final ArrayList<Node> nodes = new ArrayList<>();
        
        for (int i = 0; i < this.gamegrid.getChildren().size(); i++)
        {
            final Node node = this.gamegrid.getChildren().get(i);
            
            int row = GridPane.getRowIndex(node);
            int col = GridPane.getColumnIndex(node);
            
            final Cell cell = this.gameIstance.getField().getCell(row, col);
            
            if (!cell.isFlag())
            {
                try
                {
                    final Button btn = (Button) node;

                    if (cell.isShowing())
                    {
    //                    System.out.println("CELL: " + row + "  --  " + col + ": " + cell);
                        final Label label = new Label("   " + cell.toString() + "   ");
//                        label.setTextFill(Color.rgb(255, , 255));
                        
                        this.gamegrid.add(label, col, row);
    //                    System.out.println("LABEL: " + GridPane.getRowIndex(label) + "  --  " + GridPane.getColumnIndex(label) + ": " + cell);
                        nodes.add(node);
                    }
                }
                catch (final ClassCastException e)
                {

                }
            }
           
        }
        for (final Node node : nodes)
            this.gamegrid.getChildren().remove(node);
    }
    private void showAllBombs()
    {
        final ArrayList<Component> toAdd = new ArrayList<>();
        final ArrayList<Node> toRemove = new ArrayList<>();
        
        for (int i = 0; i < this.gamegrid.getChildren().size(); i++)
        {
            final Node node = this.gamegrid.getChildren().get(i);
            int row = GridPane.getRowIndex(node);
            int col = GridPane.getColumnIndex(node);
            
            if (this.gameIstance.getField().getCell(row, col).isBomb())
            {
                final Label label = new Label("   B   ");
                label.setTextFill(Color.RED);
                                
                toAdd.add(new Component(label, col, row));
                toRemove.add(node);
            }
        }
        for (final Node n : toRemove)
            this.gamegrid.getChildren().remove(n);
        for (final Component component : toAdd) 
            this.gamegrid.add(component.getNode(), component.getRow(), component.getCol());
        
    }
    public static void printField(Field f)
    {
        for (int row = 0; row < f.getNRows(); row++)
        {
            for (int col = 0; col < f.getNCols(); col++)
            {
                System.out.print(f.getCell(row, col));
                System.out.print("  |  ");
            }
            System.out.println("\n----------------------------------------------------------");
        }
    }

}
