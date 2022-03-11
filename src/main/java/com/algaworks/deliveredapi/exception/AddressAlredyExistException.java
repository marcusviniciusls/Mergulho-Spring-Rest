package com.algaworks.deliveredapi.exception;

public class AddressAlredyExistException extends RuntimeException{

    public AddressAlredyExistException(String message) {
        super(message);
    }
}
