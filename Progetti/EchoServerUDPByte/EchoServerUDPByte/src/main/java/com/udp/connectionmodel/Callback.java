/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.udp.connectionmodel;

/**
 *
 * @author Lorenzo
 */
@FunctionalInterface
public interface Callback<E> 
{
    void call(E parameter);
}
