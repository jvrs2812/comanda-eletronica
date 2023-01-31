package com.comanda.comanda.Commands.Adpter;

import com.comanda.comanda.Commands.Domain.CommandsBaseDto;
import com.comanda.comanda.Commands.Domain.CommandsGetDto;

import java.util.UUID;

public interface IAdpterCommands {
    void save(CommandsBaseDto dto);

    CommandsGetDto getById(UUID id);

    boolean existsById(UUID id);
}
