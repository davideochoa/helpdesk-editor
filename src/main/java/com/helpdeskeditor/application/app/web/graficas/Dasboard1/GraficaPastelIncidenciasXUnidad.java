package com.helpdeskeditor.application.app.web.graficas.Dasboard1;

import com.helpdeskeditor.application.app.web.charts.PieChartExample;
import com.helpdeskeditor.application.util.ApexCharts.ApexCharts;
import com.helpdeskeditor.application.util.ApexCharts.config.TitleSubtitle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GraficaPastelIncidenciasXUnidad {

    public GraficaPastelIncidenciasXUnidad() {}

    public static ApexCharts get(String titulo,String nombre[], Double datos[]){
        TitleSubtitle titleSubtitleFoliosUnidades = new TitleSubtitle();
        titleSubtitleFoliosUnidades.setText(titulo);

        ApexCharts pchart = new PieChartExample(nombre,datos).withTitle(titleSubtitleFoliosUnidades).build();
        pchart.setHeight("400px");

        return pchart;
    }
}
