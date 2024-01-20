/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

import java.text.DecimalFormat;
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
public class Conto implements Comparable<Conto>, Extinguishable
{
    private Set<Intestatario> intestatari;
    private Iban iban;
    private LocalDate openingDate;
    private LocalDate closeDate;
    private List<Movimento> movimenti;
    
    public Conto(final Iban iban, final LocalDate openingDate, final Intestatario... intestatari)
    {
        if (intestatari == null)
            throw new NullPointerException("Cannot be no intestataries!");
        this.intestatari = new TreeSet<>();
        
        for (final Intestatario temp : intestatari)
            this.intestatari.add(temp);
        
        this.iban = Objects.requireNonNull(iban);
        this.openingDate = Objects.requireNonNull(openingDate);
        this.closeDate = null;
        this.movimenti = new ArrayList<>();
    }

    public Iban getIban() { return this.iban; }
    public LocalDate getOpeningDate() { return this.openingDate; }
    
    public double saldo()
    {
        double sum = 0;
        for (final Movimento temp : this.movimenti)
            sum += (temp.getImporto() * temp.getType().getAmount()) - temp.getType().getCost();
        
        return Double.parseDouble(new DecimalFormat("#.0").format(sum));
    }
    public boolean isOpen()
    {
        return this.closeDate == null;
    }
    public Movimento searchMovimento(final int id)
    {
        return this.movimenti.stream().filter((final Movimento m) -> id == m.getId()).findFirst().orElse(null);
    }
    public List<Movimento> getOperazioni()
    {
        return this.movimenti;
    }
    public void newOperazione(final TipoMovimento t, final double importo, final LocalDate valdate, final String desc)
    {
        if (!this.isOpen())
            throw new IllegalStateException("Cannot execute operation in a extinguished account!");
        
        if (t == null)
            throw new NullPointerException("The operation cannot be null!");
        
        final Movimento m = new Movimento(LocalDate.now(), Objects.requireNonNull(valdate), Objects.requireNonNull(desc), this.getIban(), importo, Objects.requireNonNull(t));
        
        this.movimenti.add(m);
        
    }
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Conto: ").append(this.iban);
        
        sb.append("\n{\n\tIntestatari: ").append(this.intestatari);
        sb.append("\n\tIban: ").append(this.iban);
        sb.append(",\n\tOpening Date: ").append(this.openingDate);
        sb.append(",\n\tMovimenti:\n ").append(this.movimenti);
        sb.append("\n}");
        
        return sb.toString();
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

    @Override
    public void extinguish() 
    {
        if (!this.isOpen())
            this.closeDate = LocalDate.now();
    }
}
