/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ms.game;

/**
 *
 * @author Administrator
 */
public class Cell 
{
    private boolean isBomb;
    public boolean isFlag;
    private int adiacentBombs;
    private int x;
    private int y;
    
    public Cell(int x, int y, boolean isBomb)
    {
        this.x = x;
        this.y = y;
        this.isBomb = isBomb;
        this.adiacentBombs = 0;
        this.isFlag = false;
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
        
        if (other instanceof Cell c2)
            return c2.isBomb && this.isBomb && c2.isFlag && this.isFlag && c2.adiacentBombs == this.adiacentBombs && c2.x == this.x && c2.y == this.y;
        
        return false;
    }
    @Override
    public String toString()
    {
        if (this.isBomb)
            return "B";
        if (this.isFlag)
            return "F";
        
        return new StringBuilder().append(this.adiacentBombs).toString();
    }
    public void setBomb(final boolean value) { this.isBomb = value; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    
}

