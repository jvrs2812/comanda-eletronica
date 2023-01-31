package com.comanda.comanda.Commands.UseCase;

import com.comanda.comanda.Commands.Adpter.IAdpterCommands;
import com.comanda.comanda.Commands.Domain.CommandsBaseDto;
import com.comanda.comanda.Commands.Domain.CommandsGetDto;
import com.comanda.comanda.Commands.Enum.CommandsTypeEnum;
import com.comanda.comanda.Commands.Exception.CommandsException;
import com.comanda.comanda.Table.Adpter.IAdpterTable;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.Validations.Validations;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommandsUseCase {
    @Autowired
    private IAdpterCommands _adpter;

    @Autowired
    private IAdpterTable _adpter_Table;

    public void save(CommandsBaseDto dto) throws ComandaException {
        if(CommandsTypeEnum.values()[dto.getType()] == CommandsTypeEnum.TABLE){
            if (dto.getTable_id() == null) throw new ComandaException(CommandsException.COMMANDS_TABLE_IS_EMPTY);
            else if(!Validations.isValidId(dto.getTable_id())) throw new ComandaException(CommandsException.COMMANDS_TABLE_ID_IS_INVALID);

            if(!_adpter_Table.existTableWithID(UUID.fromString(dto.getTable_id()))) throw new ComandaException(CommandsException.COMMANDS_TABLE_ID_IS_NOT_FOUND);
        }

        _adpter.save(dto);
    }

    public CommandsGetDto getById(String id) throws ComandaException {
        if(!Validations.isValidId(id)) throw new ComandaException(CommandsException.COMMANDS_ID_IS_INVALID);

        if(!_adpter.existsById(UUID.fromString(id))) throw new ComandaException(CommandsException.COMMANDS_NOT_FOUND);

        return _adpter.getById(UUID.fromString(id));
    }
}
