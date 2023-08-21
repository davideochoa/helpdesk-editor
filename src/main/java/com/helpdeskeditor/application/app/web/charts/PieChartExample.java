package com.helpdeskeditor.application.app.web.charts;

import com.helpdeskeditor.application.util.ApexCharts.ApexChartsBuilder;
import com.helpdeskeditor.application.util.ApexCharts.config.builder.ChartBuilder;
import com.helpdeskeditor.application.util.ApexCharts.config.builder.LegendBuilder;
import com.helpdeskeditor.application.util.ApexCharts.config.builder.ResponsiveBuilder;
import com.helpdeskeditor.application.util.ApexCharts.config.chart.Toolbar;
import com.helpdeskeditor.application.util.ApexCharts.config.chart.Type;
import com.helpdeskeditor.application.util.ApexCharts.config.chart.toolbar.Export;
import com.helpdeskeditor.application.util.ApexCharts.config.legend.Position;
import com.helpdeskeditor.application.util.ApexCharts.config.responsive.builder.OptionsBuilder;

public class PieChartExample extends ApexChartsBuilder {

    public PieChartExample(String nombre[],Long folios[]) {
        Toolbar toolbar = new Toolbar();
        toolbar.setShow(true);
        Export export = new Export();

        toolbar.setExport(export);

        Double[] doubles = new Double[folios.length];

        for(int pos = 0; pos < folios.length ; pos++){
            doubles[pos] = Double.parseDouble(Long.toString(folios[pos]));
        }

        super.withLabels(nombre);
        super.withSeries(doubles);

        withChart(ChartBuilder.get().withType(Type.PIE).withToolbar(toolbar).build())
                //.withLabels("Team A", "Team B", "Team C", "Team D", "Team E")
                .withLegend(LegendBuilder.get()
                        .withPosition(Position.RIGHT)
                        .build())
                //.withSeries(44.0, 55.0, 13.0, 43.0, 22.0)
                .withResponsive(ResponsiveBuilder.get()
                        .withBreakpoint(480.0)
                        .withOptions(OptionsBuilder.get()
                                .withLegend(LegendBuilder.get()
                                        .withPosition(Position.BOTTOM)
                                        .build())
                                .build())
                        .build());
    }
}
