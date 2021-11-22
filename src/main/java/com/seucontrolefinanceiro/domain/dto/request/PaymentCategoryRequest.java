package com.seucontrolefinanceiro.domain.dto.request;

import com.seucontrolefinanceiro.domain.model.BillType;
import com.seucontrolefinanceiro.domain.model.PaymentCategory;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@RequiredArgsConstructor
public class PaymentCategoryRequest {

    @Id
    private String id;
    @NonNull
    private String description;
    @NonNull
    private boolean mutable;
    @NonNull
    private BillType billType;

    public PaymentCategory converter() {
        return PaymentCategory.builder()
                .id(this.id)
                .description(this.description)
                .billType(this.billType)
                .build();
    }
}
