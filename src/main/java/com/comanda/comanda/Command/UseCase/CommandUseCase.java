package com.comanda.comanda.Command.UseCase;

import com.comanda.comanda.Command.Adpter.IAdpterCommand;
import com.comanda.comanda.Command.Domain.CommandBaseDto;
import com.comanda.comanda.Command.Domain.CommandGetDto;
import com.comanda.comanda.Command.Enum.CommandTypeEnum;
import com.comanda.comanda.Command.Exception.CommandException;
import com.comanda.comanda.Table.Adpter.IAdpterTable;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.Validations.Validations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommandUseCase {

    private final IAdpterCommand _adpter;


    private final IAdpterTable _adpter_Table;

    public void save(CommandBaseDto dto, String enterprise_id) throws ComandaException {
        if(CommandTypeEnum.values()[dto.getType()] == CommandTypeEnum.TABLE){
            if (dto.getTable_id() == null) throw new ComandaException(CommandException.COMMANDS_TABLE_IS_EMPTY);
            else if(!Validations.isValidId(dto.getTable_id())) throw new ComandaException(CommandException.COMMANDS_TABLE_ID_IS_INVALID);

            if(!_adpter_Table.existTableWithID(UUID.fromString(dto.getTable_id()))) throw new ComandaException(CommandException.COMMANDS_TABLE_ID_IS_NOT_FOUND);
        }

        _adpter.save(dto, enterprise_id);
    }

    public CommandGetDto getById(String id, String enterprise_id) throws ComandaException {
        if(!Validations.isValidId(id)) throw new ComandaException(CommandException.COMMANDS_ID_IS_INVALID);

        if(!_adpter.existsById(UUID.fromString(id), enterprise_id)) throw new ComandaException(CommandException.COMMANDS_NOT_FOUND);

        return _adpter.getById(UUID.fromString(id), enterprise_id);
    }
}
