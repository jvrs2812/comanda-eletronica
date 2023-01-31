package com.comanda.comanda.Commands.Domain;

import com.comanda.comanda.Commands.Enum.CommandsStatusEnum;
import com.comanda.comanda.Commands.Enum.CommandsTypeEnum;
import com.comanda.comanda.Table.Repository.TableModelRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class CommandsBaseDto {

    public CommandsBaseDto(String table_id, int type) {
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
