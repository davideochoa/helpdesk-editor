package com.helpdeskeditor.application.util.ApexCharts.config.datalables;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TextAnchor {
    START("start"),
    MIDDLE("middle"),
    END("end");

    private final String value;

    TextAnchor(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
