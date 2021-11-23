package com.seucontrolefinanceiro.adapters.outbound.persistence.repositories;

import com.seucontrolefinanceiro.adapters.outbound.persistence.springdata.SpringDataMongoBillRepository;
import com.seucontrolefinanceiro.application.entities.Bill;
import com.seucontrolefinanceiro.application.ports.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Primary
public class MongoBillRepository implements BillRepository {

    @Autowired
    private SpringDataMongoBillRepository repository;

    @Override
    public List<Bill> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Bill> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Bill> findByUserId(String id) {
        return repository.findByUserId(id);
    }

    @Override
    public Bill save(Bill bill) {
        return repository.save(bill);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(List<Bill> bills) {
        repository.deleteAll(bills);
    }

    @Override
    public void delete(Bill bill) {
        repository.delete(bill);
    }

    @Override
    public Bill insert(Bill bill) {
        return repository.insert(bill);
    }
}
