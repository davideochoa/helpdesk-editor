package com.helpdeskeditor.application.util.ApexCharts.config.tooltip.y.builder;

import com.helpdeskeditor.application.util.ApexCharts.config.tooltip.y.Title;

public final class TitleBuilder {
    private String formatter;

    private TitleBuilder() {
    }

    public static TitleBuilder get() {
        return new TitleBuilder();
    }

    public TitleBuilder withFormatter(String formatter) {
        this.formatter = formatter;
        return this;
    }

    public Title build() {
        Title title = new Title();
        title.setFormatter(formatter);
        return title;
    }
}
