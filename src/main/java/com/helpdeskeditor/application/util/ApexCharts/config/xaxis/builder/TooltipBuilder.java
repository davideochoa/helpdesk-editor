package com.helpdeskeditor.application.util.ApexCharts.config.xaxis.builder;

import com.helpdeskeditor.application.util.ApexCharts.config.xaxis.Tooltip;

public class TooltipBuilder {
    private Boolean enabled;
    private Double offsetY;

    private TooltipBuilder() {
    }

    public static TooltipBuilder get() {
        return new TooltipBuilder();
    }

    public TooltipBuilder withEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public TooltipBuilder withOffsetY(Double offsetY) {
        this.offsetY = offsetY;
        return this;
    }

    public Tooltip build() {
        Tooltip tooltip = new Tooltip();
        tooltip.setEnabled(enabled);
        tooltip.setOffsetY(offsetY);
        return tooltip;
    }
}
