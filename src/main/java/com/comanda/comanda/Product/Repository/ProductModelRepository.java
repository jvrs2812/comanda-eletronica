package com.comanda.comanda.Product.Repository;

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
    private UUID categoryId;
    public void setImageUrl(List<String> imageUrl) {
        this.imageUrls = imageUrl;
    }

    public List<String> getImageUrl() {
        return imageUrls;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
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
        return new ProductGetDto(id, title, description, price, imageUrls, obs, categoryId.toString());
    }

    static public ProductModelRepository convertToModel(ProductBaseDto dto){
        ProductModelRepository prod = new ProductModelRepository();
        prod.description = dto.getDescription();
        prod.price = dto.getPrice();
        prod.title = dto.getTitle();
        prod.categoryId = UUID.fromString(dto.getCategoryId());
        prod.obs = dto.getObs();
        prod.imageUrls = dto.getImageUrls();

        return prod;
    }
}
