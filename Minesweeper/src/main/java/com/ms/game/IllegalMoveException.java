/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ms.game;

/**
 *
 * @author Administrator
 */
public class IllegalMoveException extends RuntimeException
{
    public IllegalMoveException(String message)
    {
        super(message);
    }
    public IllegalMoveException()
    {
       super("Illegal move exception!"); 
    }
}
