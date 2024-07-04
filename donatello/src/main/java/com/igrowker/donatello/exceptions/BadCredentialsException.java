package com.igrowker.donatello.exceptions;

public class BadCredentialsException extends RuntimeException{

    private static final String DESCRIPTION = "Error de credenciales";

    public BadCredentialsException () { super(DESCRIPTION); }
}
