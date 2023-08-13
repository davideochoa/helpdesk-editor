package com.helpdeskeditor.application.util.ApexCharts.config.grid.builder;

import com.helpdeskeditor.application.util.ApexCharts.config.grid.Column;

import java.util.List;

public class ColumnBuilder {
    private List<String> colors;
    private Double opacity;

    private ColumnBuilder() {
    }

    public static ColumnBuilder get() {
        return new ColumnBuilder();
    }

    public ColumnBuilder withColors(List<String> colors) {
        this.colors = colors;
        return this;
    }

    public ColumnBuilder withOpacity(Double opacity) {
        this.opacity = opacity;
        return this;
    }

    public Column build() {
        Column column = new Column();
        column.setColors(colors);
        column.setOpacity(opacity);
        return column;
    }
}
