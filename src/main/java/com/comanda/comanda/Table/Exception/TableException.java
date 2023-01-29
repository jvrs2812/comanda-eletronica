package com.comanda.comanda.Table.Exception;

import com.comanda.comanda.utils.Interface.ErrorInterface;
import org.springframework.http.HttpStatus;

public enum TableException implements ErrorInterface {
    TABLE_IDENTIFICATION_EXIST("identification already exist", HttpStatus.BAD_REQUEST.value()),
    TABLE_AMOUNT_PEOPLE_EMPTY("amount people is empty", HttpStatus.BAD_REQUEST.value()),
    TABLE_ID_IS_INVALID("table id is invalid", HttpStatus.BAD_REQUEST.value());

    private final String msg;

    private final int StatusCode;

    TableException(String msg, int statusCode) {
        this.msg = msg;
        StatusCode = statusCode;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public int getStatusCode() {
        return this.StatusCode;
    }
}
