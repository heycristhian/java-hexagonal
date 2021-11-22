package com.seucontrolefinanceiro.application.services;

import com.seucontrolefinanceiro.adapters.outbound.producer.KafkaProducer;
import com.seucontrolefinanceiro.application.entities.User;
import com.seucontrolefinanceiro.application.ports.repositories.UserRepository;
import com.seucontrolefinanceiro.application.ports.services.UserService;
import com.seucontrolefinanceiro.application.exception.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final KafkaProducer producer;

    public UserServiceImpl(UserRepository repository, KafkaProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    @Override
    public List<User> findAll() {
        log.info("Finding all users in the database");
        return repository.findAll();
    }

    @Override
    public User findById(String id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        log.info("Saving new user");
        user = repository.save(user);
        producer.send(user.getUsername());
        return user;
    }

    @Override
    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public User update(User user) {
        findById(user.getId());
        return repository.save(user);
    }

}
