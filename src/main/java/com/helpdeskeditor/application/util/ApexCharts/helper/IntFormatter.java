package com.helpdeskeditor.application.util.ApexCharts.helper;

public class IntFormatter implements Formatter {

    @Override
    public String getString() {
        return "function(val) {return parseInt(val);}";
    }
}
