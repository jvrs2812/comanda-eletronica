package com.comanda.comanda.Enterprise.Adpter;

import com.comanda.comanda.Enterprise.domain.EnterpriseDto;
import com.comanda.comanda.User.domain.UserBaseDto;

import java.util.List;
import java.util.UUID;

public interface IAdpterEnterprise {
    public void save(EnterpriseDto dto, UserBaseDto user);

    public boolean existCnpj(String cnpj);

    public List<EnterpriseDto> findAllEnterpriseByUserId(String userId);

    public EnterpriseDto findById(UUID id);
}
