/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.chat;

import java.io.Serializable;
import java.net.InetAddress;

/**
 *
 * @author gambaro.lorenzo
 */
public class Connection implements Serializable
{
    private final InetAddress address;
    private final int port;
    
    public Connection(final InetAddress address, final int port)
    {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return this.address;
    }

    public int getPort() {
        return this.port;
    }
}
