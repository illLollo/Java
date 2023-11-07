/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.strutturedati.trisgame;

/**
 *
 * @author Lorenzo
 */
public class NoPlayersException extends Exception
{
    public NoPlayersException(String message)
    {
        super(message);
    }
    public NoPlayersException()
    {
        super("There aren't enough players to play!");
    }
}
