package com.comanda.comanda.Command.Domain;

import java.util.Date;
import java.util.UUID;

public class CommandGetDto extends CommandBaseDto {

    public CommandGetDto(UUID id, Date createdAt, String table_id, int type, Date paidAt, int status, Float discount, Float totalPayble) {
        super(table_id, type);
        this.id = id;
        this.createdAt = createdAt;
        this.paidAt = paidAt;
        this.status = status;
        this.discount = discount;
        this.totalPayble = totalPayble;
    }

    private UUID id;

    private Date createdAt;

    private Date paidAt;

    private int status;

    private Float discount;

    private Float totalPayble;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Date paidAt) {
        this.paidAt = paidAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getTotalPayaple() {
        return totalPayble;
    }

    public void setTotalPayaple(Float totalPayble) {
        this.totalPayble = totalPayble;
    }
}
