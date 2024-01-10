/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author gambaro.lorenzo
 */
public class Conto 
{
    private Iban iban;
    private LocalDate openingDate;
    private String beneficiaryCode;
    private List<Movimento> movimenti;
    
    public Conto(final Iban iban, final LocalDate openingDate, final String beneficiaryCode)
    {
        this.iban = Objects.requireNonNull(iban);
        this.openingDate = Objects.requireNonNull(openingDate);
        this.beneficiaryCode = Objects.requireNonNull(beneficiaryCode);
        this.movimenti = new ArrayList<>();
    }
    public Conto(final String iban, final LocalDate openingDate, final String beneficiaryCode)
    {
        this(new Iban(iban), openingDate, beneficiaryCode);
    }
    public Iban getIban() { return this.iban; }
    public LocalDate getOpeningDate() { return this.openingDate; }
    public String beneficiaryCode() { return this.beneficiaryCode; }
    
    public double saldo()
    {
        double sum = 0;
        for (final Movimento temp : this.movimenti)
            sum += temp.getCost();
        
        return sum;
    }
    public List<Movimento> getOperazioni()
    {
        return this.movimenti;
    }
    public void newOperazione(final Movimento m)
    {
        if (m == null)
            throw new NullPointerException("The operation cannot be null!");
        this.movimenti.add(m);
    }
}
