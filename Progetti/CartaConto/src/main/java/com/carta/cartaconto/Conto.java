/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

import java.time.LocalDate;
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
    
    public Conto(final Iban iban, final LocalDate openingDate, final String beneficiaryCode)
    {
        this.iban = Objects.requireNonNull(iban);
        this.openingDate = Objects.requireNonNull(openingDate);
        this.beneficiaryCode = Objects.requireNonNull(beneficiaryCode);
    }
    public Conto(final String iban, final LocalDate openingDate, final String beneficiaryCode)
    {
        this(new Iban(iban), openingDate, beneficiaryCode);
    }
    public Iban getIban() { return this.iban; }
    public LocalDate getOpeningDate() { return this.openingDate; }
    public String beneficiaryCode() { return this.beneficiaryCode; }
}
