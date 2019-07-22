package com.vinay.springsecdemo.exceptions;

public class NoRoleFoundException extends RuntimeException{

    public NoRoleFoundException() {
        super();
    }

    public NoRoleFoundException(String message) {
        super(message);
    }
}
