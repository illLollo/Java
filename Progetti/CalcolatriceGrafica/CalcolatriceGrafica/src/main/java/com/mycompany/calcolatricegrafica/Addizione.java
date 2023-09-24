package com.mycompany.calcolatricegrafica;

public final class Addizione extends Operazione
{
    Addizione(Double a, Double b) { super(a, b); }
    Addizione() { } 
    
    @Override
    public Double calcola() { return this.getA() + this.getB(); }
}
