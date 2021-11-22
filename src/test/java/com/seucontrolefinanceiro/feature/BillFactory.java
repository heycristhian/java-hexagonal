package com.seucontrolefinanceiro.feature;

import com.seucontrolefinanceiro.application.entities.Bill;
import com.seucontrolefinanceiro.application.entities.enums.BillType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BillFactory {

    public static Bill getBill() {
        return Bill.builder()
                .billDescription("Academia")
                .amount(BigDecimal.valueOf(100.90))
                .everyMonth(true)
                .payDAy(LocalDate.now())
                .billType(BillType.PAYMENT)
                .paymentCategory(PaymentCategoryFactory.getCategoryWithId())
                .paid(false)
                .parent("123")
                .userId("432")
                .portion(24)
                .paidIn(LocalDate.now().plusDays(10))
                .build();
    }

    public static List<Bill> getBills() {
        var bill1 = Bill.builder()
                .billDescription("Academia")
                .amount(BigDecimal.valueOf(100.90))
                .everyMonth(true)
                .payDAy(LocalDate.now())
                .billType(BillType.PAYMENT)
                .paymentCategory(PaymentCategoryFactory.getCategoryWithId())
                .paid(false)
                .parent("123")
                .userId("432")
                .portion(24)
                .paidIn(LocalDate.now().plusDays(10))
                .build();

        var bill2 = Bill.builder()
                .billDescription("Mercado")
                .amount(BigDecimal.valueOf(1000.90))
                .everyMonth(true)
                .payDAy(LocalDate.now())
                .billType(BillType.PAYMENT)
                .paymentCategory(PaymentCategoryFactory.getCategoryWithId())
                .paid(false)
                .parent("987")
                .userId("432")
                .portion(24)
                .paidIn(LocalDate.now().plusDays(10))
                .build();

        return List.of(bill1, bill2);
    }

    public static Bill getBillWithId() {
        return Bill.builder()
                .id("123")
                .billDescription("Mercado")
                .amount(BigDecimal.valueOf(1000.90))
                .everyMonth(true)
                .payDAy(LocalDate.now())
                .billType(BillType.PAYMENT)
                .paymentCategory(PaymentCategoryFactory.getCategoryWithId())
                .paid(false)
                .parent("987")
                .userId("432")
                .portion(24)
                .paidIn(LocalDate.now().plusDays(10))
                .build();
    }

    public static Bill getBillIsEveryMonth() {
        return Bill.builder()
                .id("123")
                .billDescription("Mercado")
                .amount(BigDecimal.valueOf(1000.90))
                .everyMonth(true)
                .payDAy(LocalDate.now())
                .billType(BillType.PAYMENT)
                .paymentCategory(PaymentCategoryFactory.getCategoryWithId())
                .paid(false)
                .parent("987")
                .userId("432")
                .portion(10)
                .paidIn(LocalDate.now().plusDays(10))
                .build();
    }

    public static Bill getBillIsNotEveryMonth() {
        return Bill.builder()
                .id("123")
                .billDescription("Mercado")
                .amount(BigDecimal.valueOf(1000.90))
                .everyMonth(false)
                .payDAy(LocalDate.now())
                .billType(BillType.PAYMENT)
                .paymentCategory(PaymentCategoryFactory.getCategoryWithId())
                .paid(false)
                .parent("987")
                .userId("432")
                .portion(24)
                .paidIn(LocalDate.now().plusDays(10))
                .build();
    }

    public static List<Bill> getChildrenBills() {
        var bill1 = Bill.builder()
                .id("456")
                .billDescription("Mercado")
                .amount(BigDecimal.valueOf(1000.90))
                .everyMonth(true)
                .payDAy(LocalDate.now().plusMonths(1))
                .billType(BillType.PAYMENT)
                .paymentCategory(PaymentCategoryFactory.getCategoryWithId())
                .paid(false)
                .parent("123")
                .userId("432")
                .portion(10)
                .paidIn(LocalDate.now().plusDays(10))
                .build();

        var bill2 = Bill.builder()
                .id("789")
                .billDescription("Mercado")
                .amount(BigDecimal.valueOf(1000.90))
                .everyMonth(true)
                .payDAy(LocalDate.now().plusMonths(2))
                .billType(BillType.PAYMENT)
                .paymentCategory(PaymentCategoryFactory.getCategoryWithId())
                .paid(false)
                .parent("123")
                .userId("432")
                .portion(10)
                .paidIn(LocalDate.now().plusDays(10))
                .build();

        return List.of(bill1, bill2);
    }
}
