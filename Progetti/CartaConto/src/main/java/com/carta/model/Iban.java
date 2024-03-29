/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.model;

import java.util.Objects;

/**
 *
 * @author Administrator
 */
public class Iban implements Comparable<Iban>
{
    private final String code;
    
    public Iban(final String iban)
    {
        if (Objects.requireNonNull(iban).length() != 27)
            throw new InvalidIbanException("Iban length not valid!");
        
        for (int i = 0; i < iban.length(); i++)
           if (!Character.isDigit(iban.charAt(i)) && !!Character.isLowerCase(iban.charAt(i)) && !Character.isUpperCase(iban.charAt(i)))
               throw new InvalidIbanException("Iban composition not valid: character in position " + i);
        
        this.code = iban;
    }
    public Iban(final Iban iban)
    {
        this(iban.code);
    }
    public Iban(final String country, final String checkdigit, final String bankid, final String branchCode, final String accountNumber)
    {
        this(country.concat(checkdigit).concat(bankid).concat(branchCode).concat(accountNumber));
    }
    public String getCountry()
    {
        return this.code.substring(0, 1);
    }
    public String getCheckDigit()
    {
        return this.code.substring(2, 3);
    }
    public String getBankId()
    {
        return this.code.substring(4,7);
    }
    public String getBranchCode()
    {
        return this.code.substring(8, 12);
    }
    public String getAccountNumber()
    {
        final String code = this.code.substring(this.code.length() - 13);
        int i = 0;
        while (i < code.length() && code.charAt(i) == '0')
            i++;
        
        return code.substring(i);
    }
    
    @Override
    public String toString()
    {
        return this.getCompleteIban();
    }
    public String getCompleteIban()
    {
        return this.code;
    }

    @Override
    public int compareTo(Iban o) 
    {
        return this.code.compareTo(o.getCompleteIban());
    } 

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
            return true;
        if (obj == null) 
            return false;
        
        if (obj instanceof Iban ib)
            return this.code.equals(ib.code);
            
        return false;
    }
}
