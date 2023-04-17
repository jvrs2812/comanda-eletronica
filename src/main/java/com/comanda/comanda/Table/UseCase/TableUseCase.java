package com.comanda.comanda.Table.UseCase;

import com.comanda.comanda.Table.Adpter.IAdpterTable;
import com.comanda.comanda.Table.Exception.TableException;
import com.comanda.comanda.Table.domain.TableBaseDto;
import com.comanda.comanda.Table.domain.TableResponseDto;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.Configuration.JwtService;
import com.comanda.comanda.utils.Validations.Validations;
import com.comanda.comanda.utils.commom.ResponsePageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.UUID;

@Component
public class TableUseCase {

    @Autowired
    private IAdpterTable _adpter;

    @Autowired
    private JwtService jwtService;
    public void save(TableBaseDto dto) throws ComandaException {

        if(_adpter.existIdentification(dto.getIdentification())){
            if(_adpter.isEnableIdentification(dto.getIdentification())){
                throw new ComandaException(TableException.TABLE_IDENTIFICATION_EXIST);
            }else{
                UUID id = _adpter.getIdWithIdentification(dto.getIdentification());
                _adpter.updateEnable(true, id);
                _adpter.updateAmountPeople(dto.getAmount_people(), id);
                return;
            }
        };

        if(dto.getAmount_people() == 0) throw new ComandaException(TableException.TABLE_AMOUNT_PEOPLE_EMPTY);

        _adpter.save(dto);
    }

    public ResponsePageable<TableResponseDto> getAll(int page){
        return _adpter.getAll(page);
    }

    public void delete(String id) throws ComandaException {
        if(!Validations.isValidId(id)) throw new ComandaException(TableException.TABLE_ID_IS_INVALID);

        _adpter.delete(UUID.fromString(id));
    }
}
