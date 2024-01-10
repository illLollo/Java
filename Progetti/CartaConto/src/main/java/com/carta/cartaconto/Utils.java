/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

/**
 *
 * @author gambaro.lorenzo
 */
public abstract class Utils 
{
    private Utils() {}
    
    public static boolean isDigit(final char c)
    {
        return c >= '0' && c <= '9';
    }
    public static boolean isLowerCaseLetter(final char c)
    {
        return c >= 'a' && c <= 'z';
    }
    public static boolean isUpperCaseLetter(final char c)
    {
        return c >= 'A' && c <= 'Z';
    }
}
