/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
/**
 *
 * @author gambaro.lorenzo
 */
public class Banca implements Serializable
{
    private final String name;
    private final String location;
    private final String cab;
    private final String abi;
    private final Set<Conto> conti;
    
    public Banca(final String name, final String location, final String abi, final String cab)
    {
        this.name = Objects.requireNonNull(name);
        
        if (location.length() != 2)
            throw new IllegalArgumentException("Location characters not valid!");
        
        for (int i = 0; i < location.length(); i++)
            if (!Character.isUpperCase(location.charAt(i)))
                throw new IllegalArgumentException("Location characters not valid!");
        this.location = Objects.requireNonNull(location);
        
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
        final Conto c = new Conto(generateIban(this.location, this.abi, this.cab, String.valueOf(this.conti.size() + 1)), LocalDate.now(), intestatari);
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
    private static Iban generateIban(final String location, final String abi, final String cab, final String cc)
    {
        final StringBuilder ccn = new StringBuilder();
        
        for (int i = 0; i < 12 - cc.length(); i++)
            ccn.append(0);
        ccn.append(cc);
        
        final StringBuilder bbanSb = new StringBuilder().append(calculateCIN(abi, cab, ccn.toString())).append(abi).append(cab).append(ccn.toString());
                
        return new Iban(new StringBuilder(location).append(calculateCheckDigits(location, bbanSb.toString())).append(bbanSb).toString());
    }
    private static char calculateCIN(final String abi, final String cab, final String ccn)
    {
        final String bban = new StringBuilder(abi).append(cab).append(ccn).toString();
        
        final String aa = "A0B1C2D3E4F5G6H7I8J9K#L#M#N#O#P#Q#R#S#T#U#V#W#X#Y#Z#-#.# #";
        final String bb = "B1A0K#P#L#C2Q#D3R#E4V#O#S#F5T#G6U#H7M#I8N#J9W#Z#Y#X# #-#.#";
        
        int sum = 0;
        
        for (int i = 0; i < bban.length(); i += 2)
        {
            sum += Math.floorDiv(aa.indexOf(bban.charAt(i + 1)), 2);
            sum += Math.floorDiv(bb.indexOf(bban.charAt(i)), 2);
        }
        sum -= Math.floor(sum / 26) * 26;
        
        return aa.charAt(sum * 2);
    }
    private static String calculateCheckDigits(final String countryCode, final String bban) 
    {
        String fakeIban = countryCode + "00" + bban;

        fakeIban = fakeIban.toUpperCase();
        fakeIban = fakeIban.substring(4) + fakeIban.substring(0, 4);
        
        final StringBuilder sb = new StringBuilder();
        
        for (char c : fakeIban.toCharArray())
            if (Character.isLetter(c))
                sb.append((int) c - 55);
            else
                sb.append(c);
           

        final BigInteger remainder = new BigInteger(sb.toString()).mod(BigInteger.valueOf(97));
        final int checkDigits = 98 - remainder.intValue();

        return String.format("%02d", checkDigits);
    }
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
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
        sb.append("\n\nCAB: ").append(this.cab);
        sb.append("\n\nABI: ").append(this.abi);
        sb.append("\n\tConti: ").append(this.conti);
        
        return sb.toString();
    }
    

    
}
