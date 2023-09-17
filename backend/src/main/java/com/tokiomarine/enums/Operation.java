package com.tokiomarine.enums;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum Operation implements Serializable {

    A("A"), B("B"), C("C"), D("D");

    private String code;

    Operation(String code) {
        this.code = code;
    }

    public static Operation forName(String name) {
        for (Operation value : Operation.values()) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        return null;
    }
}
