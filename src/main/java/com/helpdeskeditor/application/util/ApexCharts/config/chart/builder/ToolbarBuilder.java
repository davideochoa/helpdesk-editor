package com.helpdeskeditor.application.util.ApexCharts.config.chart.builder;

import com.helpdeskeditor.application.util.ApexCharts.config.chart.Toolbar;
import com.helpdeskeditor.application.util.ApexCharts.config.chart.toolbar.AutoSelected;
import com.helpdeskeditor.application.util.ApexCharts.config.chart.toolbar.Export;
import com.helpdeskeditor.application.util.ApexCharts.config.chart.toolbar.Tools;

public class ToolbarBuilder {
    private Boolean show;
    private Tools tools;
    private Export export;
    private AutoSelected autoSelected;

    private ToolbarBuilder() {
    }

    public static ToolbarBuilder get() {
        return new ToolbarBuilder();
    }

    public ToolbarBuilder withShow(Boolean show) {
        this.show = show;
        return this;
    }

    public ToolbarBuilder withTools(Tools tools) {
        this.tools = tools;
        return this;
    }

    public ToolbarBuilder withExport(Export export) {
        this.export = export;
        return this;
    }

    public ToolbarBuilder withAutoSelected(AutoSelected autoSelected) {
        this.autoSelected = autoSelected;
        return this;
    }

    public Toolbar build() {
        Toolbar toolbar = new Toolbar();
        toolbar.setShow(show);
        toolbar.setTools(tools);
        toolbar.setExport(export);
        toolbar.setAutoSelected(autoSelected);
        return toolbar;
    }
}
