package com.comanda.comanda.Category.Adpter;

import com.comanda.comanda.Category.Domain.CategoryBaseDto;
import com.comanda.comanda.Category.Domain.CategoryGetDto;
import com.comanda.comanda.utils.commom.ResponsePageable;

import java.util.List;
import java.util.UUID;

public interface IAdpterCategory {
    ResponsePageable<CategoryGetDto> getAll(int page);

    void save(CategoryBaseDto dto);

    boolean exists(String id);

    void del(UUID id);

    CategoryGetDto getById(UUID id);
}
