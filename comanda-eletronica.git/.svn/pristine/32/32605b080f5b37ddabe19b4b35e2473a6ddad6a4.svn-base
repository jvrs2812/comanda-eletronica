package com.comanda.comanda.Category;

import com.comanda.comanda.Category.UseCase.Categories;
import com.comanda.comanda.Category.Domain.CategoryBaseDto;
import com.comanda.comanda.Category.Domain.CategoryGetDto;
import com.comanda.comanda.utils.HandleValidationException;
import com.comanda.comanda.utils.ResponseSchema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController extends HandleValidationException {

    @Autowired
    private Categories _cat;

    @GetMapping("v1/api/categories")
    public ResponseEntity<ResponseSchema<List<CategoryGetDto>>> postCategory(){
        return ResponseEntity.ok(new ResponseSchema<List<CategoryGetDto>>(_cat.getAll()));
    }

    @PostMapping("v1/api/category")
    public ResponseEntity post(@RequestBody @Valid CategoryBaseDto dto){
        _cat.save(dto);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }
}
