package com.comanda.comanda.utils.enums;

import com.comanda.comanda.utils.Interface.ErrorInterface;
import org.springframework.http.HttpStatus;

public enum EnumException implements ErrorInterface {
    SERVER_ERROR("server error", 500),
    INVALID_PAGE("invalid page", HttpStatus.BAD_REQUEST.value()),
    INVALID_EXTENSION("invalid extension", HttpStatus.BAD_REQUEST.value()),

    INVALID_FILE_SIZE("invalid size", HttpStatus.BAD_REQUEST.value());

    private final String msg;

    private final int statusCode;

    EnumException(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }
}
