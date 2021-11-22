package com.seucontrolefinanceiro.application.ports.repositories;

import com.seucontrolefinanceiro.application.entities.PaymentCategory;

import java.util.List;
import java.util.Optional;

public interface PaymentCategoryRepository {

    List<PaymentCategory> findAll();

    Optional<PaymentCategory> findById(String id);

    void deleteById(String id);

    PaymentCategory insert(PaymentCategory paymentCategory);

    PaymentCategory save(PaymentCategory paymentCategory);

    List<PaymentCategory> findByDescriptionContainingIgnoreCase(String description);

}
