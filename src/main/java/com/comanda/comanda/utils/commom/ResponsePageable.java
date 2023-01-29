package com.comanda.comanda.utils.commom;

import java.util.List;

public class ResponsePageable<T> {

    public ResponsePageable(List<T> lista, int currentPage, int totalPage) {
        this.lista = lista;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

    private List<T> lista;

    private int currentPage;

    private int totalPage;

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
