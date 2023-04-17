package com.comanda.comanda.User.Repository;

import com.comanda.comanda.Enterprise.Repository.EnterpriseModelRepository;
import com.comanda.comanda.User.domain.UserBaseDto;
import com.comanda.comanda.User.domain.UserResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "user_table")
public class UserModelRepository implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotNull
    private String name;


    @NotNull
    private String email;

    @NotNull
    private String cpf;

    @NotNull
    private Date date_birth;

    @NotNull
    private String password;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    static public UserModelRepository convertToModel(UserBaseDto dto){
        UserModelRepository user = new UserModelRepository();
        user.id = UUID.randomUUID();
        user.cpf = dto.getCpf();
        user.date_birth = dto.getDate_birth();
        user.email = dto.getEmail();
        user.name = dto.getName();
        user.password = dto.getPassword();
        Role[] roles = Role.values();
        user.role = roles[dto.getRole()];
        return user;
    }

    public UserResponseDto toDto(){
        return new UserResponseDto(this.id.toString(), this.name, this.email, this.cpf, this.date_birth, this.password, this.role.ordinal());
    }
}
