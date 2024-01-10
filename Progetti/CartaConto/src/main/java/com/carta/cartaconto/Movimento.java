/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

 
/**
 *
 * @author gambaro.lorenzo
 */
public class Movimento extends TipoMovimento implements Comparable
{
    private static int globalNumber = 0;
    private final int id;
    private final LocalDate operationDate;
    private final LocalDate valuteDate;
    private final Iban iban;
    private final double importo;

    public Movimento(final LocalDate operationDate, final LocalDate valuteDate, final String descr, final Iban iban, final double importo, final double taxes)
    {
        super(globalNumber + 1, descr, ChronoUnit.DAYS.between(operationDate, valuteDate), taxes);
        this.id = globalNumber++;
        this.operationDate = Objects.requireNonNull(operationDate);
        this.valuteDate = Objects.requireNonNull(valuteDate);
        this.iban = Objects.requireNonNull(iban);
        this.importo = importo + taxes;
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
        return new StringBuilder("Movimento: \n{\n\t").append("Id: ").append(this.id).append("\n\tOperation Date: ").append(this.operationDate).append("\n\tValute Date: ").append(this.valuteDate).append("\n\tIban: ").append(this.iban).append("\n\tImport: ").append(this.importo).append("\n\tTaxes: ").append(super.getCost()).append("\n]").toString();
    }

    @Override
    public int compareTo(Object o)
    {
        if (o instanceof Movimento m)
            return this.id - m.id;
        return -1;
    }
    
}
