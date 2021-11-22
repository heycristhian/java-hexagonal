package com.seucontrolefinanceiro.application.services;

import com.seucontrolefinanceiro.application.entities.PaymentCategory;
import com.seucontrolefinanceiro.application.ports.repositories.PaymentCategoryRepository;
import com.seucontrolefinanceiro.application.ports.services.PaymentCategoryService;
import com.seucontrolefinanceiro.application.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

public class PaymentCategoryServiceImpl implements PaymentCategoryService {

    private final PaymentCategoryRepository repository;

    public PaymentCategoryServiceImpl(PaymentCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PaymentCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public PaymentCategory findById(String id) {
        Optional<PaymentCategory> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }

    @Override
    public PaymentCategory insert(PaymentCategory paymentCategory) {
        return repository.insert(paymentCategory);
    }

    @Override
    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public PaymentCategory update(PaymentCategory paymentCategory) {
        findById(paymentCategory.getId());
        return repository.save(paymentCategory);
    }

    @Override
    public List<PaymentCategory> findByDescriptionContainingIgnoreCase(String description) {
        return repository.findByDescriptionContainingIgnoreCase(description);
    }
}
