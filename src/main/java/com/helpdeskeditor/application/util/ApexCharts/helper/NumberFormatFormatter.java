package com.helpdeskeditor.application.util.ApexCharts.helper;

public class NumberFormatFormatter implements Formatter {
    private int fixed;

    public NumberFormatFormatter(int fixed) {
        this.fixed = fixed;
    }

    @Override
    public String getString() {
        return "function(val) {return val.toFixed("+fixed+");}";
    }
}

