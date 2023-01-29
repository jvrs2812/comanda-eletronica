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

    @GetMapping("v1/api/category")
    public ResponseEntity<ResponseSchema<ResponsePageable<CategoryGetDto>>> getAllCategory(@RequestParam("page") int page){
        return ResponseEntity.ok(new ResponseSchema<ResponsePageable<CategoryGetDto>>(_cat.getAll(page)));
    }

    @GetMapping("v1/api/category/{id}")
    public ResponseEntity<ResponseSchema<CategoryGetDto>> getCategoryId(@PathVariable("id") String id){
        try {
            CategoryGetDto cat = _cat.getIdCategory(id);
            return new ResponseEntity<ResponseSchema<CategoryGetDto>>(new ResponseSchema<CategoryGetDto>(cat), HttpStatus.OK);
        } catch (ComandaException e) {
            return new ResponseEntity<ResponseSchema<CategoryGetDto>>(new ResponseSchema<CategoryGetDto>(e.listError()), HttpStatus.valueOf(e.getStatusCode()));
        }
    }

    @PostMapping("v1/api/category")
    public ResponseEntity post(@RequestBody @Valid CategoryBaseDto dto){
        _cat.save(dto);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @DeleteMapping("v1/api/category/{categoryId}")
    public ResponseEntity del(@PathVariable("categoryId") String id){
        try {
            _cat.delete(id);
            return new ResponseEntity(null, HttpStatus.OK);
        } catch (ComandaException e) {
            return new ResponseEntity<ResponseSchema>(new ResponseSchema(e.listError()), HttpStatus.valueOf(e.getStatusCode()));
        }
    }
}
