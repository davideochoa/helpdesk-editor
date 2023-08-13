package com.helpdeskeditor.application.util.ApexCharts.config;


import com.helpdeskeditor.application.util.ApexCharts.config.grid.Column;
import com.helpdeskeditor.application.util.ApexCharts.config.grid.Padding;
import com.helpdeskeditor.application.util.ApexCharts.config.grid.Position;
import com.helpdeskeditor.application.util.ApexCharts.config.grid.Row;
import com.helpdeskeditor.application.util.ApexCharts.config.grid.Xaxis;
import com.helpdeskeditor.application.util.ApexCharts.config.grid.Yaxis;

public class Grid {
    private Boolean show;
    private String borderColor;
    private Double strokeDashArray;
    private Position position;
    private Xaxis xaxis;
    private Yaxis yaxis;
    private Row row;
    private Column column;
    private Padding padding;

    public Grid() {
    }

    public Boolean getShow() {
        return show;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public Double getStrokeDashArray() {
        return strokeDashArray;
    }

    public Position getPosition() {
        return position;
    }

    public Xaxis getXaxis() {
        return xaxis;
    }

    public Yaxis getYaxis() {
        return yaxis;
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    public Padding getPadding() {
        return padding;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public void setStrokeDashArray(Double strokeDashArray) {
        this.strokeDashArray = strokeDashArray;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setXaxis(Xaxis xaxis) {
        this.xaxis = xaxis;
    }

    public void setYaxis(Yaxis yaxis) {
        this.yaxis = yaxis;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public void setPadding(Padding padding) {
        this.padding = padding;
    }

}
