package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.data.DAO.FoliosxUnidadDTO;
import com.helpdeskeditor.application.app.service.FolioService;
import com.helpdeskeditor.application.app.web.charts.PieChartExample;
import com.helpdeskeditor.application.util.ApexCharts.ApexCharts;
import com.helpdeskeditor.application.util.ApexCharts.config.TitleSubtitle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@PageTitle("DashBoard")
@Route(value = "dashboard", layout = MainLayout.class)
@AnonymousAllowed
//@RolesAllowed("USER")
public class DashBoard extends VerticalLayout {
    DatePicker fechaInicio = new DatePicker("Fecha Inicio");
    DatePicker fechaFin = new DatePicker("Fecha Fin");
    Button B_GenerarGrafico = new Button("Generar Grafico");
    List<FoliosxUnidadDTO> foliosXUnidadDTOList = new ArrayList<FoliosxUnidadDTO>();

    ApexCharts pchart = null;
    public DashBoard(FolioService folioService) {
        FormLayout dashBoard = new FormLayout();
        dashBoard.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 3));

        dashBoard.add(fechaInicio,fechaFin,B_GenerarGrafico);
        fechaInicio.setValue(LocalDate.now(ZoneId.systemDefault()));
        fechaFin.setValue(LocalDate.now(ZoneId.systemDefault()));


        foliosXUnidadDTOList = folioService.getFoliosXUnidad(Date.valueOf(fechaInicio.getValue()),Date.valueOf(fechaFin.getValue()));

        B_GenerarGrafico.addClickListener(clickEvent -> {
            foliosXUnidadDTOList = folioService.getFoliosXUnidad(Date.valueOf(fechaInicio.getValue()),Date.valueOf(fechaFin.getValue()));

            String nombre[] = foliosXUnidadDTOList.stream().map(FoliosxUnidadDTO :: getNombre2).toArray(String[] :: new);
            Long folios[] = foliosXUnidadDTOList.stream().map(FoliosxUnidadDTO :: getCantidadFolios).toArray(Long[] :: new);

            int sumaTotalFolios = 0;
            for(FoliosxUnidadDTO folio : foliosXUnidadDTOList) {
                log.info("getIdUnidad:" + folio.getNombre() + " getCantidadFolios:" + folio.getCantidadFolios());
                sumaTotalFolios = sumaTotalFolios + folio.getCantidadFolios().intValue();
            }

            TitleSubtitle titleSubtitleFoliosUnidades = new TitleSubtitle();
            titleSubtitleFoliosUnidades.setText("Folios por Unidades. Total: "+sumaTotalFolios+" folios generados");

            Double[] doubles = new Double[folios.length];

            for(int pos = 0; pos < folios.length ; pos++){
                doubles[pos] = Double.parseDouble(Long.toString(folios[pos]));
            }

            pchart.setLabels(nombre);
            pchart.setSeries(doubles);

            pchart.setTitle(titleSubtitleFoliosUnidades);

            pchart.render();

        });


        String nombre[] = foliosXUnidadDTOList.stream().map(FoliosxUnidadDTO :: getNombre2).toArray(String[] :: new);
        Long folios[] = foliosXUnidadDTOList.stream().map(FoliosxUnidadDTO :: getCantidadFolios).toArray(Long[] :: new);

        int sumaTotalFolios = 0;
        for(FoliosxUnidadDTO folio : foliosXUnidadDTOList) {
            log.info("getIdUnidad:" + folio.getNombre() + " getCantidadFolios:" + folio.getCantidadFolios());
            sumaTotalFolios = sumaTotalFolios + folio.getCantidadFolios().intValue();
        }

        TitleSubtitle titleSubtitleFoliosUnidades = new TitleSubtitle();
        titleSubtitleFoliosUnidades.setText("Folios por Unidades. Total: "+sumaTotalFolios+" folios generados");

        pchart = new PieChartExample(nombre,folios).withTitle(titleSubtitleFoliosUnidades).build();
        pchart.setHeight("400px");

        dashBoard.setColspan(pchart, 3);
        dashBoard.add(pchart);



        add(dashBoard);

    }

}
