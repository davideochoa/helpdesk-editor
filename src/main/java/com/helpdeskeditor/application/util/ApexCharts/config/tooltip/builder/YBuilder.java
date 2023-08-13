package com.helpdeskeditor.application.util.ApexCharts.config.tooltip.builder;

import com.helpdeskeditor.application.util.ApexCharts.config.tooltip.Y;
import com.helpdeskeditor.application.util.ApexCharts.config.tooltip.y.Title;
import com.helpdeskeditor.application.util.ApexCharts.helper.Formatter;

public class YBuilder {
    private String formatter;
    private Title title;

    private YBuilder() {
    }

    public static YBuilder get() {
        return new YBuilder();
    }

    public YBuilder withFormatter(String formatter) {
        this.formatter = formatter;
        return this;
    }

    public YBuilder withFormatter(Formatter formatter) {
        this.formatter = formatter.getString();
        return this;
    }

    public YBuilder withTitle(Title title) {
        this.title = title;
        return this;
    }

    public Y build() {
        Y y = new Y();
        y.setFormatter(formatter);
        y.setTitle(title);
        return y;
    }
}
