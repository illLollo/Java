package com.mycompany.calcolatricegrafica;

public final class Potenza extends Operazione
{
    Potenza(Double a, Double b) { super(a, b); }
    
    @Override
    public Double calcola() { return Math.pow(this.getA(), this.getB()); }
}
