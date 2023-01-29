package com.comanda.comanda.Product.Exception;

import com.comanda.comanda.utils.Interface.ErrorInterface;
import org.springframework.http.HttpStatus;

public enum ProductException implements ErrorInterface {
    PRODUCT_NOT_FOUND("product not found", 404),
    PRODUCT_ID_ERROR("product id is invalid", HttpStatus.BAD_REQUEST.value());



    private final String msg;

    private final int statusCode;

    ProductException(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }

    @Override
    public String getMsg() {
        return null;
    }

    @Override
    public int getStatusCode() {
        return 0;
    }
}
