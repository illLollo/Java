package com.strutturedati.strutturedati;

public class EmptyArrayException extends Exception
{
    EmptyArrayException(String message) { super(message); }
    EmptyArrayException() { super("Array Vuoto"); }
}

