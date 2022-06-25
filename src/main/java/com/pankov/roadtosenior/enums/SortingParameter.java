package com.pankov.roadtosenior.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SortingParameter {
    rating("rating"),
    price("price");

    @Getter private final String value;

    public static SortingParameter findByValue(String value) {
        SortingParameter parameter = null;
        for (SortingParameter param : values()) {
            if (param.getValue().equalsIgnoreCase(value)) {
                parameter = param;
                break;
            }
        }
        return parameter;
    }
}
