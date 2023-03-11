package com.comanda.comanda.Command.Adpter;

import com.comanda.comanda.Command.Domain.CommandBaseDto;
import com.comanda.comanda.Command.Domain.CommandGetDto;

import java.util.UUID;

public interface IAdpterCommand {
    void save(CommandBaseDto dto);

    CommandGetDto getById(UUID id);

    boolean existsById(UUID id);
}
