/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carta.cartaconto;

import java.util.Objects;

/**
 *
 * @author gambaro.lorenzo
 */
public class Indirizzo 
{
    private final String via;
    private final String numero;
    private final String cap;
    private final String provincia;
    
    public Indirizzo(final String via, final String numero, final String cap, final String provincia)
    {
        this.via = Objects.requireNonNull(via);
        this.numero = Objects.requireNonNull(numero);
        this.cap = Objects.requireNonNull(cap);
        this.provincia = Objects.requireNonNull(provincia);
    }

    public String getVia() {
        return via;
    }

    public String getNumero() {
        return numero;
    }

    public String getCap() {
        return cap;
    }

    public String getProvincia() {
        return provincia;
    }
    
}
