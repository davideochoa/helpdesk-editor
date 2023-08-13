package com.helpdeskeditor.application.util.ApexCharts.config.legend.builder;

import com.helpdeskeditor.application.util.ApexCharts.config.legend.Labels;

public class LabelsBuilder {
    private String color;
    private Boolean useSeriesColors;

    private LabelsBuilder() {
    }

    public static LabelsBuilder get() {
        return new LabelsBuilder();
    }

    public LabelsBuilder withColor(String color) {
        this.color = color;
        return this;
    }

    public LabelsBuilder withUseSeriesColors(Boolean useSeriesColors) {
        this.useSeriesColors = useSeriesColors;
        return this;
    }

    public Labels build() {
        Labels labels = new Labels();
        labels.setColor(color);
        labels.setUseSeriesColors(useSeriesColors);
        return labels;
    }
}
