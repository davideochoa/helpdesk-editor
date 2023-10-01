package com.helpdeskeditor.application.app.web.graficas.Dasboard1;

import com.helpdeskeditor.application.app.data.DAO.FolioxIncidenciaDTO;
import com.helpdeskeditor.application.app.service.FolioService;
import com.helpdeskeditor.application.app.web.charts.PieChartExample;
import com.helpdeskeditor.application.util.ApexCharts.ApexCharts;
import com.helpdeskeditor.application.util.ApexCharts.config.TitleSubtitle;
import com.vaadin.flow.component.formlayout.FormLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class GraficaPastelFoliosXIncidecia extends FormLayout{

    public GraficaPastelFoliosXIncidecia(FolioService folioService,Date fechaInicio, Date fechaFin) {
        this.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 3));

        List<FolioxIncidenciaDTO> folioxIncidenciaDTOList = new ArrayList<>();

        folioxIncidenciaDTOList = folioService.getFoliosXIncidencia(fechaInicio, fechaFin);

        log.info("fechaInicio: "+fechaInicio+" : fechaFin: "+fechaFin);
        log.info("folioxIncidenciaDTOList.size(): "+folioxIncidenciaDTOList.size());

        this.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 3));

        String nombre[] = folioxIncidenciaDTOList.stream().map(FolioxIncidenciaDTO:: getNombre2).toArray(String[] :: new);
        Long folios[] = folioxIncidenciaDTOList.stream().map(FolioxIncidenciaDTO :: getCantidadFolios).toArray(Long[] :: new);

        Double[] doubles = new Double[folios.length];

        for(int pos = 0; pos < folios.length ; pos++){
            doubles[pos] = Double.parseDouble(Long.toString(folios[pos]));
        }

        int sumaTotalFolios = 0;
        for(FolioxIncidenciaDTO folio : folioxIncidenciaDTOList)
            sumaTotalFolios = sumaTotalFolios + folio.getCantidadFolios().intValue();

        TitleSubtitle titleSubtitleFoliosUnidades = new TitleSubtitle();
        titleSubtitleFoliosUnidades.setText("Folios por Unidades. Total: "+sumaTotalFolios+" folios generados");

        ApexCharts pchart = new PieChartExample(nombre,doubles).withTitle(titleSubtitleFoliosUnidades).build();
        pchart.setHeight("400px");

        this.setColspan(pchart, 3);

        this.add(pchart);
    }
}
