package com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.radialbar.builder;

import com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.radialbar.Value;
import com.helpdeskeditor.application.util.ApexCharts.helper.Formatter;

public class ValueBuilder {
    private Boolean show;
    private String fontSize;
    private String color;
    private Double offsetY;
    private String formatter;

    private ValueBuilder() {
    }

    public static ValueBuilder get() {
        return new ValueBuilder();
    }

    public ValueBuilder withShow(Boolean show) {
        this.show = show;
        return this;
    }

    public ValueBuilder withFontSize(String fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public ValueBuilder withColor(String color) {
        this.color = color;
        return this;
    }

    public ValueBuilder withOffsetY(Double offsetY) {
        this.offsetY = offsetY;
        return this;
    }

    public ValueBuilder withFormatter(String formatter) {
        this.formatter = formatter;
        return this;
    }

    public ValueBuilder withFormatter(Formatter formatter) {
        this.formatter = formatter.getString();
        return this;
    }

    public Value build() {
        Value value = new Value();
        value.setShow(show);
        value.setFontSize(fontSize);
        value.setColor(color);
        value.setOffsetY(offsetY);
        value.setFormatter(formatter);
        return value;
    }
}
