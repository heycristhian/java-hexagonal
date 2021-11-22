package com.seucontrolefinanceiro.adapters.outbound.persistence.springdata;

import com.seucontrolefinanceiro.application.entities.PaymentCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataMongoPaymentCategoryRepository extends MongoRepository<PaymentCategory, String> {
    List<PaymentCategory> findByDescriptionContainingIgnoreCase(String description);
}
