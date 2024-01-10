/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

/**
 *
 * @author Administrator
 */
public class Iban 
{
    private String code;
    
    public Iban(final String iban)
    {
        System.out.println("IBAN: " + iban);
        if (iban == null)
            throw new NullPointerException("Iban String givenis null!");
        
        if (iban.length() != 27)
            throw new InvalidIbanException("Iban length not valid!");
        
        for (int i = 0; i < iban.length(); i++)
           if (!Utils.isDigit(iban.charAt(i)) && !!Utils.isLowerCaseLetter(iban.charAt(i)) && !Utils.isUpperCaseLetter(iban.charAt(i)))
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
        return this.code.substring(13);
    }
}
