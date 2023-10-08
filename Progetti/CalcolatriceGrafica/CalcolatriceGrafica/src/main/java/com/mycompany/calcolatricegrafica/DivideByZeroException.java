package com.mycompany.calcolatricegrafica;

public final class DivideByZeroException extends Exception
{
    DivideByZeroException(String message) { super(message); }
    DivideByZeroException() { super("Impossibile dividere per 0!"); }
}
