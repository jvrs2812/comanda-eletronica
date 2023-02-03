package com.comanda.comanda.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class HandleValidationException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseSchema<String> handleValidationException(MethodArgumentNotValidException ex){
        List<String> lista = new ArrayList<String>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            lista.add(error.getDefaultMessage());
        });

        return new ResponseSchema<String>(lista);
    }

    @ExceptionHandler(ComandaException.class)
    public ResponseEntity<ResponseSchema<String>> handleComanda(ComandaException ex){
        return new ResponseEntity<ResponseSchema<String>>(new ResponseSchema<String>(ex.listError()), HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseSchema<String>> handleDefault(Exception ex){
        List<String> lista = new ArrayList<String>();

        lista.add(ex.getMessage());

        return new ResponseEntity<ResponseSchema<String>>(new ResponseSchema<String>(lista), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseSchema<String> handleUploadSizeException(MissingServletRequestPartException ex){
        List<String> lista = new ArrayList<String>();

        lista.add(ex.getMessage());

        return new ResponseSchema<String>(lista);
    }

}
