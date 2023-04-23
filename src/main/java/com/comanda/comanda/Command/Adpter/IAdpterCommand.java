package com.comanda.comanda.Command.Adpter;

import com.comanda.comanda.Command.Domain.CommandBaseDto;
import com.comanda.comanda.Command.Domain.CommandGetDto;

import java.util.UUID;

public interface IAdpterCommand {
    public void save(CommandBaseDto dto, String enterprise_id);

    CommandGetDto getById(UUID id, String enterprise_id);

    boolean existsById(UUID id, String enterprise_id);
}
