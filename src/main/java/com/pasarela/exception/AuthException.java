/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasarela.exception;

/**
 *
 * @author apolo
 */
public class AuthException extends Exception{
    public AuthException(String err) {
        super(err);
    }  
}
