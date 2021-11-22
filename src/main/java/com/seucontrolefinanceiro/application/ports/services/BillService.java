package com.seucontrolefinanceiro.application.ports.services;

import com.seucontrolefinanceiro.application.entities.Bill;

import java.util.List;

public interface BillService {

    List<Bill> findAll();

    Bill findById(String id);

    Bill save(Bill bill);

    void delete(String id);

    Bill update(Bill newBill);

    void deleteAll(List<Bill> bills);
}
