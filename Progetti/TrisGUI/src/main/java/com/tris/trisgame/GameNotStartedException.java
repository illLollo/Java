/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tris.trisgame;

/**
 *
 * @author gambaro.lorenzo
 */
public class GameNotStartedException extends RuntimeException
{
    public GameNotStartedException(String message)
    {
        super(message);
    }
    public GameNotStartedException()
    {
        super("Game not started yet!");
    }
}
