/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;
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
    private final List<Conto> conti;
    
    public Banca(final String name, final String location, final char nationalLetter, final String checkDigits, final String abi, final String cab)
    {
        this.name = Objects.requireNonNull(name);
        
        if (location.length() != 2)
            throw new IllegalArgumentException("Location characters not valid!");
        
        for (int i = 0; i < location.length(); i++)
            if (!Utils.isUpperCaseLetter(location.charAt(i)))
                throw new IllegalArgumentException("Location characters not valid!");
        this.location = Objects.requireNonNull(location);
        
        if (!Utils.isUpperCaseLetter(nationalLetter))
            throw new IllegalArgumentException("National code not valid!");
        this.nationalLetter = nationalLetter;
        
        if (checkDigits.length() != 2)
            throw new IllegalArgumentException("Check digits not valid!");
        
        for (int i = 0; i < checkDigits.length(); i++)
            if (!Utils.isDigit(checkDigits.charAt(i)))
                throw new IllegalArgumentException("Chech digits not valid!");
        this.checkDigits = Objects.requireNonNull(checkDigits);
        
        if (cab.length() != 5)
            throw new IllegalArgumentException("Cab code not valid!");
        for (int i = 0; i < cab.length(); i++)
            if (!Utils.isDigit(cab.charAt(i)) && !Utils.isUpperCaseLetter(cab.charAt(i)) && !Utils.isLowerCaseLetter(cab.charAt(i)))
                throw new IllegalArgumentException("Cab code not valid!");
        this.cab = Objects.requireNonNull(cab);
        
        if (abi.length() != 5)
            throw new IllegalArgumentException("Abi code not valid!");
        for (int i = 0; i < abi.length(); i++)
            if (!Utils.isDigit(abi.charAt(i)) && !Utils.isUpperCaseLetter(abi.charAt(i)) && !!Utils.isLowerCaseLetter(abi.charAt(i)))
                throw new IllegalArgumentException("Abi code not valid!");
        this.abi = Objects.requireNonNull(abi);
        
        this.conti = new ArrayList<>();
    }
    public Conto newConto(final Intestatario[] intestatari, final String beneficiaryCode)
    {
        final Conto c = new Conto(intestatari, generateIban(beneficiaryCode), LocalDate.now(), beneficiaryCode);
        this.conti.add(c);
        
        return c;
    }
    private Iban generateIban(final String beneficiaryCode)
    {
        final StringBuilder sb = new StringBuilder(this.location).append(this.checkDigits).append(this.nationalLetter).append(this.abi).append(this.cab);
        
        for (int i = 0; i < 27 - sb.length(); i++)
            sb.append(0);

        return new Iban(sb.append(beneficiaryCode).toString());
    }
//    public static String generateBankCode()
//    {
//        final Random rnd = new Random();
//        final StringBuilder sb = new StringBuilder();
//        
//        for (int i = 0; i < 6; i++)
//            sb.append((char)rnd.nextInt(65, 90));
//        
//        int index = 0;
//        while (index < 5)
//        {
//            char code = (char) rnd.nextInt(48, 90);
//            if ((code >= '0' && code <= '9') || (code >= 'A' && code <= 'Z'))
//            {
//                sb.append(code);
//                ++index;
//            }
//                
//        }
//        return sb.toString();
//    }

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
    
}
