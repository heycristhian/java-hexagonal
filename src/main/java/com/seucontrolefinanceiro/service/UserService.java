package com.seucontrolefinanceiro.service;

import com.seucontrolefinanceiro.domain.model.User;
import com.seucontrolefinanceiro.exception.ObjectNotFoundException;
import com.seucontrolefinanceiro.kafka.KafkaProducer;
import com.seucontrolefinanceiro.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private KafkaProducer producer;

    public List<User> findAll() {
        log.info("Finding all users in the database");
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User save(User user) {
        log.info("Saving new user");
        user = repository.save(user);
        producer.send(user.getUsername());
        return user;
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public User update(User user) {
        findById(user.getId());
        return repository.save(user);
    }

}
