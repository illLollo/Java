package com.esercizi.calcolatrice;

public final class Radice extends Operazione
{
    Radice(double n) { super(n, 0); }
    
    @Override
    public double calcola() { return Math.sqrt(this.getA()); }
}