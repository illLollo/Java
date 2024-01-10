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
public class Intestatario implements Comparable
{
    private String cf;
    private String cognome;
    private String nome;
    private LocalDate birthdate;
    private Indirizzo address;
    private String phoneNumber;
    private String emailAddress;
    
    public Intestatario(final String cf, final String cognome, final String nome, final LocalDate birthDate, final Indirizzo address, final String phoneNumber, final String email)
    {
        if (cf == null)
            throw new NullPointerException("Fiscal code cannot be null!");
        if (!cf.matches("^[A-Za-z]{6}[0-9]{2}[A-Za-z]{1}[0-9]{2}[A-Za-z]{1}[0-9]{3}[A-Za-z]{1}$"))
            throw new IllegalArgumentException("Fiscal code not valid!");
        this.cf = cf;
        
        this.cognome = Objects.requireNonNull(cognome);
        this.nome = Objects.requireNonNull(nome);
        this.birthdate = Objects.requireNonNull(birthDate);
        this.address = Objects.requireNonNull(address);
        
        if (phoneNumber == null)
            throw new NullPointerException("Phone number cannot be null!");
        if (!phoneNumber.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$"))
            throw new IllegalArgumentException("Phone number not valid!");
        this.phoneNumber = phoneNumber;
        
        if (email == null)
            throw new NullPointerException("Email cannot be null!");
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
            throw new IllegalArgumentException("Email not valid!");
        this.emailAddress = email;
    }

    public String getCf() {
        return cf;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Indirizzo getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    
    public void setAddress(final Indirizzo address) {
        this.address = address;
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
