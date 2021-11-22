package com.seucontrolefinanceiro.adapters.outbound.persistence.springdata;

import com.seucontrolefinanceiro.application.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataMongoUserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
}
