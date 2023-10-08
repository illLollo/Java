package com.mycompany.calcolatricegrafica;

public final class Divisione extends Operazione
{
    Divisione(Double a, Double b) { super(a, b); }
    Divisione() { }
    

    @Override
    public Double calcola() throws DivideByZeroException
    { 
        if (this.getB() == 0) throw new DivideByZeroException("Impossibile dividere per 0!");
        return this.getB() == 0 ? 0.0 : this.getA() / this.getB(); 
        
    }
}
