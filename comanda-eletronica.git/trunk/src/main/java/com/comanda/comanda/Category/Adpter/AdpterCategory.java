package com.comanda.comanda.Category.Adpter;

import com.comanda.comanda.Category.Domain.CategoryBaseDto;
import com.comanda.comanda.Category.Domain.CategoryGetDto;
import com.comanda.comanda.Category.repository.CategoryModelRepository;
import com.comanda.comanda.Category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AdpterCategory implements IAdpterCategory{

    @Autowired
    private CategoryRepository _repo;
    @Override
    public List<CategoryGetDto> getAll() {
        List<CategoryGetDto> lista = new ArrayList<CategoryGetDto>();

        List<CategoryModelRepository> model = _repo.findAll();

        for (int i = 0; i < model.size(); i++) {
            lista.add(model.get(i).convertToDomain());
        }

        return lista;
    }

    @Override
    public void save(CategoryBaseDto dto) {
        _repo.save(CategoryModelRepository.convertToModel(dto, true));
    }

    @Override
    public boolean exists(String id) {
        return _repo.existsById(UUID.fromString(id));
    }
}
