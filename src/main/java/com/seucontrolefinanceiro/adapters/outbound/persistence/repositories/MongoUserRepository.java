package com.seucontrolefinanceiro.adapters.outbound.persistence.repositories;

import com.seucontrolefinanceiro.adapters.outbound.persistence.springdata.SpringDataMongoUserRepository;
import com.seucontrolefinanceiro.application.entities.User;
import com.seucontrolefinanceiro.application.ports.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Primary
public class MongoUserRepository implements UserRepository {

    @Autowired
    private SpringDataMongoUserRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
