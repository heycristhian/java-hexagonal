package com.seucontrolefinanceiro.adapters.inbound.dto.request;

import com.seucontrolefinanceiro.application.entities.Bill;
import com.seucontrolefinanceiro.application.entities.enums.BillType;
import com.seucontrolefinanceiro.application.entities.PaymentCategory;
import com.seucontrolefinanceiro.application.services.PaymentCategoryServiceImpl;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class BillRequest {

    @Autowired
    private PaymentCategoryServiceImpl service;

    private String id;
    @NonNull
    private String billDescription;
    @NonNull
    private BigDecimal amount;
    @NonNull
    private boolean everyMonth;
    @NonNull
    private boolean sameAmount;
    @NonNull
    private LocalDate payDAy;
    @NonNull
    private BillType billType;
    @NonNull
    private PaymentCategory paymentCategory;
    @NonNull
    private boolean paid;
    @NonNull
    private String userId;
    private String parentId;
    private Integer portion;
    private LocalDate paidIn;

    public Bill converter() {
        return Bill.builder()
                .id(this.getId())
                .billDescription(this.getBillDescription())
                .amount(this.getAmount())
                .everyMonth(this.isEveryMonth())
                .payDAy(this.getPayDAy())
                .billType(this.getBillType())
                .paymentCategory(this.getPaymentCategory())
                .paid(this.isPaid())
                .userId(this.getUserId())
                .portion(this.getPortion())
                .parent(this.getParentId())
                .paidIn(this.getPaidIn())
                .build();
    }
}
