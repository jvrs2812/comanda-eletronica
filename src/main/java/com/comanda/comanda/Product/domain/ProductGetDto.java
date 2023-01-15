package com.comanda.comanda.Product.domain;

import java.util.List;
import java.util.UUID;

public class ProductGetDto extends ProductBaseDto {

    public ProductGetDto(UUID id, String title, String description, Double price, List<String> imageUrls, String obs, String categoryId) {
        super(title, description, price, obs, categoryId, imageUrls);
        this.id = id;
    }

    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}