package com.comanda.comanda.Product.Adpter;

import com.comanda.comanda.Product.Repository.ProductModelRepository;
import com.comanda.comanda.Product.Repository.ProductRepository;
import com.comanda.comanda.Product.domain.ProductAllResponse;
import com.comanda.comanda.Product.domain.ProductBaseDto;
import com.comanda.comanda.Product.domain.ProductGetDto;
import com.comanda.comanda.utils.ComandaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.comanda.comanda.Product.Exception.ProductException.PRODUCT_NOT_FOUND;

@Component
public class AdpterProduct implements IAdpterProduct {

    @Autowired
    private ProductRepository _repo;

    @Override
    public ProductAllResponse getAll(int page) {
        Pageable pageable = PageRequest.of(page - 1, 20);

        Page<ProductModelRepository> lista = _repo.findAll(pageable);

        List<ProductGetDto> listaDomain = new ArrayList<ProductGetDto>();

        for (int i = 0; i < lista.getContent().stream().count() ; i++) {
            listaDomain.add(lista.getContent().get(i).convertToDomain());
        }

        ProductAllResponse response = new ProductAllResponse(listaDomain, page, lista.getTotalPages());

        return response;
    }

    @Override
    public void save(ProductBaseDto dto) {
        _repo.save(ProductModelRepository.convertToModel(dto));
    }

    @Override
    public void put(String id, ProductBaseDto dto) {
        ProductModelRepository productModelRepository = ProductModelRepository.convertToModel(dto);

        productModelRepository.setId(UUID.fromString(id));

        _repo.save(productModelRepository);
    }

    @Override
    public boolean existProduct(UUID id) {
        return _repo.existsById(id);
    }

    @Override
    public ProductGetDto getById(UUID id) throws ComandaException {
        if(existProduct(id)){
            return _repo.getById(id).convertToDomain();
        }else{
            throw new ComandaException(PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public void deletbyId(String id) {
        _repo.deleteById(UUID.fromString(id));
    }
}
