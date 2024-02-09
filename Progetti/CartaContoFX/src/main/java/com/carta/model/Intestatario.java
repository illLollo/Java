
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package com.carta.model;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/**
 *
 * @author gambaro.lorenzo
 */
public class Intestatario implements Comparable<Intestatario>, Serializable
{
    private final String username;
    private final String password;
    private final String cf;
    private final String cognome;
    private final String nome;
    private final LocalDate birthdate;
    private Indirizzo address;
    private String phoneNumber;
    private String emailAddress;
    private TipoIntestatario power;
    private Map<Iban, Conto> contiAssociati;
    
    public Intestatario(final String username, final String password, final TipoIntestatario power, final String cf, final String cognome, final String nome, final LocalDate birthDate, final Indirizzo address, final String phoneNumber, final String email)
    {
        this.username = Objects.requireNonNull(username);
        this.password = calcolaHash(Objects.requireNonNull(password));
        this.power = Objects.requireNonNull(power);
        
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
        
        this.contiAssociati = new HashMap<>();
    }
    public void addConto(final Conto c)
    {
        if (this.contiAssociati.containsKey(Objects.requireNonNull(c).getIban()))
            throw new IllegalArgumentException("Given Conto already marked belonging to this Intestatario!");
        
        if (!c.getIntestatari().contains(this))
            throw new IllegalArgumentException("Given conto does not belong to this Intestatario!");
        
        this.contiAssociati.put(c.getIban(), c);
    }
    public String getUsername() 
    {
        return this.username;
    }
    public TipoIntestatario getPower()
    {
        return this.power;
    }
    public String getHashedPassword()
    {
       return this.password;
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
    public int compareTo(Intestatario o) 
    {
        if (o == this)
            return 0;
        if (o == null)
            return -1;
         
        return o.getCf().compareTo(this.getCf());
    }
    @Override 
    public String toString()
    {
        return new StringBuilder(this.cognome).append(' ').append(this.nome).toString();
    }

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.cf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Intestatario other = (Intestatario) obj;
        return Objects.equals(this.cf, other.cf);
    }

    
    protected static String calcolaHash(final String password) 
    {
        try 
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            final StringBuilder sb = new StringBuilder();
            for (byte b : md.digest(password.getBytes()))
                sb.append(String.format("%02x", b));
            
            return sb.toString();
        } 
        catch (final NoSuchAlgorithmException ex) 
        {
            System.err.println(ex);
            return null;
        }
    }
}

