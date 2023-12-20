/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

import java.time.LocalDate;

/**
 *
 * @author gambaro.lorenzo
 */
public class Conto 
{
    private String iban;
    private LocalDate openingDate;
    private String beneficiaryCode;
    
    public Conto(final String iban, final LocalDate openingDate, final String beneficiaryCode)
    {
        this.iban = iban;
        this.openingDate = openingDate;
        this.beneficiaryCode = beneficiaryCode;
    }
}
