package com.helpdeskeditor.application.util.ApexCharts.config.legend;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Position {
    TOP("top"),
    RIGHT("right"),
    BOTTOM("bottom"),
    LEFT("left");

    private final String value;

    Position(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
