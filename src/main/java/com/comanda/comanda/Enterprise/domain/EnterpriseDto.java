package com.comanda.comanda.Enterprise.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnterpriseDto {

    @NotBlank(message = "name is required")
    private String name;


    @NotBlank(message = "cnpj is required")
    private String cnpj;

    @NotBlank(message = "email is required")
    @Email(message = "email is invalid")
    private String email;

    @NotBlank(message = "telephone is required")
    private String telephone;

    @NotBlank(message = "address is required")
    private String address;

    @NotBlank(message = "city is required")
    private String city;

    @NotBlank(message = "state is required")
    private String state;

    @NotBlank(message = "country is required")
    private String country;

    @NotBlank(message = "cep is required")
    private String cep;
}
