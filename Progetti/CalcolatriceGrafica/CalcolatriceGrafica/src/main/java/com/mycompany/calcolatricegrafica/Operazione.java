
package com.mycompany.calcolatricegrafica;

public abstract class Operazione 
{
    private Double a;
    private Double b;
    
    Operazione(double a, double b) { this.a = a; this.b = b; }
    Operazione() {}
    
    protected Double getA() { return this.a; }
    protected Double getB() { return this.b; }
    protected void setA(Double a) { this.a = a; }
    protected void setB(Double b) { this.b = b; }
    
    public abstract Double calcola();
}

