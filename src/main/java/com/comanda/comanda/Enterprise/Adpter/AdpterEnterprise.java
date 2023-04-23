package com.comanda.comanda.Enterprise.Adpter;

import com.comanda.comanda.Enterprise.Repository.EnterpriseModelRepository;
import com.comanda.comanda.Enterprise.Repository.EnterpriseRepository;
import com.comanda.comanda.Enterprise.domain.EnterpriseDto;
import com.comanda.comanda.User.Repository.UserModelRepository;
import com.comanda.comanda.User.domain.UserBaseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdpterEnterprise implements IAdpterEnterprise {

    private EnterpriseRepository repo;

    @Override
    public void save(EnterpriseDto dto, UserBaseDto user) {
        this.repo.save(EnterpriseModelRepository.convertToModel(dto, UserModelRepository.convertToModel(user)));
    }

    @Override
    public boolean existCnpj(String cnpj) {
        return this.repo.existCnpj(cnpj);
    }

    @Override
    public List<EnterpriseDto> findAllEnterpriseByUserId(String userId) {
        List<EnterpriseModelRepository> models = this.repo.findAllEnterpriseByUserId(UUID.fromString(userId));
        List<EnterpriseDto> dtos = new ArrayList<EnterpriseDto>();

        for (EnterpriseModelRepository model:
                models) {
            dtos.add(model.convertToDomain());
        }

        return dtos;
    }

    @Override
    public EnterpriseDto findById(UUID id) {
        return this.repo.getById(id).convertToDomain();
    }
}
