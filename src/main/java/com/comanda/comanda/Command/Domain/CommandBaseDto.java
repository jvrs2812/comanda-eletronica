package com.comanda.comanda.Command.Domain;

import jakarta.validation.constraints.NotNull;

public class CommandBaseDto {

    public CommandBaseDto(String table_id, int type) {
        this.table_id = table_id;
        this.type = type;
    }

    private String table_id;

    @NotNull(message = "type is null")
    private int type;

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
