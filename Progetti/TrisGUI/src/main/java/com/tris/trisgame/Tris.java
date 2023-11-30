/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tris.trisgame;

/**
 *
 * @author Administrator
 */
public interface Tris 
{
    int COLS = 3;
    int ROWS = 3;
    
    boolean isOver();
    void move(int row, int col);
    boolean tryMove(int row, int col);
    void start();
    void stop();
    void reset();
    boolean isStarted();
    Turn getTurn();
    Player getWinner();
    Cell getCell(int row, int col);
}
