package com.tokiomarine.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BadRequestException extends Exception {
    private static final long serialVersionUID = 1L;

    private String message;

}
