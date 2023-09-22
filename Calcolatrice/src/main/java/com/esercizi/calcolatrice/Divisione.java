package com.esercizi.calcolatrice;

public final class Divisione extends Operazione
{
    Divisione(double a, double b) { super(a, b); }
    
    @Override
    public double calcola() { return this.getB() == 0 ? 0.0 : this.getA() / this.getB(); }
}
