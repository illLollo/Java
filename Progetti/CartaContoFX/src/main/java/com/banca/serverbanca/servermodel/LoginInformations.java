/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banca.serverbanca.servermodel;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Administrator
 */
public class LoginInformations implements Serializable
{
    private final String username;
    private final String hashedPassword;
    
    public LoginInformations(final String username, final String hashedPassword)
    {
        this.username = Objects.requireNonNull(username);
        this.hashedPassword = Objects.requireNonNull(hashedPassword);
    }

    public String getUsername() {
        return this.username;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }
    
        
}
