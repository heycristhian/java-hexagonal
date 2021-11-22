package com.seucontrolefinanceiro.adapters.outbound.persistence.repositories;

import com.seucontrolefinanceiro.adapters.outbound.persistence.springdata.SpringDataMongoPaymentCategoryRepository;
import com.seucontrolefinanceiro.application.entities.PaymentCategory;
import com.seucontrolefinanceiro.application.ports.repositories.PaymentCategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MongoPaymentCategoryRepository implements PaymentCategoryRepository {

    private SpringDataMongoPaymentCategoryRepository repository;

    @Override
    public List<PaymentCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<PaymentCategory> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public PaymentCategory insert(PaymentCategory paymentCategory) {
        return repository.insert(paymentCategory);
    }

    @Override
    public PaymentCategory save(PaymentCategory paymentCategory) {
        return repository.save(paymentCategory);
    }

    @Override
    public List<PaymentCategory> findByDescriptionContainingIgnoreCase(String description) {
        return repository.findByDescriptionContainingIgnoreCase(description);
    }
}
