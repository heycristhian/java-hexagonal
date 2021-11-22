package com.seucontrolefinanceiro.util;

import com.seucontrolefinanceiro.domain.model.PaymentCategory;
import com.seucontrolefinanceiro.domain.model.User;
import com.seucontrolefinanceiro.repository.PaymentCategoryRepository;
import com.seucontrolefinanceiro.repository.UserRepository;

import java.util.stream.Collectors;

public class ObjectExistUtil {
    public boolean paymentExist(PaymentCategory paymentCategory, PaymentCategoryRepository repository) {
        boolean exist = repository
                .findByDescriptionContainingIgnoreCase(paymentCategory.getDescription())
                .stream()
                .filter(x -> x.getBillType().equals(paymentCategory.getBillType()))
                .collect(Collectors.toList()).size() == 0;

        if(exist) {
            return true;
        }
        return false;
    }

    public boolean userExist(User user, UserRepository repository) {
        boolean exist = repository.findByCpfContainingIgnoreCase(user.getCpf())
                .stream()
                .filter(x -> x.getCpf().equals(user.getCpf()))
                .collect(Collectors.toList()).size() == 0;

        if(exist) {
            return true;
        }
        return false;
    }

}
