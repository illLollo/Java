/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crono.cronometro;

/**
 *
 * @author Administrator
 */
public final class IllegalChronometerStateException extends RuntimeException
{
    public IllegalChronometerStateException(String message) 
    {
        super(message);
    }
    public IllegalChronometerStateException()
    {
        this("Triggered an illegal Chronometer state!");
    }
}
