package com.tris.trisgame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Administrator
 * @param <T>
 */
public class Cell<T>
{
    private T symbol;
    private Player ownership;
    
    public Cell(T symbol, Player p)
    {
        this.symbol = symbol;
        this.ownership = p;
    }
    public Cell(T symbol)
    {
        this.symbol = symbol;
        this.ownership = null;
    }
    
    public T getSymbol() { return this.symbol; }
    
    public void setOwnership(Player p) { 
        this.ownership = p; 
        this.symbol = (T) p.getCode();
    }
    
    public void setSymbol(T symbol) { this.symbol = symbol;}
    
    public Player getOwnership() { return this.ownership; }
    
    @Override
    public String toString() { return this.symbol.toString(); }
    
    public boolean isEmpty() { return this.ownership == null; }
    @Override 
    public boolean equals(Object other) {
        if (other == null) 
            return false;

        if (other instanceof Cell cell) 
        {
            if (cell.getSymbol() == null || cell.getOwnership() == null)
                return false;
            
            return cell.getSymbol().equals(this.getSymbol()) && cell.getOwnership().equals(this.getOwnership());
        }

        return false;
    }
}
