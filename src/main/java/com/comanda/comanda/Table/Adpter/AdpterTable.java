package com.comanda.comanda.Table.Adpter;

import com.comanda.comanda.Product.domain.ProductGetDto;
import com.comanda.comanda.Table.Repository.TableModelRepository;
import com.comanda.comanda.Table.Repository.TableRepository;
import com.comanda.comanda.Table.domain.TableBaseDto;
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
public class AdpterTable implements IAdpterTable{

    @Autowired
    private TableRepository _repo;

    @Override
    public void save(TableBaseDto dto) {
        _repo.save(TableModelRepository.convertToModel(dto));
    }

    @Override
    public boolean existIdentification(String ind) {
        return _repo.existIdentification(ind);
    }

    @Override
    public ResponsePageable<TableResponseDto> getAll(int page) {

        Pageable pageable = PageRequest.of(page - 1, 20);

        Page<TableModelRepository> lista = _repo.getAll(pageable);

        List<TableResponseDto> listaDomain = new ArrayList<TableResponseDto>();

        for (int i = 0; i < lista.getContent().stream().count() ; i++) {
            listaDomain.add(lista.getContent().get(i).convertToDomain());
        }

        ResponsePageable<TableResponseDto> response = new ResponsePageable<TableResponseDto>(listaDomain, page, lista.getTotalPages());

        return response;
    }

    @Override
    public void delete(UUID id) {
        updateEnable(false, id);
    }

    @Override
    public void updateEnable(boolean enable, UUID id) {
        _repo.updateEnableTable(enable, id);
    }

    @Override
    public boolean isEnableIdentification(String id) {
        return _repo.isEnableIdentification(id);
    }

    @Override
    public UUID getIdWithIdentification(String ind) {
        return _repo.getIdWithIdentification(ind);
    }

    @Override
    public void updateAmountPeople(int amount, UUID id) {
        _repo.updateAmountPeople(amount, id);
    }

    @Override
    public boolean existTableWithID(UUID id) {
        return _repo.existsById(id);
    }
}
