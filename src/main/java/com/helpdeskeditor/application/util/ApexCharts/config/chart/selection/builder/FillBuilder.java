package com.helpdeskeditor.application.util.ApexCharts.config.chart.selection.builder;

import com.helpdeskeditor.application.util.ApexCharts.config.chart.selection.Fill;

public class FillBuilder {
    private String color;
    private Double opacity;

    private FillBuilder() {
    }

    public static FillBuilder get() {
        return new FillBuilder();
    }

    public FillBuilder withColor(String color) {
        this.color = color;
        return this;
    }

    public FillBuilder withOpacity(Double opacity) {
        this.opacity = opacity;
        return this;
    }

    public Fill build() {
        Fill fill = new Fill();
        fill.setColor(color);
        fill.setOpacity(opacity);
        return fill;
    }
}
