package com.comanda.comanda.Category.repository;

import com.comanda.comanda.Category.Domain.CategoryBaseDto;
import com.comanda.comanda.Category.Domain.CategoryGetDto;
import com.comanda.comanda.Enterprise.Repository.EnterpriseModelRepository;
import com.comanda.comanda.Enterprise.Repository.EnterpriseRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "category")
public class CategoryModelRepository {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private EnterpriseModelRepository enterpriseId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public CategoryGetDto convertToDomain(){
        return new CategoryGetDto(id.toString(), title, description, createAt, updateAt);
    }

    static public CategoryModelRepository convertToModel(CategoryBaseDto dto, boolean create, EnterpriseModelRepository enterpriseId){
        CategoryModelRepository category = new CategoryModelRepository();
        category.id = UUID.randomUUID();
        category.description = dto.getDescription();
        category.title = dto.getTitle();
        category.enterpriseId = enterpriseId;

        if(create) category.createAt = new Date();

        category.updateAt = new Date();
        return category;
    }
}
