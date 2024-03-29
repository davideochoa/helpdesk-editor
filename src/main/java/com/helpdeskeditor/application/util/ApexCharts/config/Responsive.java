package com.helpdeskeditor.application.util.ApexCharts.config;


import com.helpdeskeditor.application.util.ApexCharts.config.responsive.Options;

public class Responsive {
    private Double breakpoint;
    private Options options;

    public Responsive() {
    }

    public Double getBreakpoint() {
        return breakpoint;
    }

    public Options getOptions() {
        return options;
    }

    public void setBreakpoint(Double breakpoint) {
        this.breakpoint = breakpoint;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

}
