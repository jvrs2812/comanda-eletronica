package com.comanda.comanda.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.ArrayList;
import java.util.List;

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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseSchema<String> handleUploadSizeException(MissingServletRequestPartException ex){
        List<String> lista = new ArrayList<String>();

        lista.add(ex.getMessage());

        return new ResponseSchema<String>(lista);
    }

}
