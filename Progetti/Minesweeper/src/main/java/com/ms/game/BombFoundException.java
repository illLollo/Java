/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ms.game;

/**
 *
 * @author Administrator
 */
public class BombFoundException extends RuntimeException
{
    public BombFoundException(String message)
    {
        super(message);
    }
    public BombFoundException()
    {
        super("Bomb found!");
    }
}
