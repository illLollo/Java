/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ms.game;

import java.util.Arrays;

/**
 *
 * @author Administrator
 */
public class Cell 
{
    private boolean isBomb;
    public boolean isFlag;
    private int adiacentBombs;
    private final int x;
    private final int y;
    private final Field gamefield;
    private boolean isShowing;
    
    public Cell(int x, int y, boolean isBomb, final Field field)
    {
        this.x = x;
        this.y = y;
        this.isBomb = isBomb;
        this.adiacentBombs = 0;
        this.isFlag = false;
        this.gamefield = field;
        this.isShowing = false;
    }
    public void setFlag(final boolean value) { this.isFlag = value; }
    public boolean isFlag() { return this.isFlag; }
    public boolean isBomb() { return this.isBomb; }
    
    public void increaseAdiacent(int amount) { this.adiacentBombs += amount; }
    
    @Override 
    public boolean equals(Object other)
    {
        if (other == null)
            return false;
        try
        {
            Cell c2 = (Cell) other;
                return c2.x == this.x && c2.y == this.y;
        }
        catch (final ClassCastException e)
        {
            return false;
        }
    }
    public String getValue()
    {
        if (this.isFlag)
            return "F";
        if (this.isBomb)
            return "B";
        if (!this.isShowing())
            return "X";
        
        return new StringBuilder().append(this.adiacentBombs).toString();
    }
    public int getAdiacentsBombsCounter() { return this.adiacentBombs; }
    public void setBomb(final boolean value) { this.isBomb = value; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public Cell[] getAdiacents()
    {
        final Cell[] adiacents = new Cell[8];
        int index = 0;
        
        for (int i = (x - 1) < 0 ? 0 : x - 1; i <= (x + 1 >= this.gamefield.getNCols() ? this.gamefield.getNCols() - 1 : x + 1); i++)
        {
            for (int j = (y - 1) < 0 ? 0 : y - 1; j <= (y + 1 >= this.gamefield.getNRows() ? this.gamefield.getNRows() - 1 : y + 1); j++)
            {
                final Cell selected = this.gamefield.getCell(i, j);
                if (!this.equals(selected))
                    adiacents[index++] = selected;
            }
        }

        return Arrays.copyOf(adiacents, index);
    }
    public boolean isShowing() { return this.isShowing; }
    public void setVisibility(boolean value)
    {
        this.isShowing = value;
    }
}

