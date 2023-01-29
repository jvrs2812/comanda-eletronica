package com.comanda.comanda.Category.Adpter;

import com.comanda.comanda.Category.Domain.CategoryBaseDto;
import com.comanda.comanda.Category.Domain.CategoryGetDto;
import com.comanda.comanda.Category.repository.CategoryModelRepository;
import com.comanda.comanda.Category.repository.CategoryRepository;
import com.comanda.comanda.Table.domain.TableResponseDto;
import com.comanda.comanda.utils.commom.ResponsePageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AdpterCategory implements IAdpterCategory{

    @Autowired
    private CategoryRepository _repo;
    @Override
    public ResponsePageable<CategoryGetDto> getAll(int page) {
        Pageable pageable = PageRequest.of(page - 1, 20);

        List<CategoryGetDto> lista = new ArrayList<CategoryGetDto>();

        Page<CategoryModelRepository> model = _repo.findAll(pageable);

        for (int i = 0; i < model.stream().count(); i++) {
            lista.add(model.getContent().get(i).convertToDomain());
        }

        ResponsePageable<CategoryGetDto> response = new ResponsePageable<CategoryGetDto>(lista, page, model.getTotalPages());

        return response;
    }

    @Override
    public void save(CategoryBaseDto dto) {
        _repo.save(CategoryModelRepository.convertToModel(dto, true));
    }

    @Override
    public boolean exists(String id) {
        return _repo.existsById(UUID.fromString(id));
    }

    @Override
    public void del(UUID id) {
        _repo.deleteById(id);
    }

    @Override
    public CategoryGetDto getById(UUID id) {
        return _repo.getById(id).convertToDomain();
    }
}
