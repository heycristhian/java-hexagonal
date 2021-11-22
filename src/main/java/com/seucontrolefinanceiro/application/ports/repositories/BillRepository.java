package com.seucontrolefinanceiro.application.ports.repositories;

import com.seucontrolefinanceiro.application.entities.Bill;

import java.util.List;
import java.util.Optional;

public interface BillRepository {

    List<Bill> findAll();

    Optional<Bill> findById(String id);

    List<Bill> findByUserId(String id);

    Bill save(Bill bill);

    void deleteById(String id);

    void deleteAll(List<Bill> bills);

    void delete(Bill bill);

    Bill insert(Bill bill);
}
