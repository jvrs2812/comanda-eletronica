package com.comanda.comanda.Table.Adpter;

import com.comanda.comanda.Table.domain.TableBaseDto;
import com.comanda.comanda.Table.domain.TableResponseDto;
import com.comanda.comanda.utils.commom.ResponsePageable;

import java.util.UUID;

public interface IAdpterTable {
    void save(TableBaseDto dto);

    boolean existIdentification(String ind);

    ResponsePageable<TableResponseDto> getAll(int page);

    void delete(UUID id);

    void updateEnable(boolean enable, UUID id);

    boolean isEnableIdentification(String id);

    UUID getIdWithIdentification(String ind);

    void updateAmountPeople(int amount, UUID id);
}
