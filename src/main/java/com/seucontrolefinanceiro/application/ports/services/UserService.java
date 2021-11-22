package com.seucontrolefinanceiro.application.ports.services;

import com.seucontrolefinanceiro.application.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User findById(String id);

    Optional<User> findByEmail(String email);

    User save(User user);

    void delete(String id);

    User update(User user);
}
