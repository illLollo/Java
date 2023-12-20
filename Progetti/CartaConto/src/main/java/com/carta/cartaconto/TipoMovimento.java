/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

/**
 *
 * @author gambaro.lorenzo
 */
public abstract class TipoMovimento 
{
    private final long code;
    private final String descr;
    
    private final long daysAfter;
    private final double cost;
    
    public TipoMovimento(final long code, final String descr, final long daysAfter, final double cost)
    {
        this.code = code;
        this.descr = descr;
        this.daysAfter = daysAfter;
        this.cost = cost;               
    }
}
