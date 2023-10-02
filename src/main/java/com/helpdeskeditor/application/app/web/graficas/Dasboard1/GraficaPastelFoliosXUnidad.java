package com.helpdeskeditor.application.app.web.graficas.Dasboard1;

import com.helpdeskeditor.application.app.data.DAO.FoliosxUnidadDTO;
import com.helpdeskeditor.application.app.service.FolioService;
import com.helpdeskeditor.application.app.web.charts.PieChartExample;
import com.helpdeskeditor.application.util.ApexCharts.ApexCharts;
import com.helpdeskeditor.application.util.ApexCharts.config.TitleSubtitle;
import com.vaadin.flow.component.formlayout.FormLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class GraficaPastelFoliosXUnidad extends FormLayout{

    public GraficaPastelFoliosXUnidad(FolioService folioService, Date fechaInicio, Date fechaFin) {
        this.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 3));

        List<FoliosxUnidadDTO> foliosXUnidadDTOList = new ArrayList<FoliosxUnidadDTO>();

        foliosXUnidadDTOList = folioService.getFoliosXUnidad(fechaInicio, fechaFin);

        this.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 3));

        String nombre[] = foliosXUnidadDTOList.stream().map(FoliosxUnidadDTO:: getNombre2).toArray(String[] :: new);
        Long folios[] = foliosXUnidadDTOList.stream().map(FoliosxUnidadDTO :: getCantidadFolios).toArray(Long[] :: new);

        Double[] doubles = new Double[folios.length];

        for(int pos = 0; pos < folios.length ; pos++){
            doubles[pos] = Double.parseDouble(Long.toString(folios[pos]));
        }

        int sumaTotalFolios = 0;
        for(FoliosxUnidadDTO folio : foliosXUnidadDTOList)
            sumaTotalFolios = sumaTotalFolios + folio.getCantidadFolios().intValue();

        TitleSubtitle titleSubtitleFoliosUnidades = new TitleSubtitle();
        titleSubtitleFoliosUnidades.setText("Folios por Unidades. Total: "+sumaTotalFolios+" folios generados");

        ApexCharts pchart = new PieChartExample(nombre,doubles).withTitle(titleSubtitleFoliosUnidades).build();
        pchart.setHeight("400px");

        this.setColspan(pchart, 3);

        this.add(pchart);
    }
}
