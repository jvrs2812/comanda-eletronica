package com.comanda.comanda.Table.Repository;

import com.comanda.comanda.Category.repository.CategoryModelRepository;
import com.comanda.comanda.Product.Repository.ProductModelRepository;
import com.comanda.comanda.Product.domain.ProductBaseDto;
import com.comanda.comanda.Table.Enum.StatusTable;
import com.comanda.comanda.Table.domain.TableBaseDto;
import com.comanda.comanda.Table.domain.TableResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Table(name = "table_restaurant")
public class TableModelRepository {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;
    @NotNull
    private String identification;
    @NotNull
    private int amount_people;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private StatusTable status;
    @NotNull
    private boolean enable;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public int getAmount_people() {
        return amount_people;
    }

    public void setAmount_people(int amount_people) {
        this.amount_people = amount_people;
    }

    public StatusTable getStatus() {
        return status;
    }

    public void setStatus(StatusTable status) {
        this.status = status;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    static public TableModelRepository convertToModel(TableBaseDto dto){
        TableModelRepository table = new TableModelRepository();
        table.id = UUID.randomUUID();
        table.identification = dto.getIdentification();
        table.amount_people = dto.getAmount_people();
        table.enable = true;
        table.status = StatusTable.AVAILABLE;

        return table;
    }

    public TableResponseDto convertToDomain(){
        return new TableResponseDto(this.id, this.identification, this.amount_people, this.status.ordinal());
    }
}
