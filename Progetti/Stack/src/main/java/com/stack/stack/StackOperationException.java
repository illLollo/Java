/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stack.stack;

/**
 *
 * @author Administrator
 */
public class StackOperationException extends IllegalStateException
{
    public StackOperationException(final String message)
    {
        super(message);
    }
    public StackOperationException()
    {
        super("Stack Operation Exception!");
    }
}
