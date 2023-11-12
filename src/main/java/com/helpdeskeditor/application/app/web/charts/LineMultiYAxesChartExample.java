package com.helpdeskeditor.application.app.web.charts;

import com.helpdeskeditor.application.app.service.FolioService;
import com.helpdeskeditor.application.util.ApexCharts.ApexChartsBuilder;
import com.helpdeskeditor.application.util.ApexCharts.config.XAxis;
import com.helpdeskeditor.application.util.ApexCharts.config.builder.*;
import com.helpdeskeditor.application.util.ApexCharts.config.chart.Type;
import com.helpdeskeditor.application.util.ApexCharts.config.chart.builder.ZoomBuilder;
import com.helpdeskeditor.application.util.ApexCharts.config.grid.builder.RowBuilder;
import com.helpdeskeditor.application.util.ApexCharts.config.stroke.Curve;
import com.helpdeskeditor.application.util.ApexCharts.config.yaxis.builder.AxisBorderBuilder;
import com.helpdeskeditor.application.util.ApexCharts.config.yaxis.builder.TitleBuilder;
import com.helpdeskeditor.application.util.ApexCharts.helper.Series;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LineMultiYAxesChartExample extends ApexChartsBuilder {
    public LineMultiYAxesChartExample(Series series,List<String> categorias) {

        withChart(ChartBuilder.get()
                .withType(Type.LINE)
                .withZoom(ZoomBuilder.get()
                        .withEnabled(false)
                        .build())
                .build())
                .withStroke(StrokeBuilder.get()
                        .withCurve(Curve.STRAIGHT)
                        .build())
                .withGrid(GridBuilder.get()
                        .withRow(RowBuilder.get()
                                .withColors("#f3f3f3", "transparent")
                                .withOpacity(0.5).build()
                        ).build())
                .withXaxis(XAxisBuilder.get()
                        .withCategories(categorias)
                        .build())
                .withYaxis(YAxisBuilder.get()
                        .withTitle(TitleBuilder.get().withText(series.getName()).build())
                        .withAxisBorder(AxisBorderBuilder.get().withShow(true).build())
                        .build()
                )
                .withSeries(series);
    }
}
