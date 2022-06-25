package com.pankov.roadtosenior.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SortingOrder {
    asc("asc"),
    desc("desc");

    @Getter
    private final String value;

    public static SortingOrder findByValue(String value) {
        SortingOrder parameter = null;
        for (SortingOrder param : values()) {
            if (param.getValue().equalsIgnoreCase(value)) {
                parameter = param;
                break;
            }
        }
        return parameter;
    }
}
