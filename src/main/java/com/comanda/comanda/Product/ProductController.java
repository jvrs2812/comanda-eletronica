package com.comanda.comanda.Product;

import com.comanda.comanda.Product.UseCase.Products;
import com.comanda.comanda.Product.domain.ProductBaseDto;
import com.comanda.comanda.Product.domain.ProductGetDto;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.HandleValidationException;
import com.comanda.comanda.utils.ResponseSchema;
import com.comanda.comanda.utils.commom.ResponsePageable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProductController extends HandleValidationException {
    @Autowired
    private Products _products;

    @GetMapping("v1/api/{enterpriseId}/products")
    public ResponseEntity<ResponseSchema<ResponsePageable<ProductGetDto>>> getAll(@PathVariable("enterpriseId") String enterpriseId, @RequestParam int page) throws ComandaException {
       ResponsePageable<ProductGetDto> _prod = _products.getAll(page, enterpriseId);
       return ResponseEntity.ok(new ResponseSchema<ResponsePageable<ProductGetDto>>(_prod));
    }

    @PutMapping("v1/api/{enterpriseId}/products/{id}")
    public ResponseEntity put(@PathVariable("enterpriseId") String enterpriseId, @PathVariable("id") String id, @Valid @RequestBody ProductBaseDto dto) throws ComandaException {
        _products.put(id, dto, enterpriseId);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping("v1/api/products/{id}")
    public ResponseEntity<ResponseSchema<ProductGetDto>> put(@PathVariable("id") String id) throws ComandaException {
        ProductGetDto _prod = _products.getById(id);
        return new ResponseEntity(new ResponseSchema<ProductGetDto>(_prod), HttpStatus.OK);
    }


    @PostMapping(value = "v1/api/{enterpriseId}/products",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity save(@PathVariable("enterpriseId") String enterpriseId, @RequestPart("product") @Valid ProductBaseDto dto, @RequestParam MultipartFile[] images) throws ComandaException {
        _products.save(dto, images, enterpriseId);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @DeleteMapping("v1/api/products/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) throws ComandaException {
        _products.deleteById(id);
        return new ResponseEntity(null, HttpStatus.OK);
    }

}
