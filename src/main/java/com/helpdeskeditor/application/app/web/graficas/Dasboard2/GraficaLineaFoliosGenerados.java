package com.helpdeskeditor.application.app.web.graficas.Dasboard2;

import com.helpdeskeditor.application.app.data.DAO.DatosCategoriasSeriesDAO;
import com.helpdeskeditor.application.app.data.DAO.FolioxIncidenciaDTO;
import com.helpdeskeditor.application.app.service.FolioService;
import com.helpdeskeditor.application.app.web.charts.LineMultiYAxesChartExample;
import com.helpdeskeditor.application.app.web.charts.PieChartExample;
import com.helpdeskeditor.application.util.ApexCharts.ApexCharts;
import com.helpdeskeditor.application.util.ApexCharts.config.TitleSubtitle;
import com.helpdeskeditor.application.util.ApexCharts.helper.Series;
import com.vaadin.flow.component.formlayout.FormLayout;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
public class GraficaLineaFoliosGenerados extends FormLayout{

    public GraficaLineaFoliosGenerados(FolioService folioService, Date fechaInicio, Date fechaFin) {
        this.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 3));

        List<DatosCategoriasSeriesDAO> datosCategoriasSeriesDAOList  = folioService.getCantidadFoliosGeneradosXMes(fechaInicio, fechaFin);

        String nombre[] = datosCategoriasSeriesDAOList.stream().map(DatosCategoriasSeriesDAO:: getNombre).toArray(String[] :: new);
        Object data[] = datosCategoriasSeriesDAOList.stream().map(DatosCategoriasSeriesDAO :: getData).toArray(Object[] :: new);

        TitleSubtitle titleSubtitleFoliosUnidades = new TitleSubtitle();
        titleSubtitleFoliosUnidades.setText("Folios Generados");

        Series series = new Series<>();
        series.setName("Folios Generados");
        series.setData(data);

        List<String> categorias = Arrays.asList(nombre);

        ApexCharts pchart = new LineMultiYAxesChartExample(series,categorias).withTitle(titleSubtitleFoliosUnidades).build();
        pchart.setHeight("400px");

        this.setColspan(pchart, 3);

        this.add(pchart);
    }
}
