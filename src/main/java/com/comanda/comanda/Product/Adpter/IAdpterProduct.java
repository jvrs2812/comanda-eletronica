package com.comanda.comanda.Product.Adpter;

import com.comanda.comanda.Product.domain.ProductBaseDto;
import com.comanda.comanda.Product.domain.ProductGetDto;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.commom.ResponsePageable;

import java.util.UUID;

public interface IAdpterProduct {
    ResponsePageable<ProductGetDto> getAll(int page);

    void save(ProductBaseDto dto);

    void put(String id, ProductBaseDto dto);

    boolean existProduct(UUID id);

    ProductGetDto getById(UUID id) throws ComandaException;

    void deletbyId(String id);

    boolean existwithCategoryId(UUID id);
}
