/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fifteen.giocodel15;
import java.util.Random;

/**
 *
 * @author gambaro.lorenzo
 */
public final class FifteenGame 
{
    private final GameTable gametable;
    private boolean isStarted;
    private static final Random rnd = new Random();
    
    public FifteenGame()
    {
        this.gametable = new GameTable();
        this.isStarted = false;
    }
    public void start()
    {
        if (!this.isStarted())
            this.isStarted = true;
    }
    public boolean isStarted() { return this.isStarted; }
    public void stop()
    {
        if (this.isStarted())
            this.isStarted = false;
    }
    public boolean tryMove(int row, int col)
    {
       final Card current = this.gametable.getCell(row, col);
       for (final Card ad : this.gametable.getCellAdiacents(current))
           if (ad.getValue() == 0) 
               return true;
       return false;
    }
    public void randomInitialize(int times)
    { 
        //no need to check if move is valid because we start from the empty one, which is by definition always empty
        for (int i = 0; i < times; i++)
        {
            final Card empty = this.gametable.getEmpty();
            final Card[] emptyAdiacents = this.gametable.getCellAdiacents(empty);
            
            int index = rnd.nextInt(emptyAdiacents.length);
            
            this.makeMove(emptyAdiacents[index].getRow(), emptyAdiacents[index].getCol());
        }
    }
    public void makeMove(int cellRow, int cellCol)
    {
        final Card current = this.gametable.getCell(cellRow, cellCol);
        
        this.gametable.getEmpty().setValue(current.getValue());
        current.setValue(0);
        this.gametable.setEmpty(current);
        
        this.isStarted = !this.hasWon();
    }
    public boolean hasWon()
    {
        for (int row = 0; row < this.gametable.getRows(); row++)
            for (int col = 0; col < this.gametable.getCols(); col++)
            {
                final Card card = this.gametable.getCell(row, col);
                int coefx = ((this.gametable.getRows() * row) + col + 1) % (this.gametable.getRows() * this.gametable.getCols());
                
                if (card.getValue() != coefx)
                    return false;
            }
        return true;
    }
    public GameTable getTable() { return this.gametable; }
}
