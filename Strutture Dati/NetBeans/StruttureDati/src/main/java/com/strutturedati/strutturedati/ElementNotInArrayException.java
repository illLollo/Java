package com.strutturedati.strutturedati;

public final class ElementNotInArrayException extends Exception
{
    ElementNotInArrayException(String s) { super(s); }
    ElementNotInArrayException() { super("Elemento non nell'array!"); }
}
