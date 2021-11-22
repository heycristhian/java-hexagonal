package com.seucontrolefinanceiro.adapters.inbound.controllers;

import com.seucontrolefinanceiro.adapters.configuration.security.TokenService;
import com.seucontrolefinanceiro.application.entities.User;
import com.seucontrolefinanceiro.adapters.inbound.dto.response.TokenResponse;
import com.seucontrolefinanceiro.adapters.inbound.dto.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenResponse> authenticate(@RequestBody @Valid LoginRequest form) {
        UsernamePasswordAuthenticationToken dataLogin = form.converter();
        try {
            Authentication authenticate = authenticationManager.authenticate(dataLogin);
            String token = tokenService.generateToken(authenticate);
            User loggedUser = (User) authenticate.getPrincipal();
            return ResponseEntity.ok(new TokenResponse(token, "Bearer", loggedUser.getId()));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
