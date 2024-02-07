/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.model;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Administrator
 */
public class User implements Serializable, Comparable
{
    private static int globalUid = 0;
    private final int uid;
    private final String username;
    private final String hashPassword;
    protected final Set<Conto> conti;
    
    private static enum TipoUtente { ADMIN, DEFAULT_USER };
    
    private final TipoUtente tipo;
    
    public User(final String username, final String password, final TipoUtente power, final boolean needsHashing)
    {
        this.username = Objects.requireNonNull(username);
        this.hashPassword = Objects.requireNonNull(needsHashing ? calcolaHash(Objects.requireNonNull(password)) : Objects.requireNonNull(password));
        this.conti = new TreeSet<>();
        this.tipo = Objects.requireNonNull(power);
        this.uid = globalUid++;
    }
    public User(final String username, final String password, final TipoUtente power)
    {
        this(username, password, power, true);
    }
    public TipoUtente getType() { return this.tipo; }
    private static String calcolaHash(final String password) 
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
    public String getUsername() { return this.username; }
    public String getPassword() { return this.hashPassword; }
    public int getUid() { return uid; }
    

    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.username);
        hash = 37 * hash + Objects.hashCode(this.hashPassword);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)
            return true;
        
        if (obj instanceof User u)
            return this.getUsername().equals(u.getUsername()) && this.getPassword().equals(u.getPassword());
        
        return false;
    }

    @Override
    public int compareTo(Object o)
    {
        if (o == this)
            return 0;

        if (o instanceof User u)
            return u.getUsername().compareTo(this.getUsername()) + u.getPassword().compareTo(this.getPassword());
        return -1;
    }

    @Override
    public String toString() 
    {
        final StringBuilder sb = new StringBuilder("User: { ")
                .append("\n\tUID: ").append(this.uid)
                .append(",\n\tUsername: ").append(this.username)
                .append(",\n\tHashedPassword: ").append(this.hashPassword).append(" \n\t}");
        
        return sb.toString();
    }
    
    
}
