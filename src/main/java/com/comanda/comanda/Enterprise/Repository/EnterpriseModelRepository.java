package com.comanda.comanda.Enterprise.Repository;

import com.comanda.comanda.Enterprise.domain.EnterpriseDto;
import com.comanda.comanda.User.Repository.UserModelRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "enterprise")
public class EnterpriseModelRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotNull
    private String name;

    private String cnpj;

    @NotNull
    private String email;

    @NotNull
    private String telephone;

    @NotNull
    private String address;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String country;

    @NotNull
    private String cep;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @ManyToMany
    @JoinTable(name = "enterprise_users",
            joinColumns = @JoinColumn(name = "enterprise_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private Set<UserModelRepository> users;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }


    public Set<UserModelRepository> getUsers() {
        return users;
    }

    public void setUsers(Set<UserModelRepository> users) {
        this.users = users;
    }

    public EnterpriseDto convertToDomain(){
        return EnterpriseDto.builder()
                .email(this.email)
                .cep(this.cep)
                .name(this.name)
                .state(this.state)
                .cnpj(this.cnpj)
                .address(this.address)
                .country(this.country)
                .city(this.city)
                .telephone(this.telephone)
                .id(this.id.toString())
                .build();
    }

    static public EnterpriseModelRepository convertToModel(EnterpriseDto dto, UserModelRepository usersModel){
        Set<UserModelRepository> users = new HashSet<UserModelRepository>();
        users.add(usersModel);

        EnterpriseModelRepository enterpriseModelRepository = new EnterpriseModelRepository();
        enterpriseModelRepository.cep = dto.getCep();
        enterpriseModelRepository.address = dto.getAddress();
        enterpriseModelRepository.city = dto.getCity();
        enterpriseModelRepository.cnpj = dto.getCnpj();
        enterpriseModelRepository.email = dto.getEmail();
        enterpriseModelRepository.country = dto.getCountry();
        enterpriseModelRepository.state = dto.getState();
        enterpriseModelRepository.telephone = dto.getTelephone();
        enterpriseModelRepository.name = dto.getName();
        enterpriseModelRepository.id = UUID.randomUUID();
        enterpriseModelRepository.users = users;
        enterpriseModelRepository.updateAt  = new Date();
        enterpriseModelRepository.createdAt = new Date();

        return enterpriseModelRepository;
    }
}
