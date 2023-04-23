package com.comanda.comanda.Command.Adpter;

import com.comanda.comanda.Command.Domain.CommandBaseDto;
import com.comanda.comanda.Command.Domain.CommandGetDto;
import com.comanda.comanda.Command.Repository.CommandModelRepository;
import com.comanda.comanda.Command.Repository.CommandRepository;
import com.comanda.comanda.Enterprise.Adpter.IAdpterEnterprise;
import com.comanda.comanda.Enterprise.Repository.EnterpriseModelRepository;
import com.comanda.comanda.User.Repository.UserModelRepository;
import com.comanda.comanda.utils.Configuration.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdpterCommand implements IAdpterCommand {

   private final CommandRepository _repoCommands;

    private final IAdpterEnterprise _enterprise;

    private final JwtService _jwt;

    @Override
    public void save(CommandBaseDto dto, String enterprise_id) {
        EnterpriseModelRepository enterpriseModelRepository = EnterpriseModelRepository.convertToModel(
                this._enterprise.findById(UUID.fromString(enterprise_id)),
                this._jwt.getUserContextSecurity());

        this._repoCommands.save(CommandModelRepository.fromDto(dto, enterpriseModelRepository));
    }


    @Override
    public CommandGetDto getById(UUID id, String enterprise_id) {
        return _repoCommands.findById(id, UUID.fromString(enterprise_id)).fromModel();
    }

    @Override
    public boolean existsById(UUID id,  String enterprise_id) {
        return _repoCommands.existByUUID(id, UUID.fromString(enterprise_id));
    }
}
