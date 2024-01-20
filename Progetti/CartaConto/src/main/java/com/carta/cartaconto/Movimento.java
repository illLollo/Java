/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

import java.time.LocalDate;
import java.util.Objects;

 
/**
 *
 * @author gambaro.lorenzo
 */
public class Movimento implements Comparable<Movimento>
{
    private static int globalNumber = 0;
    private final int id;
    private final LocalDate operationDate;
    private final LocalDate valuteDate;
    private final String descr;
    private final Iban iban;
    private final double importo;
    private final TipoMovimento type;

    protected Movimento(final LocalDate operationDate, final LocalDate valuteDate, final String descr, final Iban iban, final double importo, final TipoMovimento t)
    {
        this.id = globalNumber++;
        this.operationDate = Objects.requireNonNull(operationDate);
        this.valuteDate = Objects.requireNonNull(valuteDate);
        this.descr = Objects.requireNonNull(descr);
        this.iban = Objects.requireNonNull(iban);
        
        if (importo <= 0)
            throw new IllegalArgumentException("Import must be grater than 0");
        
        this.importo = importo;
        this.type = Objects.requireNonNull(t);
    }

    public TipoMovimento getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public LocalDate getValuteDate() {
        return valuteDate;
    }

    public Iban getIban() {
        return iban;
    }

    public double getImporto() {
        return importo;
    }
    
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Movimento: \n{\n\t");
        
        sb.append("Id: ").append(this.id);
        sb.append("\n\tDescription: ").append(this.descr);
        sb.append("\n\tType: ").append(this.type.getDesc());
        sb.append("\n\tOperation Date: ").append(this.operationDate);
        sb.append("\n\tValute Date: ").append(this.valuteDate);
        sb.append("\n\tIban: ").append(this.iban);
        sb.append("\n\tImport: ").append(this.type.getAmount() > 0 ? "+" : "-").append(this.importo);
        sb.append("\n\tOperation Cost: ").append(this.type.getCost()).append("\n]");
        
        return sb.toString();
    }

    @Override
    public int compareTo(Movimento o) 
    {
        if (o == null)
            return -1;
        if (this.type == o.type)
            return this.iban.compareTo(o.iban);
        return -1;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.operationDate);
        hash = 79 * hash + Objects.hashCode(this.valuteDate);
        hash = 79 * hash + Objects.hashCode(this.descr);
        hash = 79 * hash + Objects.hashCode(this.iban);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.importo) ^ (Double.doubleToLongBits(this.importo) >>> 32));
        hash = 79 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (obj instanceof Movimento mov)
            return this.id == mov.id ;
        
        return false;
    }
    


    
}
