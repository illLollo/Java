/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fifteen.giocodel15;

/**
 *
 * @author gambaro.lorenzo
 */
public interface FifteenGame
{
    void start();
    void stop();
    void reset();
    boolean isStarted();
    boolean tryMove(int row, int col);
    void makeMove(int row, int col);
    GameTable getTable();
}
