package com.helpdeskeditor.application.util.ApexCharts.helper;

public class StringFormatter implements Formatter {

    @Override
    public String getString() {
        return "function(val) {return val.toString()}";
    }
}
