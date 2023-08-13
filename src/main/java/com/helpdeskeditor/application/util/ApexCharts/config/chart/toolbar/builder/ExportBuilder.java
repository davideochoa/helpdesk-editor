package com.helpdeskeditor.application.util.ApexCharts.config.chart.toolbar.builder;

import com.helpdeskeditor.application.util.ApexCharts.config.chart.toolbar.*;

public class ExportBuilder {
    private Csv csv;
    private Svg svg;
    private Png png;

    private ExportBuilder() {
    }

    public static ExportBuilder get() {
        return new ExportBuilder();
    }

    public ExportBuilder withCsv(Csv csv) {
        this.csv = csv;
        return this;
    }

    public ExportBuilder withSvg(Svg svg) {
        this.svg = svg;
        return this;
    }

    public ExportBuilder withPng(Png png) {
        this.png = png;
        return this;
    }

    public Export build() {
        Export export = new Export();
        export.setCsv(csv);
        export.setSvg(svg);
        export.setPng(png);
        return export;
    }
}
