package com.comanda.comanda.Enterprise.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnterpriseDto {
    private String id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "cnpj is required")
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$", message = "cnpj is invalid")
    private String cnpj;

    @NotBlank(message = "email is required")
    @Email(message = "email is invalid")
    private String email;

    @NotBlank(message = "telephone is required")
    @Pattern(regexp = "\\(\\d{2}\\)\\s\\d{4,5}\\-\\d{4}", message = "telephone is invalid")
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
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "zip code is in invalid format(xxxxx-xxx)")
    private String cep;
}
