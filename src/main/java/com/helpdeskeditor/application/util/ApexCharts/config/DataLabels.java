package com.helpdeskeditor.application.util.ApexCharts.config;


import com.helpdeskeditor.application.util.ApexCharts.config.datalables.DropShadow;
import com.helpdeskeditor.application.util.ApexCharts.config.datalables.Style;
import com.helpdeskeditor.application.util.ApexCharts.config.datalables.TextAnchor;

import java.util.List;

public class DataLabels {
    private Boolean enabled;
    private List<Double> enabledOnSeries;
    private String formatter;
    private TextAnchor textAnchor;
    private Double offsetX;
    private Double offsetY;
    private Style style;
    private DropShadow dropShadow;

    public DataLabels() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public List<Double> getEnabledOnSeries() {
        return enabledOnSeries;
    }

    public String getFormatter() {
        return formatter;
    }

    public TextAnchor getTextAnchor() {
        return textAnchor;
    }

    public Double getOffsetX() {
        return offsetX;
    }

    public Double getOffsetY() {
        return offsetY;
    }

    public Style getStyle() {
        return style;
    }

    public DropShadow getDropShadow() {
        return dropShadow;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setEnabledOnSeries(List<Double> enabledOnSeries) {
        this.enabledOnSeries = enabledOnSeries;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public void setTextAnchor(TextAnchor textAnchor) {
        this.textAnchor = textAnchor;
    }

    public void setOffsetX(Double offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(Double offsetY) {
        this.offsetY = offsetY;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public void setDropShadow(DropShadow dropShadow) {
        this.dropShadow = dropShadow;
    }

}
