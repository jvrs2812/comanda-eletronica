package com.comanda.comanda.Category.Adpter;

import com.comanda.comanda.Category.Domain.CategoryBaseDto;
import com.comanda.comanda.Category.Domain.CategoryGetDto;

import java.util.List;

public interface IAdpterCategory {
    List<CategoryGetDto> getAll();

    void save(CategoryBaseDto dto);

    boolean exists(String id);
}
