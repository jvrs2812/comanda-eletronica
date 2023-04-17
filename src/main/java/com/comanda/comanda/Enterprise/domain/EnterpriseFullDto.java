package com.comanda.comanda.Enterprise.domain;

import com.comanda.comanda.User.Repository.UserModelRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EnterpriseFullDto extends EnterpriseDto{

    private UserModelRepository id_user;

    EnterpriseFullDto(@NotBlank(message = "name is required") String name, @NotBlank(message = "cnpj is required") String cnpj, @NotBlank(message = "email is required") @Email(message = "email is invalid") String email, @NotBlank(message = "telephone is required") String telephone, @NotBlank(message = "address is required") String address, @NotBlank(message = "city is required") String city, @NotBlank(message = "state is required") String state, @NotBlank(message = "country is required") String country, @NotBlank(message = "cep is required") String cep) {
        super(name, cnpj, email, telephone, address, city, state, country, cep);
    }

    public UserModelRepository getId_user() {
        return id_user;
    }

    public void setId_user(UserModelRepository id_user) {
        this.id_user = id_user;
    }
}
