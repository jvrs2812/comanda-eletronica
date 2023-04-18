package com.comanda.comanda.Enterprise.Exception;

import com.comanda.comanda.utils.Interface.ErrorInterface;
import org.springframework.http.HttpStatus;

public enum EnterpriseException implements ErrorInterface {
    ENTERPRISE_NOT_FOUND("enterprise not found", 404),
    ENTERPRISE_CNPJ_EXISTS("enterprise cnpj already exists", HttpStatus.BAD_REQUEST.value()),
    ENTERPRISE_ID_ERROR("enterprise id is invalid", HttpStatus.BAD_REQUEST.value());

    private final String msg;

    private final int statusCode;

    EnterpriseException(String msg, int statusCode) {
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
