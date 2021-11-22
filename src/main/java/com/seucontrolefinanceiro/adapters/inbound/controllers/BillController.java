package com.seucontrolefinanceiro.adapters.inbound.controllers;

import com.seucontrolefinanceiro.application.entities.Bill;
import com.seucontrolefinanceiro.adapters.inbound.dto.response.BillResponse;
import com.seucontrolefinanceiro.adapters.inbound.dto.response.UserResponse;
import com.seucontrolefinanceiro.adapters.inbound.dto.request.BillRequest;
import com.seucontrolefinanceiro.application.ports.services.BillService;
import com.seucontrolefinanceiro.application.ports.services.PaymentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("scf-service/bills")
public class BillController {

    @Autowired
    private BillService service;

    @Autowired
    private PaymentCategoryService paymentCategoryService;

    @GetMapping
    public ResponseEntity<List<BillResponse>> find(String query) {
        List<Bill> bills = service.findAll();
        return ResponseEntity.ok().body(BillResponse.converter(bills));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillResponse> findById(@PathVariable String id) {
        Bill bill = service.findById(id);
        return ResponseEntity.ok().body(new BillResponse(bill));
    }

    @PostMapping
    public ResponseEntity<BillResponse> insert(@RequestBody @Validated BillRequest form, UriComponentsBuilder uriBuilder) {
        Bill bill = form.converter();
        service.save(bill);
        URI uri = uriBuilder.path("scf-service/bills/{id}").buildAndExpand(bill.getId()).toUri();
        return ResponseEntity.created(uri).body(new BillResponse(bill));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(String id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping()
    public ResponseEntity<UserResponse> update(@RequestBody @Validated BillRequest form, UriComponentsBuilder uriBuilder) {
        Bill bill = form.converter();
        bill.setPaymentCategory(paymentCategoryService.findByDescriptionContainingIgnoreCase(bill.getPaymentCategory().getDescription()).get(0));
        service.update(bill);
        return ResponseEntity.noContent().build();
    }
}
