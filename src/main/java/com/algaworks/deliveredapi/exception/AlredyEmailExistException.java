package com.algaworks.deliveredapi.exception;

public class AlredyEmailExistException extends RuntimeException{

    public AlredyEmailExistException(String message) {
        super("E-mail Alredy Exist" + message);
    }
}
