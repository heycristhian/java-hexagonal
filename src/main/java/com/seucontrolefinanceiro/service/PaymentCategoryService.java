package com.seucontrolefinanceiro.service;

import com.seucontrolefinanceiro.domain.model.PaymentCategory;
import com.seucontrolefinanceiro.exception.ObjectNotFoundException;
import com.seucontrolefinanceiro.repository.PaymentCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentCategoryService {

    @Autowired
    private PaymentCategoryRepository repository;

    public List<PaymentCategory> findAll() {
        return repository.findAll();
    }

    public PaymentCategory findById(String id) {
        Optional<PaymentCategory> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }

    public PaymentCategory insert(PaymentCategory paymentCategory) {
        return repository.insert(paymentCategory);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public PaymentCategory update(PaymentCategory paymentCategory) {
        findById(paymentCategory.getId());
        return repository.save(paymentCategory);
    }

    public List<PaymentCategory> findByDescriptionContainingIgnoreCase(String description) {
        return repository.findByDescriptionContainingIgnoreCase(description);
    }
}
