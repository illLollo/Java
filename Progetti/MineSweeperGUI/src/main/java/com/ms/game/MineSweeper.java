/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ms.game;

/**
 *
 * @author gambaro.lorenzo
 */
public interface MineSweeper 
{
    void stop();
    void start();
    void reset();
    boolean tryCell(int row, int col);
    void viewCell(int row, int col);
    void setFlag(int row, int col);
    boolean isStarted();
    Field getField();
}
