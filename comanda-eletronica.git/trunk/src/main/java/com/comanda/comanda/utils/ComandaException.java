package com.comanda.comanda.utils;


import com.comanda.comanda.utils.Interface.ErrorInterface;

import java.util.ArrayList;
import java.util.List;

public class ComandaException extends Exception{
    private final int StatusCode;
    public ComandaException(ErrorInterface exception){
        super(exception.getMsg());
        this.StatusCode = exception.getStatusCode();
    }

    public List<String> listError(){
        List<String> errorList = new ArrayList<String>();

        errorList.add(this.getMessage());
        return errorList;
    }

    public int getStatusCode() {
        return StatusCode;
    }
}
