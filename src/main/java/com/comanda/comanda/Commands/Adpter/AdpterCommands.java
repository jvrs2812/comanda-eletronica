package com.comanda.comanda.Commands.Adpter;

import com.comanda.comanda.Commands.Domain.CommandsBaseDto;
import com.comanda.comanda.Commands.Domain.CommandsGetDto;
import com.comanda.comanda.Commands.Repository.CommandsModelRepository;
import com.comanda.comanda.Commands.Repository.CommandsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AdpterCommands implements IAdpterCommands{
    @Autowired
    private CommandsRepository _repoComands;

    @Override
    public void save(CommandsBaseDto dto) {
        _repoComands.save(CommandsModelRepository.fromDto(dto));
    }

    @Override
    public CommandsGetDto getById(UUID id) {
        return _repoComands.findById(id).get().fromModel();
    }

    @Override
    public boolean existsById(UUID id) {
        return _repoComands.existsById(id);
    }
}
