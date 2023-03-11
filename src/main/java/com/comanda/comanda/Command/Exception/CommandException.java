package com.comanda.comanda.Command.Exception;

import com.comanda.comanda.utils.Interface.ErrorInterface;
import org.springframework.http.HttpStatus;

public enum CommandException implements ErrorInterface {
    COMMANDS_TABLE_IS_EMPTY("type is table but table id is empty", HttpStatus.BAD_REQUEST.value()),
    COMMANDS_TABLE_ID_IS_INVALID("table id is invalid", HttpStatus.BAD_REQUEST.value()),
    COMMANDS_TABLE_ID_IS_NOT_FOUND("table is not found", HttpStatus.NOT_FOUND.value()),
    COMMANDS_ID_IS_INVALID("commands id is invalid", HttpStatus.BAD_REQUEST.value()),
    COMMANDS_NOT_FOUND("comanda is not found", HttpStatus.NOT_FOUND.value());
    CommandException(String msg, int statusCode) {
        this.msg = msg;
        StatusCode = statusCode;
    }

    private String msg;

    private int StatusCode;



    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public int getStatusCode() {
        return this.StatusCode;
    }
}
