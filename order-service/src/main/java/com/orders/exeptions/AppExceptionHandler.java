package com.orders.exeptions;


import jdk.jshell.spi.ExecutionControl;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value = ExecutionControl.UserException.class)
    public ResponseEntity<Object> handleUserException(ExecutionControl.UserException ex , WebRequest request){

        ErrorMessage errorMessages = new ErrorMessage( ex.getMessage());
        return new ResponseEntity<>(errorMessages, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleOtherException(Exception ex , WebRequest request){

        ErrorMessage errorMessages = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessages, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex , WebRequest request){
        Map<String, String> errorMessages = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(errr ->errorMessages.put(errr.getField(), errr.getDefaultMessage()));
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }
}