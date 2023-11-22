/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.giocodel15;

/**
 *
 * @author gambaro.lorenzo
 */
public class FifteenGame 
{
    private final GameTable gametable;
    
    public FifteenGame()
    {
        this.gametable = new GameTable();
        
    }
    public boolean tryMove(int row, int col)
    {
        return false;
    }
    public GameTable getTable() { return this.gametable; }
}
