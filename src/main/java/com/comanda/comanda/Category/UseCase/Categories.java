package com.comanda.comanda.Category.UseCase;

import com.comanda.comanda.Category.Adpter.IAdpterCategory;
import com.comanda.comanda.Category.Domain.CategoryBaseDto;
import com.comanda.comanda.Category.Domain.CategoryGetDto;
import com.comanda.comanda.Category.Exception.CategoryException;
import com.comanda.comanda.Enterprise.Repository.EnterpriseRepository;
import com.comanda.comanda.Product.Adpter.IAdpterProduct;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.Validations.Validations;
import com.comanda.comanda.utils.commom.ResponsePageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class Categories {

    @Autowired
    private IAdpterCategory _adpter;

    @Autowired
    private IAdpterProduct _prod;

    @Autowired
    private EnterpriseRepository _repoEnterprise;

    public ResponsePageable<CategoryGetDto> getAll(int page, String enterpriseId){
        return _adpter.getAll(page, UUID.fromString(enterpriseId));
    }

    public void save(CategoryBaseDto dto, String enterpriseId){
        _adpter.save(dto, this._repoEnterprise.getById(UUID.fromString(enterpriseId)).convertToDomain());
    }

    public void delete(String id, String enterpriseId) throws ComandaException {
        if(!Validations.isValidId(id)) throw new ComandaException(CategoryException.CATEGORY_ID_EXCEPTION);

        if(!_adpter.exists(id, enterpriseId)) throw new ComandaException(CategoryException.CATEGORY_NOT_FOUND);

        if(_prod.existwithCategoryId(UUID.fromString(id))) throw new ComandaException(CategoryException.CATEGORY_PRODUCT_EXIST);

        _adpter.del(UUID.fromString(id));
    }

    public CategoryGetDto getIdCategory(String id, String enterpriseId) throws ComandaException {
        if(!Validations.isValidId(id)) throw new ComandaException(CategoryException.CATEGORY_ID_EXCEPTION);

        if(!_adpter.exists(id, enterpriseId)) throw new ComandaException(CategoryException.CATEGORY_NOT_FOUND);

        return _adpter.getById(UUID.fromString(id), UUID.fromString(enterpriseId));
    }

}
