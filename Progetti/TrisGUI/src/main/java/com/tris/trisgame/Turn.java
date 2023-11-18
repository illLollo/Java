/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tris.trisgame;

import java.util.Random;

/**
 *
 * @author Administrator
 * @param <T>
 */
public class Turn<T>
{
    private static final Random rnd = new Random();
    private final T p1;
    private final T p2;
    
    private T currentTurn;
    
    public Turn(T p1, T p2)
    {
        this.p1 = p1;
        this.p2 = p2;

        this.currentTurn = (rnd.nextInt(1) == 0 ? this.p1 : this.p2);
    }
    public void switchTurn() { this.currentTurn = (this.currentTurn.equals(p1) ? this.p2 : this.p1); }
    public T getCurrent() { return this.currentTurn; }
    
}
