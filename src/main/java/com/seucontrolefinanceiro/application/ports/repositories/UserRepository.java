package com.seucontrolefinanceiro.application.ports.repositories;

import com.seucontrolefinanceiro.application.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    User save(User user);

    void deleteById(String id);
}
