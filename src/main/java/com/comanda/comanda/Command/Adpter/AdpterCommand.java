package com.comanda.comanda.Command.Adpter;

import com.comanda.comanda.Command.Domain.CommandBaseDto;
import com.comanda.comanda.Command.Domain.CommandGetDto;
import com.comanda.comanda.Command.Repository.CommandModelRepository;
import com.comanda.comanda.Command.Repository.CommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdpterCommand implements IAdpterCommand {

    private final CommandRepository _repoComands;

    @Override
    public void save(CommandBaseDto dto) {
        _repoComands.save(CommandModelRepository.fromDto(dto));
    }

    @Override
    public CommandGetDto getById(UUID id) {
        return _repoComands.findById(id).get().fromModel();
    }

    @Override
    public boolean existsById(UUID id) {
        return _repoComands.existsById(id);
    }
}
