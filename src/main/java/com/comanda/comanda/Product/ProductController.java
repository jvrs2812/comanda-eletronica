package com.comanda.comanda.Product;

import com.comanda.comanda.Product.Exception.*;
import com.comanda.comanda.Product.UseCase.Products;
import com.comanda.comanda.Product.domain.ProductAllResponse;
import com.comanda.comanda.Product.domain.ProductBaseDto;
import com.comanda.comanda.Product.domain.ProductGetDto;
import com.comanda.comanda.utils.HandleValidationException;
import com.comanda.comanda.utils.ResponseSchema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController extends HandleValidationException {
    @Autowired
    private Products _products;

    @GetMapping("v1/api/products")
    public ResponseEntity<ResponseSchema<ProductAllResponse>> getAll(@RequestParam int page){
        try {
            ProductAllResponse _prod = _products.getAll(page);
            return ResponseEntity.ok(new ResponseSchema<ProductAllResponse>(_prod));
        } catch (PageException e) {
            List<String> listError = new ArrayList<String>();
            listError.add(e.getMessage());
            return new ResponseEntity<ResponseSchema<ProductAllResponse>>(new ResponseSchema<ProductAllResponse>(listError), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("v1/api/product/{id}")
    public ResponseEntity put(@PathVariable("id") String id, @Valid @RequestBody ProductBaseDto dto){
        try {
            _products.put(id, dto);
            return new ResponseEntity(null, HttpStatus.OK);
        }catch (ProductNotExist e){
            List<String> listError = new ArrayList<String>();
            listError.add(e.getMessage());
            return new ResponseEntity<ResponseSchema<String>>(new ResponseSchema<String>(listError), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("v1/api/product/{id}")
    public ResponseEntity<ResponseSchema<ProductGetDto>> put(@PathVariable("id") String id){
        try {
            ProductGetDto _prod = _products.getById(id);
            return new ResponseEntity(new ResponseSchema<ProductGetDto>(_prod), HttpStatus.OK);
        }catch (ProductIdException e){
            List<String> listError = new ArrayList<String>();
            listError.add(e.getMessage());
            return new ResponseEntity<ResponseSchema<ProductGetDto>>(new ResponseSchema<ProductGetDto>(listError), HttpStatus.BAD_REQUEST);
        } catch (ProductNotExist e) {
            List<String> listError = new ArrayList<String>();
            listError.add(e.getMessage());
            return new ResponseEntity<ResponseSchema<ProductGetDto>>(new ResponseSchema<ProductGetDto>(listError), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(value = "v1/api/product",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity save(@RequestPart("product") @Valid ProductBaseDto dto, @RequestParam MultipartFile[] images){
        try {
            _products.save(dto, images);
            return new ResponseEntity(null, HttpStatus.CREATED);
        }catch (ImageException e){
            List<String> listError = new ArrayList<String>();
            listError.add(e.getMessage());
            return new ResponseEntity<ResponseSchema<String>>(new ResponseSchema<String>(listError), HttpStatus.BAD_REQUEST);
        } catch (ProductCategoryException e){
            List<String> listError = new ArrayList<String>();
            listError.add(e.getMessage());
            return new ResponseEntity<ResponseSchema<String>>(new ResponseSchema<String>(listError), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("v1/api/product/{id}")
    public ResponseEntity delete(@PathVariable("id") String id){
        try {
            _products.deleteById(id);
            return new ResponseEntity(null, HttpStatus.OK);
        }catch (ProductNotExist e){
            List<String> listError = new ArrayList<String>();
            listError.add(e.getMessage());
            return new ResponseEntity<ResponseSchema<String>>(new ResponseSchema<String>(listError), HttpStatus.BAD_REQUEST);
        } catch (ProductIdException e) {
            List<String> listError = new ArrayList<String>();
            listError.add(e.getMessage());
            return new ResponseEntity<ResponseSchema<String>>(new ResponseSchema<String>(listError), HttpStatus.BAD_REQUEST);
        }
    }

}
