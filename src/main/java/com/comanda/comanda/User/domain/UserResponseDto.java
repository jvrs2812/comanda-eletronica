package com.comanda.comanda.User.domain;


import java.util.Date;


public class UserResponseDto extends UserBaseDto {
    public UserResponseDto(String id, String name, String email, String cpf, Date date_birth, String password, int role) {
        super(name, email, cpf, date_birth, password, role);
        this.id = id;
    }

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
