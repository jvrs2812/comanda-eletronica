package com.comanda.comanda.Category;

import com.comanda.comanda.Category.UseCase.Categories;
import com.comanda.comanda.Category.Domain.CategoryBaseDto;
import com.comanda.comanda.Category.Domain.CategoryGetDto;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.HandleValidationException;
import com.comanda.comanda.utils.ResponseSchema;
import com.comanda.comanda.utils.commom.ResponsePageable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController extends HandleValidationException {

    @Autowired
    private Categories _cat;

    public CategoryController(Categories cat){
        this._cat = cat;
    }

    @GetMapping("v1/api/{enterpriseId}/category")
    public ResponseEntity<ResponseSchema<ResponsePageable<CategoryGetDto>>> getAllCategory(@PathVariable("enterpriseId") String enterpriseId, @RequestParam("page") int page){
        return ResponseEntity.ok(new ResponseSchema<ResponsePageable<CategoryGetDto>>(_cat.getAll(page, enterpriseId)));
    }

    @GetMapping("v1/api/{enterpriseId}/category/{id}")
    public ResponseEntity<ResponseSchema<CategoryGetDto>> getCategoryId(@PathVariable("enterpriseId") String enterpriseId, @PathVariable("id") String id) throws ComandaException {
        CategoryGetDto cat = _cat.getIdCategory(id);
        return new ResponseEntity<ResponseSchema<CategoryGetDto>>(new ResponseSchema<CategoryGetDto>(cat), HttpStatus.OK);
    }

    @PostMapping("v1/api/{enterpriseId}/category")
    public ResponseEntity post(@PathVariable("enterpriseId") String enterpriseId, @RequestBody @Valid CategoryBaseDto dto){
        _cat.save(dto, enterpriseId);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @DeleteMapping("v1/api/{enterpriseId}/category/{categoryId}")
    public ResponseEntity del(@PathVariable("categoryId") String id) throws ComandaException {
        _cat.delete(id);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
