package com.helpdeskeditor.application.util.ApexCharts.config.builder;

import com.helpdeskeditor.application.util.ApexCharts.config.Responsive;
import com.helpdeskeditor.application.util.ApexCharts.config.responsive.Options;

public class ResponsiveBuilder {
    private Double breakpoint;
    private Options options;

    private ResponsiveBuilder() {
    }

    public static ResponsiveBuilder get() {
        return new ResponsiveBuilder();
    }

    public ResponsiveBuilder withBreakpoint(Double breakpoint) {
        this.breakpoint = breakpoint;
        return this;
    }

    public ResponsiveBuilder withOptions(Options options) {
        this.options = options;
        return this;
    }

    public Responsive build() {
        Responsive responsive = new Responsive();
        responsive.setBreakpoint(breakpoint);
        responsive.setOptions(options);
        return responsive;
    }
}
