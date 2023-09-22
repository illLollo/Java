package com.esercizi.calcolatrice;

public abstract class Operazione 
{
    private double a;
    private double b;
    
    Operazione(double a, double b) { this.a = a; this.b = b; }
    
    protected double getA() { return this.a; }
    protected double getB() { return this.b; }
    
    public abstract double calcola();
}
