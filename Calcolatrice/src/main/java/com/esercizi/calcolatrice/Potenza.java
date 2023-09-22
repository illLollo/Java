package com.esercizi.calcolatrice;

public final class Potenza extends Operazione
{
    Potenza(double a, double b) { super(a, b); }
    
    @Override
    public double calcola() { return Math.pow(this.getA(), this.getB()); }
}
