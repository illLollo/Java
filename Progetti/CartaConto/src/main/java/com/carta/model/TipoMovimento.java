/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author gambaro.lorenzo
 */
public class TipoMovimento implements Serializable
{   
    private static long id = 0;
    private final long code;
    private final String desc;
    private final double cost;
    private final double amount;
    
    public TipoMovimento(final String desc, final double cost, final double amount)
    {
        this.code = id++;
        this.desc = Objects.requireNonNull(desc);
        this.cost = cost;
        if (amount < -1 || amount > 1)
            throw new IllegalArgumentException("Segno must be -1 for negative, +1 for positive!");
        this.amount = amount;
    }

    public long getCode() 
    {
        return this.code;
    }

    public double getCost() 
    {
        return this.cost;
    }

    public double getAmount() 
    {
        return this.amount;
    }

    public String getDesc() 
    {
        return this.desc;
    }  

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Movimento ").append(this.desc).append(": { ");
        sb.append("Code:").append(this.code).append(", ");
        sb.append("Cost: ").append(this.cost).append(", ");
        sb.append("Sign: ").append(this.amount > 0 ? "+" : "-");
        sb.append(" }");
        return sb.toString();
    }
    
}
