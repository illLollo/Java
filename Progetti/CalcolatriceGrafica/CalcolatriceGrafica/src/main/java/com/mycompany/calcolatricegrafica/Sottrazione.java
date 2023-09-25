package com.mycompany.calcolatricegrafica;

public final class Sottrazione extends Operazione
{
    Sottrazione(Double a, Double b) { super(a, b); }
    Sottrazione() {}

    @Override
    public Double calcola() { return this.getA() - this.getB(); }
}