package com.seucontrolefinanceiro.application.ports.services;

import com.seucontrolefinanceiro.application.entities.PaymentCategory;

import java.util.List;

public interface PaymentCategoryService {

    List<PaymentCategory> findAll();

    PaymentCategory findById(String id);

    PaymentCategory insert(PaymentCategory paymentCategory);

    void delete(String id);

    PaymentCategory update(PaymentCategory paymentCategory);

    List<PaymentCategory> findByDescriptionContainingIgnoreCase(String description);
}
