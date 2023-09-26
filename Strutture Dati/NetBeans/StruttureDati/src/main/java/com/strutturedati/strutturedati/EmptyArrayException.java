package com.strutturedati.strutturedati;

public final class EmptyArrayException extends Exception
{
    EmptyArrayException(String message) { super(message); }
    EmptyArrayException() { super("Array Vuoto"); }
}

