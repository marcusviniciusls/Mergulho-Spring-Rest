package com.algaworks.deliveredapi.exception;

public class AlredyEmailExist extends RuntimeException{

    public AlredyEmailExist(String message) {
        super("E-mail Alredy Exist" + message);
    }
}
