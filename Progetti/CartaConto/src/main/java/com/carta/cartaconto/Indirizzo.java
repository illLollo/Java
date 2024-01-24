/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author gambaro.lorenzo
 */
public class Indirizzo implements Serializable
{
    private final String via;
    private final String numero;
    private final String cap;
    private final String comune;
    private final String provincia;
    
    public Indirizzo(final String via, final String numero, final String cap, final String comune, final String provincia)
    {
        this.via = Objects.requireNonNull(via);
        this.numero = Objects.requireNonNull(numero);
        this.cap = Objects.requireNonNull(cap);
        this.provincia = Objects.requireNonNull(provincia);
        this.comune = Objects.requireNonNull(comune);
    }

    public String getVia() 
    {
        return this.via;
    }

    public String getNumero() 
    {
        return this.numero;
    }

    public String getCap() 
    {
        return this.cap;
    }

    public String getProvincia() 
    {
        return this.provincia;
    }

    public String getComune() 
    {
        return this.comune;
    }
    
}
