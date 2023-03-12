package com.comanda.comanda.User.domain;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserBaseDto {
    public UserBaseDto(String name, String email, String cpf, Date date_birth, String password, int role) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.date_birth = date_birth;
        this.password = password;
        this.role = role;
    }

    @NotNull(message = "role is empty")
    private int role;

    @NotBlank(message = "name is empty")
    @Size(min = 5, message = "name must be at least 5 characters long")
    private String name;

    @NotBlank(message = "email is empty")
    @Email(message = "email is invalid")
    private String email;

    @NotBlank(message = "cpf is empty")
    @Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})", message = "cpf is invalid")
    private String cpf;

    @NotNull(message = "date_birth is empty")
    private Date date_birth;

    @NotNull(message = "password is empty")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&^_-]{8,}$", message = "password must have at least one uppercase letter, one lowercase letter and one special character")
    private String password;

    public String getName() {
        return name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
