package com.esercizi.calcolatrice;

public final class Moltiplicazione extends Operazione
{
    Moltiplicazione(double a, double b) { super(a, b); }
    
    @Override
    public double calcola() { return this.getA() * this.getB(); }
}
