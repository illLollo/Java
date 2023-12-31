/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ms.game;

/**
 *
 * @author Administrator
 */
public final class MineSweeperGame implements MineSweeper
{
    private Field gameField;
    private boolean isStarted;
    private final int rows;
    private final int cols;
    
    public MineSweeperGame(int dim1, int dim2)
    {
       this.gameField = new Field(dim1, dim2);
       this.isStarted = false;
       this.rows = dim1;
       this.cols = dim2;
    }
    public MineSweeperGame()
    {
        this(10, 10);
    }
    @Override
    public boolean isStarted()
    {
        return this.isStarted;
    }
    @Override
    public void start()
    {
        if (this.isStarted())
            throw new IllegalStateException("Minesweeper game already started!");
        this.isStarted = true;
    }
    @Override
    public void stop()
    {
        if (!this.isStarted())
            throw new IllegalStateException("Minesweeper game not started yet!");
        this.isStarted = false;
    }
    
    @Override
    public boolean tryCell(int xCell, int yCell)
    {
        if (!this.isStarted())
            throw new IllegalStateException("Minesweeper game not started yet!");
        
        final Cell selected = this.gameField.getCell(xCell,yCell);
        if (selected.isFlag() || selected.isShowing())
            return false;
        
        return xCell >= 0 && xCell < this.gameField.getNCols() && yCell >= 0 || yCell < this.gameField.getNRows();       
    }
    @Override
    public void viewCell(int row, int col)
    {
        if (!this.isStarted())
            throw new IllegalStateException("Minesweeper game not started yet!");
        
        final Cell selected = this.gameField.getCell(row, col);
        
        if (selected.isBomb())
        {
            this.isStarted = false;
            throw new BombFoundException();
        }
        if (selected.isShowing())
            throw new IllegalMoveException("Cell already showing");
        if (selected.isFlag())
            throw new IllegalMoveException("Cell is flagged!");
           
        this.dfsShowCell(selected);
        this.isStarted = !this.hasWon();
    }
    @Override
    public Field getField() { return this.gameField; }
    private void dfsShowCell(Cell c)
    {
        c.setVisibility(true);
        if (c.getAdiacentsBombsCounter() == 0)
            for (Cell temp : c.getAdiacents())
                if (!temp.isShowing() && !temp.isFlag())
                {
                    if (temp.getAdiacentsBombsCounter() == 0)
                        this.dfsShowCell(temp);
                    else temp.setVisibility(true);
                }
    }
    @Override
    public void reset()
    {
        this.isStarted = false;
        this.gameField = new Field(this.rows, this.cols);
    }
    @Override
    public void setFlag(int row, int col)
    {
        if (!this.isStarted())
            throw new IllegalStateException("Minesweeper game not started yet!");
        
        final Cell selected = this.gameField.getCell(row, col);
        
        if (selected.isShowing())
            throw new IllegalMoveException("Cell showing!");
        
        selected.setFlag(!selected.isFlag());
    }
    private boolean hasWon()
    {
        for (int row = 0; row < this.gameField.getNRows(); row++)
            for (int col = 0; col < this.gameField.getNCols(); col++)
            {
                final Cell selected = this.gameField.getCell(row, col);
                if (!selected.isBomb() && !selected.isShowing() && !selected.isFlag()) 
                    return false;
            }
        
        return true;
    }

}
