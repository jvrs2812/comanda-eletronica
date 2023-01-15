package com.comanda.comanda.Product.UseCase;

import com.comanda.comanda.Product.Adpter.IAdpterProduct;
import com.comanda.comanda.Product.Adpter.IStorageAdapter;
import com.comanda.comanda.Product.Exception.ImageException;
import com.comanda.comanda.Product.Exception.PageException;
import com.comanda.comanda.Product.Exception.ProductIdException;
import com.comanda.comanda.Product.Exception.ProductNotExist;
import com.comanda.comanda.Product.domain.ProductAllResponse;
import com.comanda.comanda.Product.domain.ProductBaseDto;
import com.comanda.comanda.Product.domain.ProductGetDto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class Products {
    @Autowired
    private IAdpterProduct _product;

    @Autowired
    private IStorageAdapter _storage;

    private static final String[] extensionValid = new String[] {"JPG","JPEG","PNG"};

    private boolean isValidUUID(String uuid){
        try {
            UUID.fromString(uuid);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    public void put(String id, ProductBaseDto base) throws ProductNotExist {
        if(!isValidUUID(base.getCategoryId())) throw new ProductNotExist("Category id is invalid");

        if(!isValidUUID(id)) throw new ProductNotExist("Product id is invalid");

        if(!_product.existProduct(UUID.fromString(id))){
            throw new ProductNotExist("this product is not found");
        }
        _product.put(id, base);
    }

    public ProductAllResponse getAll(int page) throws PageException {
        if(page <= 0) {
            throw new PageException("invalid page");
        }
        return _product.getAll(page);
    }

    public void save(ProductBaseDto product, MultipartFile[] images) throws ProductNotExist, ImageException {
        if(!isValidUUID(product.getCategoryId())) throw new ProductNotExist("Category id is invalid");

        List<String> urls = saveImageProduct(images);

        product.setImageUrls(urls);

        _product.save(product);
    }

    public ProductGetDto getById(String id) throws ProductIdException, ProductNotExist {
        if(!isValidUUID(id)) throw new ProductIdException("Product id is invalid");

        ProductGetDto _prod = _product.getById(UUID.fromString(id));

        return _prod;
    }

    public List<String> saveImageProduct(MultipartFile[] images) throws ImageException {
        AtomicBoolean isNotValidImage = new AtomicBoolean(false);
        AtomicBoolean isNotValidSizeImage = new AtomicBoolean(false);

        Arrays.asList(images).stream().forEach(file ->{
            String extension = FilenameUtils.getExtension(file.getOriginalFilename()).toUpperCase();

            long fileSizeInBytes = file.getSize();

            long fileSizeInMb = fileSizeInBytes / 1000000;

            if(fileSizeInMb >= 2) isNotValidSizeImage.set(true);

            if(!Arrays.stream(extensionValid).anyMatch(extension::equals)) isNotValidImage.set(true);
        });

        if (isNotValidImage.get()) throw new ImageException("file extension is not valid");
        if (isNotValidSizeImage.get()) throw new ImageException("file size is more is 2mb");

        return _storage.saveImage(images);
    }
}
