/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author gambaro.lorenzo
 */
public class Conto implements Comparable<Conto>
{
    private Set<Intestatario> intestatari;
    private Iban iban;
    private LocalDate openingDate;
    private String beneficiaryCode;
    private List<Movimento> movimenti;
    
    public Conto(final Intestatario[] intestatari, final Iban iban, final LocalDate openingDate, final String beneficiaryCode)
    {
        if (intestatari == null)
            throw new NullPointerException("Cannot be no intestataries!");
        this.intestatari = new TreeSet<>();
        
        for (final Intestatario temp : intestatari)
            this.intestatari.add(temp);
        
        this.iban = Objects.requireNonNull(iban);
        this.openingDate = Objects.requireNonNull(openingDate);
        this.beneficiaryCode = Objects.requireNonNull(beneficiaryCode);
        this.movimenti = new ArrayList<>();
    }
    public Conto(final Intestatario[] intestatari, final String iban, final LocalDate openingDate, final String beneficiaryCode)
    {
        this(intestatari, new Iban(iban), openingDate, beneficiaryCode);
    }
    public Iban getIban() { return this.iban; }
    public LocalDate getOpeningDate() { return this.openingDate; }
    public String beneficiaryCode() { return this.beneficiaryCode; }
    
    public double saldo()
    {
        double sum = 0;
        for (final Movimento temp : this.movimenti)
            sum += temp.getImporto();
        
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
        
        if (!movimenti.contains(m))
            this.movimenti.add(m);
    }
    @Override
    public String toString()
    {
        return new StringBuilder("Conto: ").append(this.iban).append("\n[\n\tIntestatari: ").append(this.intestatari).append("\n\tIban: ").append(this.iban).append(",\n\tOpening Date: ").append(this.openingDate).append(",\n\tBeneficiary Code: ").append(this.beneficiaryCode).append(",\n\tMovimenti:\n ").append(this.movimenti).append("\n}").toString();
    }

    @Override
    public int compareTo(Conto o) 
    {
        return this.getIban().compareTo(o.getIban());
    }

    public Set<Intestatario> getIntestatari() 
    {
        return this.intestatari;
    }
}
