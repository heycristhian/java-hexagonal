package com.seucontrolefinanceiro.adapters.inbound.dto.response;

import com.seucontrolefinanceiro.application.entities.PaymentCategory;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PaymentCategoryResponse {

    private String id;
    @NonNull
    private String description;
    @NonNull
    private String billType;

    public PaymentCategoryResponse(PaymentCategory pc) {
        this.id = pc.getId();
        this.description = pc.getDescription();
        this.billType = pc.getBillType().getDescription();
    }

    public static List<PaymentCategoryResponse> converter(List<PaymentCategory> paymentCategories) {
        return paymentCategories.stream().map(PaymentCategoryResponse::new).collect(Collectors.toList());
    }
}
