package com.comanda.comanda.Category.Adpter;

import com.comanda.comanda.Category.Domain.CategoryBaseDto;
import com.comanda.comanda.Category.Domain.CategoryGetDto;
import com.comanda.comanda.Enterprise.Repository.EnterpriseModelRepository;
import com.comanda.comanda.Enterprise.domain.EnterpriseDto;
import com.comanda.comanda.utils.commom.ResponsePageable;

import java.util.List;
import java.util.UUID;

public interface IAdpterCategory {
    ResponsePageable<CategoryGetDto> getAll(int page, UUID enterprise_id);

    void save(CategoryBaseDto dto, EnterpriseDto enterprise);

    boolean exists(String id, String enterpriseId);

    void del(UUID id);

    CategoryGetDto getById(UUID id, UUID enterpriseId);
}
