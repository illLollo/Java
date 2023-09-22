package com.esercizi.calcolatrice;

public enum Operazioni
{
    ADDIZIONE('+'),
    SOTTRAZIONE('-'),
    MOLTIPLICAZIONE('*'),
    DIVISIONE('/'),
    POTENZA('^'),
    RADICEQUADRATA('|');
    
    private final char op;
    
    Operazioni(char operation) { this.op = operation; }
        
    @Override
    public String toString() { return new StringBuilder().append(this.op).toString(); }  
}
