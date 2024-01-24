/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
/**
 *
 * @author gambaro.lorenzo
 */
public class Intestatario implements Comparable, Serializable
{
    private final String cf;
    private final String cognome;;
    private final String nome;
    private final LocalDate birthdate;
    private Indirizzo address;
    private String phoneNumber;
    private String emailAddress;
    
    public Intestatario(final String cf, final String cognome, final String nome, final LocalDate birthDate, final Indirizzo address, final String phoneNumber, final String email)
    {
        if (!Objects.requireNonNull(cf).matches("^[A-Za-z]{6}[0-9]{2}[A-Za-z]{1}[0-9]{2}[A-Za-z]{1}[0-9]{3}[A-Za-z]{1}$"))
            throw new IllegalArgumentException("Fiscal code not valid!");
        this.cf = cf;
        
        this.cognome = Objects.requireNonNull(cognome);
        this.nome = Objects.requireNonNull(nome);
        this.birthdate = Objects.requireNonNull(birthDate);
        this.address = Objects.requireNonNull(address);
        
        if (!Objects.requireNonNull(phoneNumber).matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$"))
            throw new IllegalArgumentException("Phone number not valid!");
        this.phoneNumber = phoneNumber;
        
        if (!Objects.requireNonNull(email).matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
            throw new IllegalArgumentException("Email not valid!");
        this.emailAddress = email;
    }

    public String getCf() 
    {
        return this.cf;
    }

    public String getCognome() 
    {
        return this.cognome;
    }

    public String getNome() 
    {
        return this.nome;
    }

    public LocalDate getBirthdate() 
    {
        return this.birthdate;
    }

    public Indirizzo getAddress() 
    {
        return this.address;
    }

    public String getPhoneNumber() 
    {
        return this.phoneNumber;
    }

    public String getEmailAddress() 
    {
        return this.emailAddress;
    }
    
    public void setAddress(final Indirizzo address) 
    {
        this.address = Objects.requireNonNull(address);
    } 

    public void setPhoneNumber(final String phoneNumber) 
    {
        if (!Objects.requireNonNull(phoneNumber).matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$"))
            throw new IllegalArgumentException("Phone number not valid!");
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(final String emailAddress) 
    {
        if (!Objects.requireNonNull(emailAddress).matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
            throw new IllegalArgumentException("Email not valid!");
        this.emailAddress = emailAddress;
    }

    @Override
    public int compareTo(Object o) 
    {
        if (o instanceof Intestatario i)
            return i.getCf().compareTo(this.getCf());
        return -1;
    }
    @Override 
    public String toString()
    {
        return new StringBuilder(this.cognome).append(' ').append(this.nome).toString();
    }
}
