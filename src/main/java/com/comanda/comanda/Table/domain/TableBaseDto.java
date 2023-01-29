package com.comanda.comanda.Table.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TableBaseDto {

    public TableBaseDto(String identification, int amount_people) {
        this.identification = identification;
        this.amount_people = amount_people;
    }

    @NotNull(message = "identification is null")
    @NotEmpty(message = "identification is empty")
    private String identification;
    private int amount_people;

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
}
