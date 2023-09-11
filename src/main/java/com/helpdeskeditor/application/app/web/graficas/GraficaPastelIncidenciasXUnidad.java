package com.helpdeskeditor.application.app.web.graficas;

import com.helpdeskeditor.application.app.data.DAO.FoliosxUnidadDTO;
import com.helpdeskeditor.application.app.service.FolioService;
import com.helpdeskeditor.application.app.web.charts.PieChartExample;
import com.helpdeskeditor.application.util.ApexCharts.ApexCharts;
import com.helpdeskeditor.application.util.ApexCharts.config.TitleSubtitle;
import com.vaadin.flow.component.formlayout.FormLayout;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
