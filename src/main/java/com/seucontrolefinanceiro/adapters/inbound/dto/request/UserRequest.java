package com.seucontrolefinanceiro.adapters.inbound.dto.request;

import com.seucontrolefinanceiro.application.entities.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
public class UserRequest {

    private final String id;

    @NotNull
    private final String fullName;

    @NotNull
    private final String email;

    @NotNull
    private final String password;

    @NotNull
    @CPF
    private final String cpf;

    public User converter() {
        return User.builder()
                .id(this.id)
                .fullName(this.fullName)
                .email(this.email)
                .password(this.password)
                .cpf(this.cpf)
                .build();
    }
}