package com.seucontrolefinanceiro.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document()
@EqualsAndHashCode(of = {"description", "billType"})
@ToString(of = {"description", "billType"})
public class PaymentCategory {

    @Id
    private String id;
    @NonNull
    private String description;
    private BillType billType;
}
