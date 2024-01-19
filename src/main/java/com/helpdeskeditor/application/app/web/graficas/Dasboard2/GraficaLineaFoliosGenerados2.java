package com.helpdeskeditor.application.app.web.graficas.Dasboard2;

import com.helpdeskeditor.application.app.web.charts.LineMultiYAxesChartExample;
import com.helpdeskeditor.application.util.ApexCharts.ApexCharts;
import com.helpdeskeditor.application.util.ApexCharts.config.TitleSubtitle;
import com.helpdeskeditor.application.util.ApexCharts.helper.Series;
import com.vaadin.flow.component.formlayout.FormLayout;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class GraficaLineaFoliosGenerados2 extends FormLayout{

    public GraficaLineaFoliosGenerados2(String nombreGrafica, String nombreSerie,String etiquetas[],Integer data[]) {
        this.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 3));

        TitleSubtitle titleSubtitleFoliosUnidades = new TitleSubtitle();
        titleSubtitleFoliosUnidades.setText(nombreGrafica);

        Series series = new Series<>();
        series.setName(nombreSerie);
        series.setData(data);

        List<String> categorias = Arrays.asList(etiquetas);

        ApexCharts pchart = new LineMultiYAxesChartExample(series,categorias).withTitle(titleSubtitleFoliosUnidades).build();
        pchart.setHeight("400px");

        this.setColspan(pchart, 3);

        this.add(pchart);
    }
}
