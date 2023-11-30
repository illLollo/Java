/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tris.trisgame;

/**
 *
 * @author Administrator
 * @param <T>
 */
public class Player<T>
{
    private String name;
    private T code;
    private final TrisGame game;
    
    public Player(final String name, final T code, final TrisGame game)
    {
        this.name = name;
        this.code = code;
        this.game = game;
    }
    
    public String getName() { return this.name; }
    public T getCode() { return this.code; }
    public void setCode(T code) { this.code = code; } 
    public void setName(String name) { this.name = name; } 
    
    @Override 
    public String toString() { return this.name;}
    @Override
    public boolean equals(Object other)
    {
        if (other == null) return false;
        
        try 
        {
            Player p = (Player) other;
            return p.getCode() == this.getCode() && p.getName().equals(this.getName());
        }
        catch (final ClassCastException e)
        {
            return false;
        }
//        if (other instanceof Player p)
    }
    public boolean chooseMove(int row, int col) throws InvalidMoveException
    {
            if (this.game.tryMove(row, col))
            {
                this.game.move(row, col);
                return true;
            }
        
        return false;
    }
}
