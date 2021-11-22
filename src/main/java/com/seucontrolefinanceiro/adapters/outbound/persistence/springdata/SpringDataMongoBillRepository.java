package com.seucontrolefinanceiro.adapters.outbound.persistence.springdata;

import com.seucontrolefinanceiro.application.entities.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataMongoBillRepository extends MongoRepository<Bill, String> {
    List<Bill> findByUserId(String id);
}
