/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

import java.util.Objects;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
/**
 *
 * @author gambaro.lorenzo
 */
public class Banca 
{
    private final String name;
    private final String location;
    private final String checkDigits;
    private final char nationalLetter;
    private final String cab;
    private final String abi;
    private final Set<Conto> conti;
    
    public Banca(final String name, final String location, final char nationalLetter, final String checkDigits, final String abi, final String cab)
    {
        this.name = Objects.requireNonNull(name);
        
        if (location.length() != 2)
            throw new IllegalArgumentException("Location characters not valid!");
        
        for (int i = 0; i < location.length(); i++)
            if (!Character.isUpperCase(location.charAt(i)))
                throw new IllegalArgumentException("Location characters not valid!");
        this.location = Objects.requireNonNull(location);
        
        if (!Character.isUpperCase(nationalLetter))
            throw new IllegalArgumentException("National code not valid!");
        this.nationalLetter = nationalLetter;
        
        if (checkDigits.length() != 2)
            throw new IllegalArgumentException("Check digits not valid!");
        
        for (int i = 0; i < checkDigits.length(); i++)
            if (!Character.isDigit(checkDigits.charAt(i)))
                throw new IllegalArgumentException("Chech digits not valid!");
        this.checkDigits = Objects.requireNonNull(checkDigits);
        
        if (cab.length() != 5)
            throw new IllegalArgumentException("Cab code not valid!");
        for (int i = 0; i < cab.length(); i++)
            if (!Character.isDigit(cab.charAt(i)) && !Character.isUpperCase(cab.charAt(i)) && !Character.isLowerCase(cab.charAt(i)))
                throw new IllegalArgumentException("Cab code not valid!");
        this.cab = Objects.requireNonNull(cab);
        
        if (abi.length() != 5)
            throw new IllegalArgumentException("Abi code not valid!");
        for (int i = 0; i < abi.length(); i++)
            if (!Character.isDigit(abi.charAt(i)) && !Character.isUpperCase(abi.charAt(i)) && !!Character.isLowerCase(abi.charAt(i)))
                throw new IllegalArgumentException("Abi code not valid!");
        this.abi = Objects.requireNonNull(abi);
        
        final Comparator<Conto> cmp = (final Conto lhs, final Conto rhs) -> 
        {
            if (lhs == null && rhs == null)
                return 0;
            if (lhs == null)
                return -1;
            
            return lhs.getIban().compareTo(rhs.getIban());
        };
        
        this.conti = new TreeSet<>(cmp);
    }
    public Conto newConto(final Intestatario... intestatari)
    {
        final Conto c = new Conto(generateIban(String.valueOf(this.conti.size() + 1)), LocalDate.now(), intestatari);
        this.conti.add(c);
        
        return c;
    }
    public void extinguishConto(final Iban iban)
    {
        final Conto c = this.findConto(iban);
        
        if (c == null)
            throw new IllegalArgumentException("Account not found for iban " + iban.toString() + "!");
        
        if (c.isOpen())
           c.extinguish();
    }
    public Conto findConto(final Iban iban)
    {
        final Conto conto = this.conti.stream().filter((final Conto c) -> Objects.requireNonNull(iban).equals(c.getIban())).findFirst().orElse(null);
        return conto;
    }
    private Iban generateIban(final String beneficiaryCode)
    {
        final StringBuilder sb = new StringBuilder(this.location).append(this.checkDigits).append(this.nationalLetter).append(this.abi).append(this.cab);
        
        for (int i = 0; i < 12 - beneficiaryCode.length(); i++)
            sb.append(0);
        
        return new Iban(sb.append(beneficiaryCode).toString());
    }
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCheckDigits() {
        return checkDigits;
    }

    public char getNationalLetter() {
        return nationalLetter;
    }

    public String getCab() {
        return cab;
    }

    public String getAbi() {
        return abi;
    }

    @Override
    public String toString() 
    {
        final StringBuilder sb = new StringBuilder("Banca ").append(this.name).append(": {");
        
        sb.append("\n\tLocation: ").append(this.location);
        sb.append("\n\tCheck Digits: ").append(this.checkDigits);
        sb.append("\n\nNational Letter: ").append(this.nationalLetter);
        sb.append("\n\nCAB: ").append(this.cab);
        sb.append("\n\nABI: ").append(this.abi);
        sb.append("\n\tConti: ").append(this.conti);
        
        return sb.toString();
    }
    

    
}
