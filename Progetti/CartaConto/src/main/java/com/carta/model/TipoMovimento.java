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
public enum TipoMovimento implements Serializable
{
    VERSAMENTO_CONTANTE(0, "Versamento Ordinario", 0.1, 1),
    PRELIEVO_CONTANTE(1, "Prelievo Contante", 0.1, -1),
    BONIFICO_ORDINARIO(2, "Bonifico Ordinario", 0.0, -1),
    BONIFICO_RICEVUTO(3, "Bonifico Ricevuto", 0.0, 1);
    
    private final long code;
    private final String desc;
    private final double cost;
    private final double amount;
    
    private TipoMovimento(final long code, final String desc, final double cost, final double amount)
    {
        this.code = code;
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
