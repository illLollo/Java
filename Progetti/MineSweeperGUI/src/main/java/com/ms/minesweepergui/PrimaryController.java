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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class PrimaryController implements Initializable 
{

    @FXML
    private GridPane gamegrid;
    private MineSweeper gameIstance;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        this.gameIstance = new MineSweeper(15, 15);

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
                                updateGrid(this.gamegrid, this.gameIstance);
                            }
                        }
                        catch (final BombFoundException e) 
                        {
                            showAllBombs(this.gamegrid, this.gameIstance);
                            getAlert(Alert.AlertType.WARNING, "Hai perso", "Hai perso!", "Sei esploso!")
                                    .showAndWait();

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
                    
                    if (!this.gameIstance.isStarted()) 
                    {
                        showAllBombs(this.gamegrid, this.gameIstance);
                        getAlert(Alert.AlertType.INFORMATION, "Hai Vinto", "Hai Vinto!", "Hai scoperto tutte le caselle senza beccare bombe!")
                                .showAndWait();

                        this.gamegrid.getChildren().clear();
                        this.initialize(url, rb);
                    }
                    printField(this.gameIstance.getField());
                    System.out.println("\n\n");
                });
            }
        this.gameIstance.start();
    }

    private static void updateGrid(final GridPane gamegrid, final MineSweeper gameIstance) 
    {
        final ArrayList<Node> nodes = new ArrayList<>();

        for (int i = 0; i < gamegrid.getChildren().size(); i++) 
        {
            final Node node = gamegrid.getChildren().get(i);

            int row = GridPane.getRowIndex(node);
            int col = GridPane.getColumnIndex(node);

            final Cell cell = gameIstance.getField().getCell(row, col);

            if (!cell.isFlag() && cell.isShowing()) 
            {
                try
                {
                    final Button btn = (Button) node;
                    final Label label = new Label("     " + cell.getValue() + "     ");

                    gamegrid.add(label, col, row);
                    nodes.add(btn);
                }
                catch (final ClassCastException e) {}
            }
        }
        for (final Node node : nodes)
            gamegrid.getChildren().remove(node);
    }

    private static void showAllBombs(GridPane gamegrid, final MineSweeper gameIstance) 
    {
        //USARE LA CLASSE CARD PER ARGINARE IL PROBLEMA DELL'ORDINAMENTO DEI BUTTON NELL'OBSERVABLE LIST getChildren();

        final ArrayList<Card> toAdd = new ArrayList<>();
        final ArrayList<Node> toRemove = new ArrayList<>();

        for (int i = 0; i < gamegrid.getChildren().size(); i++) 
        {
            final Node node = gamegrid.getChildren().get(i);
            int row = GridPane.getRowIndex(node);
            int col = GridPane.getColumnIndex(node);

            if (gameIstance.getField().getCell(row, col).isBomb()) 
            {
                final Label label = new Label("   B   ");
                label.setTextFill(Color.RED);

                toAdd.add(new Card(label, col, row));
                toRemove.add(node);
            }
        }
        for (int i = 0; i < toRemove.size(); i++)
        {
            gamegrid.getChildren().remove(toRemove.get(i));
            final Card c = toAdd.get(i);
            gamegrid.add(c.getNode(), c.getRow(), c.getCol());
        }
    }
    public static void printField(Field f) 
    {
        for (int row = 0; row < f.getNRows(); row++) {
            for (int col = 0; col < f.getNCols(); col++) {
                System.out.print(f.getCell(row, col).getValue());
                System.out.print("  |  ");
            }
            System.out.println("\n----------------------------------------------------------");
        }
    }
    private static Alert getAlert(final AlertType type, final String hText, final String wTitle, final String cText) 
    {
        final Alert alert = new Alert(type);
        alert.setHeaderText(hText);
        alert.setTitle(wTitle);
        alert.setContentText(cText);

        return alert;
    }

}
