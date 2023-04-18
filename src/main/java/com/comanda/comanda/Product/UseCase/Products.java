package com.comanda.comanda.Product.UseCase;

import com.comanda.comanda.Category.Adpter.IAdpterCategory;
import com.comanda.comanda.Category.Exception.CategoryException;
import com.comanda.comanda.Enterprise.Repository.EnterpriseRepository;
import com.comanda.comanda.Product.Adpter.IAdpterProduct;
import com.comanda.comanda.Product.Adpter.IStorageAdapter;
import com.comanda.comanda.Product.domain.ProductBaseDto;
import com.comanda.comanda.Product.domain.ProductGetDto;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.Validations.Validations;
import com.comanda.comanda.utils.commom.ResponsePageable;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.comanda.comanda.Product.Exception.ProductException.*;
import static com.comanda.comanda.utils.enums.EnumException.*;

@Component
public class Products {
    @Autowired
    private IAdpterProduct _product;

    @Autowired
    IAdpterCategory _category;

    @Autowired
    private IStorageAdapter _storage;

    @Autowired
    private EnterpriseRepository _enterpriserepo;

    private static final String[] extensionValid = new String[] {"JPG","JPEG","PNG"};

    public void put(String id, ProductBaseDto base, String enterpriseId) throws ComandaException {
        if(!Validations.isValidId(base.getCategoryId())) throw new ComandaException(CategoryException.CATEGORY_ID_EXCEPTION);

        if(!Validations.isValidId(id)) throw new ComandaException(PRODUCT_ID_ERROR);

        if(!_product.existProduct(UUID.fromString(id))){
            throw new ComandaException(PRODUCT_NOT_FOUND);
        }
        _product.put(id, base, this._enterpriserepo.getById(UUID.fromString(enterpriseId)));
    }

    public ResponsePageable<ProductGetDto> getAll(int page, String enterpriseId) throws ComandaException {
        if(page <= 0) {
            throw new ComandaException(INVALID_PAGE);
        }
        return _product.getAll(page, UUID.fromString(enterpriseId));
    }

    public void save(ProductBaseDto product, MultipartFile[] images, String enterpriseId) throws ComandaException {
        if(!Validations.isValidId(product.getCategoryId())) throw new ComandaException(CategoryException.CATEGORY_ID_EXCEPTION);
        if(!_category.exists(product.getCategoryId())) throw new ComandaException(CategoryException.CATEGORY_NOT_FOUND);

        List<String> urls = saveImageProduct(images);

        product.setImageUrls(urls);

        _product.save(product, this._enterpriserepo.getById(UUID.fromString(enterpriseId)));
    }

    public ProductGetDto getById(String id) throws ComandaException {
        if(!Validations.isValidId(id)) throw new ComandaException(PRODUCT_ID_ERROR);

        ProductGetDto _prod = _product.getById(UUID.fromString(id));

        return _prod;
    }

    public List<String> saveImageProduct(MultipartFile[] images) throws ComandaException {
        AtomicBoolean isNotValidImage = new AtomicBoolean(false);
        AtomicBoolean isNotValidSizeImage = new AtomicBoolean(false);

        Arrays.asList(images).stream().forEach(file ->{
            String extension = FilenameUtils.getExtension(file.getOriginalFilename()).toUpperCase();

            long fileSizeInBytes = file.getSize();

            long fileSizeInMb = fileSizeInBytes / 1000000;

            if(fileSizeInMb >= 2) isNotValidSizeImage.set(true);

            if(!Arrays.stream(extensionValid).anyMatch(extension::equals)) isNotValidImage.set(true);
        });

        if (isNotValidImage.get()) throw new ComandaException(INVALID_EXTENSION);
        if (isNotValidSizeImage.get()) throw new ComandaException(INVALID_FILE_SIZE);

        return _storage.saveImage(images);
    }

    public void deleteById(String id) throws ComandaException {
        ProductGetDto dto = getById(id);

        if(!_product.existProduct(UUID.fromString(id))) throw new ComandaException(PRODUCT_NOT_FOUND);

        _product.deletbyId(id);

        for (int i = 0; i < dto.getImageUrls().size(); i++) {
            _storage.deleteImage(dto.getImageUrls().get(i));
        }
    }
}
