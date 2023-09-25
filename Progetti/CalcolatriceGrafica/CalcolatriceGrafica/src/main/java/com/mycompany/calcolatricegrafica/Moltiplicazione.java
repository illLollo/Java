package com.mycompany.calcolatricegrafica;

public final class Moltiplicazione extends Operazione
{
    Moltiplicazione(Double a, Double b) { super(a, b); }
    Moltiplicazione() {  }
    
    @Override
    public Double calcola() { return this.getA() * this.getB(); }
}
