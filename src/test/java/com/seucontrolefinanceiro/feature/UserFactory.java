package com.seucontrolefinanceiro.feature;

import com.seucontrolefinanceiro.domain.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserFactory {


    public static List<User> getUsers() {
        var user1 = User.builder()
                .fullName("Cristhian Dias 1")
                .email("cristhian@gmail.com")
                .password(new BCryptPasswordEncoder().encode("teste123"))
                .cpf("43377264035")
                .build();

        var user2 = User.builder()
                .fullName("Cristhian Dias 2")
                .email("cristhiandias@gmail.com")
                .password(new BCryptPasswordEncoder().encode("teste432"))
                .cpf("99015203040")
                .build();

        return List.of(user1, user2);
    }

    public static User getDefaultUser() {
        return User.builder()
                .fullName("Cristhian Dias 1")
                .email("cristhian@gmail.com")
                .password(new BCryptPasswordEncoder().encode("teste123"))
                .cpf("43377264035")
                .build();
    }

    public static User getUserWithId() {
        return User.builder()
                .id("617344e1c7d4da6d3c8082cf")
                .fullName("Cristhian Dias 1")
                .email("cristhian@gmail.com")
                .password(new BCryptPasswordEncoder().encode("teste123"))
                .cpf("43377264035")
                .build();
    }
}
