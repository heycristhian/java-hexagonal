package com.seucontrolefinanceiro.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StandardException {

    private final Long timestamp;
    private final Integer status;
    private final String error;
    private final String message;
    private final String path;
}
