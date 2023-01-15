package com.comanda.comanda.Product.Exception;

public class PageException extends Exception{
    public PageException(String invalidPage) {
        super(invalidPage);
    }
}
