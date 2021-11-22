package com.seucontrolefinanceiro.adapters.inbound.controllers;

import com.seucontrolefinanceiro.application.entities.PaymentCategory;
import com.seucontrolefinanceiro.adapters.inbound.dto.response.PaymentCategoryResponse;
import com.seucontrolefinanceiro.adapters.inbound.dto.response.UserResponse;
import com.seucontrolefinanceiro.adapters.inbound.dto.request.PaymentCategoryRequest;
import com.seucontrolefinanceiro.application.ports.services.PaymentCategoryService;
import com.seucontrolefinanceiro.application.services.PaymentCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("scf-service/payment-categories")
public class PaymentCategoryController {

    @Autowired
    private PaymentCategoryService service;

    @GetMapping
    public ResponseEntity<List<PaymentCategoryResponse>> find(String query) {
        List<PaymentCategory> paymentCategories = service.findAll();
        return ResponseEntity.ok().body(PaymentCategoryResponse.converter((paymentCategories)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentCategoryResponse> findById(@PathVariable String id) {
        PaymentCategory paymentCategory = service.findById(id);
        return ResponseEntity.ok().body(new PaymentCategoryResponse(paymentCategory));
    }

    @PostMapping
    public ResponseEntity<PaymentCategoryResponse> insert(@RequestBody @Validated PaymentCategoryRequest form, UriComponentsBuilder uriBuilder) {
        PaymentCategory paymentCategory = form.converter();
        paymentCategory = service.insert(paymentCategory);
        URI uri = uriBuilder.path("scf-service/payment-categories/{id}").buildAndExpand(paymentCategory.getId()).toUri();
        return ResponseEntity.created(uri).body(new PaymentCategoryResponse(paymentCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<UserResponse> update(@RequestBody @Validated PaymentCategoryRequest form, UriComponentsBuilder uriBuilder) {
        PaymentCategory paymentCategory = form.converter();
        paymentCategory = service.update(paymentCategory);
        URI uri = uriBuilder.path("scf-service/payment-categories/{id}").buildAndExpand(paymentCategory.getId()).toUri();
        return ResponseEntity.noContent().build();
    }
}
