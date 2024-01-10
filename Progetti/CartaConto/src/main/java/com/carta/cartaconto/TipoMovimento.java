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
    private final double taxes;
    
    protected TipoMovimento(final long code, final String descr, final long daysAfter, final double taxes)
    {
        this.code = code;
        this.descr = descr;
        this.daysAfter = daysAfter;
        this.taxes = taxes;               
    }

    public long getCode() {
        return code;
    }

    public String getDescr() {
        return descr;
    }

    public long getDaysAfter() {
        return daysAfter;
    }

    public double getCost() {
        return taxes;
    }
}
