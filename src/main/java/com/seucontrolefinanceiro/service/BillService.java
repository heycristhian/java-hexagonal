package com.seucontrolefinanceiro.service;

import com.seucontrolefinanceiro.domain.model.Bill;
import com.seucontrolefinanceiro.domain.model.User;
import com.seucontrolefinanceiro.exception.ObjectNotFoundException;
import com.seucontrolefinanceiro.repository.BillRepository;
import com.seucontrolefinanceiro.repository.UserRepository;
import com.seucontrolefinanceiro.util.GenerateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillService {

    @Autowired
    private BillRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private final int PORTION_DEFAULT = 11;

    public List<Bill> findAll() {
        return repository.findAll();
    }

    public Bill findById(String id) {
        Optional<Bill> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }

    public Bill save(Bill bill) {
        User user = userService.findById(bill.getUserId());
        List<Bill> allBillsByUserId = repository.findByUserId(bill.getUserId());
        user.setBills(allBillsByUserId);
        analyzeMonthlyBill(bill, user);
        String parentId = bill.getParent() == null ? bill.getId() : bill.getParent();
        bill.setParent(parentId);
        repository.save(bill);
        userRepository.save(user);
        return bill;
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public Bill update(Bill newBill) {
        if (!newBill.isPaid()) {
            Bill oldBill = findById(newBill.getId());
            boolean oldBillIsEveryMonth = oldBill.isEveryMonth();
            boolean newBillIsEveryMonth = newBill.isEveryMonth();
            newBill.setParent((newBill.getParent() == null) ? newBill.getId() : newBill.getParent());

            if (newBillIsEveryMonth && !oldBillIsEveryMonth) {
                createChildrenBill(newBill);
            } else if (oldBillIsEveryMonth && !newBillIsEveryMonth) {
                removeChildrenBill(newBill);
            } else if (newBill.isEveryMonth()) {
                onlyUpdateChildrenBill(newBill);
            }
            repository.save(newBill);
            return newBill;
        } else {
            return pay(newBill);
        }
    }


    private Bill pay(Bill bill) {
        repository.save(bill);
        return bill;
    }

    public void deleteAll(List<Bill> bills) {
        repository.deleteAll(bills);
    }

    private void analyzeMonthlyBill(Bill bill, User user) {
        if (bill.isEveryMonth()) {
            Integer index = bill.getPortion() == null ? PORTION_DEFAULT : bill.getPortion();
            List<Bill> bills = GenerateObject.generateBills(bill, index);
            repository.insert(bill);
            user.addToListBill(bill);

            for (Bill b : bills) {
                b.setParent(bill.getId());
                repository.insert(b);
                user.addToListBill(b);
            }
        } else {
            repository.insert(bill);
            user.addToListBill(bill);
        }
    }

    private void createChildrenBill(Bill newBill) {
        List<Bill> bills;
        User user = userService.findById(newBill.getUserId());
        user.setBills(repository.findByUserId(newBill.getUserId()));

        Integer index = newBill.getPortion() == null ? PORTION_DEFAULT : newBill.getPortion();
        bills = GenerateObject.generateBills(newBill, index);

        for (Bill b : bills) {
            b.setParent(newBill.getId());
            repository.insert(b);
            user.addToListBill(b);
        }
        userRepository.save(user);
    }

    private void removeChildrenBill(Bill newBill) {
        String parentId = newBill.getParent() == null ? newBill.getId() : newBill.getParent();

        repository.findByUserId(newBill.getUserId())
                .stream().filter(x ->
                !x.isPaid()
                        && x.getParent().compareTo(parentId) == 0)
                .collect(Collectors.toList()).forEach(x -> repository.delete(x));

        newBill.setPortion(null);
    }

    private void onlyUpdateChildrenBill(Bill newBill) {
        List<Bill> bills;
        String parentId = newBill.getParent() == null ? newBill.getId() : newBill.getParent();
        try {
            bills = repository.findByUserId(newBill.getUserId())
                    .stream()
                    .filter(x -> x.getParent().equals(parentId)
                            && !x.isPaid()
                            && !x.getId().equals(newBill.getId()))
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw e;
        }

        for (Bill b : bills) {
            GenerateObject.cloneBill(b, newBill);
            repository.save(b);
        }
    }
}
