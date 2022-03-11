package com.algaworks.deliveredapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException resourceNotFoundException, HttpServletRequest httpServletRequest) {
        String error = "OBJECT NOT FOUND EXCEPTION";
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardError erro = new StandardError(Instant.now(), httpStatus.value(), error, resourceNotFoundException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> errorBeanValidation(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest httpServletRequest) {
        String error = "BEAN VALIDATION";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError erro = new StandardError(Instant.now(), httpStatus.value(), error, methodArgumentNotValidException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(erro);
    }

    @ExceptionHandler(AlredyEmailExistException.class)
    public ResponseEntity<StandardError> errorEmailAlredyExist(AlredyEmailExistException alredyEmailExist, HttpServletRequest httpServletRequest) {
        String error = "E-MAIL ALREDY EXIST";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError erro = new StandardError(Instant.now(), httpStatus.value(), error, alredyEmailExist.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(erro);
    }

    @ExceptionHandler(StateDeliveyException.class)
    public ResponseEntity<StandardError> errorStateDelivery(StateDeliveyException stateDeliveyException, HttpServletRequest httpServletRequest) {
        String error = "State Incorret";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError erro = new StandardError(Instant.now(), httpStatus.value(), error, stateDeliveyException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(erro);
    }

    @ExceptionHandler(AddressAlredyExistException.class)
    public ResponseEntity<StandardError> addresAlredyExist(AddressAlredyExistException addressAlredyExist, HttpServletRequest httpServletRequest) {
        String error = "Address Alredy Exist";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError erro = new StandardError(Instant.now(), httpStatus.value(), error, addressAlredyExist.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(erro);
    }
}