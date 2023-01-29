package com.comanda.comanda.Category.Exception;

import com.comanda.comanda.utils.Interface.ErrorInterface;
import org.springframework.http.HttpStatus;

public enum CategoryException implements ErrorInterface {
    CATEGORY_ID_EXCEPTION("category id is invalid", HttpStatus.BAD_REQUEST.value()),
    CATEGORY_NOT_FOUND("category is not found", HttpStatus.NOT_FOUND.value()),
    CATEGORY_PRODUCT_EXIST("there are products with this category ", HttpStatus.BAD_REQUEST.value());


    private final String msg;

    private final int StatusCode;

    CategoryException(String msg, int statusCode) {
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
