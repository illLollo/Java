/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ms.game;

/**
 *
 * @author Administrator
 */
public class MineSweeper 
{
    private Player p;
    private Field gameField;
    private boolean isStarted;
    
    public MineSweeper(int dim1, int dim2, Player p)
    {
       this.p = p;
       this.gameField = new Field(dim1, dim2);
       this.isStarted = false;
    }
    public MineSweeper(Player p)
    {
        this(10, 10, p);
    }
    public boolean isStarted()
    {
        return this.isStarted;
    }
    public void start()
    {
        if (this.isStarted())
            throw new IllegalStateException("Minesweeper game already started!");
        this.isStarted = true;
    }
    public void stop()
    {
        if (!this.isStarted())
            throw new IllegalStateException("Minesweeper game not started yet!");
        this.isStarted = false;
    }
    
    public boolean tryCell(int xCell, int yCell)
    {
        if (!this.isStarted())
            throw new IllegalStateException("Minesweeper game not started yet!");
        
        final Cell selected = this.gameField.getCell(xCell,yCell);
        if (selected.isFlag() || selected.isShowing())
            return false;
        
        return xCell >= 0 && xCell < this.gameField.getNCols() && yCell >= 0 || yCell < this.gameField.getNRows();       
    }
    public void viewCell(int xCell, int yCell)
    {
        if (!this.isStarted())
            throw new IllegalStateException("Minesweeper game not started yet!");
        
        final Cell selected = this.gameField.getCell(xCell, yCell);
        
        if (selected.isBomb())
        {
            this.isStarted = false;
            throw new BombFoundException();
        }
        if (selected.isShowing())
            throw new IllegalMoveException("Cell already showing");
        if (selected.isFlag())
            throw new IllegalMoveException("Cell is flagged!");
           
        this.showCell(selected);
    }
    public Field getField() { return this.gameField; }
    private void showCell(Cell c)
    {
        c.setVisibility(true);
        if (c.getAdiacentsBombsCounter() == 0)
            for (Cell temp : c.getAdiacents())
                if (temp != null)
                {
                    if (!temp.isShowing())
                    {
                        if (temp.getAdiacentsBombsCounter() == 0)
                            this.showCell(temp);
                        else temp.setVisibility(true);
                    }
                }
    }
    public void setFlag(int xCell, int yCell)
    {
        if (!this.isStarted())
            throw new IllegalStateException("Minesweeper game not started yet!");
        
        final Cell selected = this.gameField.getCell(xCell, yCell);
        
        if (selected.isShowing())
            throw new IllegalMoveException("Cell showing!");
        
        selected.setFlag(!selected.isFlag());
    }
}
