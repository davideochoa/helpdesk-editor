package com.helpdeskeditor.application.app.web.charts;

import com.helpdeskeditor.application.util.ApexCharts.ApexChartsBuilder;
import com.helpdeskeditor.application.util.ApexCharts.config.builder.ChartBuilder;
import com.helpdeskeditor.application.util.ApexCharts.config.builder.DataLabelsBuilder;
import com.helpdeskeditor.application.util.ApexCharts.config.builder.FillBuilder;
import com.helpdeskeditor.application.util.ApexCharts.config.builder.PlotOptionsBuilder;
import com.helpdeskeditor.application.util.ApexCharts.config.builder.StrokeBuilder;
import com.helpdeskeditor.application.util.ApexCharts.config.builder.XAxisBuilder;
import com.helpdeskeditor.application.util.ApexCharts.config.chart.Type;
import com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.builder.BarBuilder;
import com.helpdeskeditor.application.util.ApexCharts.helper.Series;

public class VerticalBarChartExample extends ApexChartsBuilder {
    public VerticalBarChartExample() {
        withChart(ChartBuilder.get()
                .withType(Type.BAR)
                .build())
                .withPlotOptions(PlotOptionsBuilder.get()
                        .withBar(BarBuilder.get()
                                .withHorizontal(false)
                                .withColumnWidth("55%")
                                .build())
                        .build())
                .withDataLabels(DataLabelsBuilder.get()
                        .withEnabled(false).build())
                .withStroke(StrokeBuilder.get()
                        .withShow(true)
                        .withWidth(2.0)
                        .withColors("transparent")
                        .build())
                .withSeries(new Series<>("Net Profit","44","55","57","56","61","58","63","60","66"),
                        new Series<>("Revenue","76","85","101","98","87","105","91","114","94"),
                        new Series<>("Free Cash Flow","35","41","36","26","45","48","52","53","41"))
                .withXaxis(XAxisBuilder.get().withCategories("Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct").build())
                .withFill(FillBuilder.get()
                        .withOpacity(1.0).build());
    }
}
