/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ms.game;

/**
 *
 * @author Administrator
 */
public final class IllegalStateException extends RuntimeException 
{
    public IllegalStateException(String message)
    {
        super(message);
    }
    public IllegalStateException()
    {
        super("Illegal State!");
    }
}
