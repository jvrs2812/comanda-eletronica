package com.comanda.comanda.Category.Domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryBaseDto {

    @NotNull
    private String title;

    @NotNull
    private String description;


    public CategoryBaseDto(String title, String description) {
        this.title = title;
        this.description = description;
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
}
