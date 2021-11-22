package com.seucontrolefinanceiro.feature;

import com.seucontrolefinanceiro.domain.model.BillType;
import com.seucontrolefinanceiro.domain.model.PaymentCategory;

import java.util.List;

public class PaymentCategoryFactory {

    public static List<PaymentCategory> getAllCategories() {
        var category1 = PaymentCategory.builder()
                .description("ACAD DOS MONSTRAO")
                .billType(BillType.PAYMENT)
                .build();

        var category2 = PaymentCategory.builder()
                .description("FACULDADE RIP")
                .billType(BillType.RECEIVEMENT)
                .build();

        return List.of(category1, category2);
    }

    public static PaymentCategory getCategory() {
        return PaymentCategory.builder()
                .description("FACULDADE RIP")
                .billType(BillType.RECEIVEMENT)
                .build();
    }

    public static PaymentCategory getCategoryWithId() {
        return PaymentCategory.builder()
                .id("123456789")
                .description("ACAD DOS MONSTRAO")
                .billType(BillType.PAYMENT)
                .build();
    }

    public static List<PaymentCategory> getCategoryContainingDescription() {
        var category1 = PaymentCategory.builder()
                .description("ACADEMIA XPLOUD")
                .billType(BillType.PAYMENT)
                .build();

        var category2 = PaymentCategory.builder()
                .description("ACADEMIA LEQUIPE")
                .billType(BillType.PAYMENT)
                .build();
        return List.of(category1, category2);
    }
}
