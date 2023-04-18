package com.comanda.comanda.Product.Repository;

import com.comanda.comanda.Category.repository.CategoryModelRepository;
import com.comanda.comanda.Enterprise.Repository.EnterpriseModelRepository;
import com.comanda.comanda.Product.domain.ProductBaseDto;
import com.comanda.comanda.Product.domain.ProductGetDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product")
public class ProductModelRepository {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Double price;
    @NotNull
    private List<String> imageUrls;
    @NotNull
    private String obs;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryModelRepository categoryId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private EnterpriseModelRepository enterpriseId;

    public EnterpriseModelRepository getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(EnterpriseModelRepository enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public CategoryModelRepository getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoryModelRepository categoryId) {
        this.categoryId = categoryId;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public UUID  getId() {
        return id;
    }
    public void setId(UUID  id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public ProductGetDto convertToDomain(){
        return new ProductGetDto(id, title, description, price, imageUrls, obs, categoryId.getId().toString());
    }

    static public ProductModelRepository convertToModel(ProductBaseDto dto, EnterpriseModelRepository enterpriseId){
        ProductModelRepository prod = new ProductModelRepository();
        prod.description = dto.getDescription();
        prod.price = dto.getPrice();
        prod.title = dto.getTitle();

        CategoryModelRepository category = new CategoryModelRepository();
        category.setId(UUID.fromString(dto.getCategoryId()));

        prod.categoryId = category;
        prod.enterpriseId = enterpriseId;

        prod.obs = dto.getObs();
        prod.imageUrls = dto.getImageUrls();

        return prod;
    }
}
