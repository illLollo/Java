package com.esercizi.calcolatrice;

public final class Sottrazione extends Operazione
{
    Sottrazione(double a, double b) { super(a, b); }

    @Override
    public double calcola() { return this.getA() - this.getB(); }
}