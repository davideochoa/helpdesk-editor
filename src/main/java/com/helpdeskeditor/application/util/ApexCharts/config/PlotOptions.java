package com.helpdeskeditor.application.util.ApexCharts.config;

import com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.Bar;
import com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.Candlestick;
import com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.Heatmap;
import com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.Pie;
import com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.Radar;
import com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.RadialBar;
import com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.boxplot.BoxPlot;

public class PlotOptions {
    private Bar bar;
    private Candlestick candlestick;
    private Heatmap heatmap;
    private Pie pie;
    private Radar radar;
    private RadialBar radialBar;
    private BoxPlot boxPlot;


    public PlotOptions() {
    }

    public Bar getBar() {
        return bar;
    }

    public Candlestick getCandlestick() {
        return candlestick;
    }

    public Heatmap getHeatmap() {
        return heatmap;
    }

    public Pie getPie() {
        return pie;
    }

    public Radar getRadar() {
        return radar;
    }

    public RadialBar getRadialBar() {
        return radialBar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public void setCandlestick(Candlestick candlestick) {
        this.candlestick = candlestick;
    }

    public void setHeatmap(Heatmap heatmap) {
        this.heatmap = heatmap;
    }

    public void setPie(Pie pie) {
        this.pie = pie;
    }

    public void setRadar(Radar radar) {
        this.radar = radar;
    }

    public void setRadialBar(RadialBar radialBar) {
        this.radialBar = radialBar;
    }

    public BoxPlot getBoxPlot() {
        return boxPlot;
    }

    public void setBoxPlot(BoxPlot boxPlot) {
        this.boxPlot = boxPlot;
    }
}
