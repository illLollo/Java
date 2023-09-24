package com.mycompany.calcolatricegrafica;

public final class Radice extends Operazione
{
    Radice(Double n) { super(n, 0); }
    
    @Override
    public Double calcola() { return Math.sqrt(this.getA()); }
}