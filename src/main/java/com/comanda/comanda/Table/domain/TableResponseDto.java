package com.comanda.comanda.Table.domain;

import com.comanda.comanda.Table.Enum.StatusTable;

import java.util.UUID;

public class TableResponseDto extends TableBaseDto{

    public TableResponseDto(UUID id, String identification, int amount_people, StatusTable status) {
        super(identification, amount_people);
        this.id = id;
        this.status = status;
    }

    private UUID id;
    private StatusTable status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StatusTable getStatus() {
        return status;
    }

    public void setStatus(StatusTable status) {
        this.status = status;
    }
}
