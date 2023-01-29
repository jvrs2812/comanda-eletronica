package com.comanda.comanda.Category.Domain;

import java.util.Date;
import java.util.UUID;

public class CategoryGetDto extends CategoryBaseDto{

    public CategoryGetDto(String id, String title, String description, Date createAt, Date updateAt) {
        super(title, description);
        this.id = UUID.fromString(id);
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
    private UUID id;

    private Date createAt;

    private Date updateAt;

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

    public void setId(UUID id) {
        this.id = id;
    }

    public String getId() {
        return id.toString();
    }

}
