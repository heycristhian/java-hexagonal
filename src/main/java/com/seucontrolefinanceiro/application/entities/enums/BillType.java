package com.seucontrolefinanceiro.application.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BillType {
    PAYMENT("Pagamento"),
    RECEIVEMENT("Recebimento");

    private String description;
}
