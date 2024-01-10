/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

 
/**
 *
 * @author gambaro.lorenzo
 */
public class Movimento extends TipoMovimento
{
    private static long globalNumber = 0;
    private final long id;
    private final LocalDate operationDate;
    private final LocalDate valuteDate;
    private final String iban;
    private final double importo;

    public Movimento(final LocalDate operationDate, final LocalDate valuteDate, final String descr, final String iban, final double importo)
    {
        super(globalNumber + 1, descr, ChronoUnit.DAYS.between(operationDate, valuteDate), 12);
        this.id = globalNumber++;
        this.operationDate = operationDate;
        this.valuteDate = valuteDate;
        this.iban = iban;
        this.importo = importo;
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

    public String getIban() {
        return iban;
    }

    public double getImporto() {
        return importo;
    }
    
}
