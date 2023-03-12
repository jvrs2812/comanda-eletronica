package com.comanda.comanda.User.Exception;

import com.comanda.comanda.utils.Interface.ErrorInterface;
import org.springframework.http.HttpStatus;

public enum UserException implements ErrorInterface {
    USER_EMAIL_EXIST("email already exist", HttpStatus.BAD_REQUEST.value());


    private final String msg;

    private final int StatusCode;

    UserException(String msg, int statusCode) {
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
