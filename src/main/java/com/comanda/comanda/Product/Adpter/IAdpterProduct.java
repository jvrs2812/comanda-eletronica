package com.comanda.comanda.Product.Adpter;

import com.comanda.comanda.Enterprise.Repository.EnterpriseModelRepository;
import com.comanda.comanda.Product.domain.ProductBaseDto;
import com.comanda.comanda.Product.domain.ProductGetDto;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.commom.ResponsePageable;

import java.util.UUID;

public interface IAdpterProduct {
    ResponsePageable<ProductGetDto> getAll(int page, UUID enterpriseId);

    void save(ProductBaseDto dto, EnterpriseModelRepository enterpriseId);

    void put(String id, ProductBaseDto dto, EnterpriseModelRepository enterpriseId);

    boolean existProduct(UUID id);

    ProductGetDto getById(UUID id) throws ComandaException;

    void deletbyId(String id);

    boolean existwithCategoryId(UUID id);
}
