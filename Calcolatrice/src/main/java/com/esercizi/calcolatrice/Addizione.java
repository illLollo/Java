package com.esercizi.calcolatrice;

public final class Addizione extends Operazione
{
    Addizione(double a, double b) { super(a, b); }
    
    @Override
    public double calcola() { return this.getA() + this.getB(); }
}
