package com.comanda.comanda.Category.UseCase;

import com.comanda.comanda.Category.Adpter.IAdpterCategory;
import com.comanda.comanda.Category.Domain.CategoryBaseDto;
import com.comanda.comanda.Category.Domain.CategoryGetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Categories {

    @Autowired
    private IAdpterCategory _adpter;

    public List<CategoryGetDto> getAll(){
        return _adpter.getAll();
    }

    public void save(CategoryBaseDto dto){
        _adpter.save(dto);
    }
}
