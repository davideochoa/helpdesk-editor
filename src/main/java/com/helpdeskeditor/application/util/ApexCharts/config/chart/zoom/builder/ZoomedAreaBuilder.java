package com.helpdeskeditor.application.util.ApexCharts.config.chart.zoom.builder;

import com.helpdeskeditor.application.util.ApexCharts.config.chart.zoom.Fill;
import com.helpdeskeditor.application.util.ApexCharts.config.chart.zoom.Stroke;
import com.helpdeskeditor.application.util.ApexCharts.config.chart.zoom.ZoomedArea;

public class ZoomedAreaBuilder {
    private Fill fill;
    private Stroke stroke;

    private ZoomedAreaBuilder() {
    }

    public static ZoomedAreaBuilder get() {
        return new ZoomedAreaBuilder();
    }

    public ZoomedAreaBuilder withFill(Fill fill) {
        this.fill = fill;
        return this;
    }

    public ZoomedAreaBuilder withStroke(Stroke stroke) {
        this.stroke = stroke;
        return this;
    }

    public ZoomedArea build() {
        ZoomedArea zoomedArea = new ZoomedArea();
        zoomedArea.setFill(fill);
        zoomedArea.setStroke(stroke);
        return zoomedArea;
    }
}
