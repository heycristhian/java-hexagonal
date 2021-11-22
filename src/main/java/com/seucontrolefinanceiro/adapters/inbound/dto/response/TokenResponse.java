package com.seucontrolefinanceiro.adapters.inbound.dto.response;

import lombok.Getter;

@Getter
public class TokenResponse {

    private String token;
    private String type;
    private String userId;

    public TokenResponse(String token, String bearer, String userId) {
        this.token = token;
        this.type = bearer;
        this.userId = userId;
    }
}
