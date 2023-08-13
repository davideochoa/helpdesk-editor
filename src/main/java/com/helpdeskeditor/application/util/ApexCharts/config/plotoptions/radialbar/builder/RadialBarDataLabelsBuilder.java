package com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.radialbar.builder;

import com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.radialbar.Name;
import com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.radialbar.RadialBarDataLabels;
import com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.radialbar.Total;
import com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.radialbar.Value;

public class RadialBarDataLabelsBuilder {
    private Boolean show;
    private Name name;
    private Value value;
    private Total total;

    private RadialBarDataLabelsBuilder() {
    }

    public static RadialBarDataLabelsBuilder get() {
        return new RadialBarDataLabelsBuilder();
    }

    public RadialBarDataLabelsBuilder withShow(Boolean show) {
        this.show = show;
        return this;
    }

    public RadialBarDataLabelsBuilder withName(Name name) {
        this.name = name;
        return this;
    }

    public RadialBarDataLabelsBuilder withValue(Value value) {
        this.value = value;
        return this;
    }

    public RadialBarDataLabelsBuilder withTotal(Total total) {
        this.total = total;
        return this;
    }

    public RadialBarDataLabels build() {
        RadialBarDataLabels dataLabels = new RadialBarDataLabels();
        dataLabels.setShow(show);
        dataLabels.setName(name);
        dataLabels.setValue(value);
        dataLabels.setTotal(total);
        return dataLabels;
    }
}
