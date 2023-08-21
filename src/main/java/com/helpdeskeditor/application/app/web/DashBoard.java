package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.data.DAO.FoliosxUnidadDTO;
import com.helpdeskeditor.application.app.service.FolioService;
import com.helpdeskeditor.application.app.web.charts.PieChartExample;
import com.helpdeskeditor.application.util.ApexCharts.ApexCharts;
import com.helpdeskeditor.application.util.ApexCharts.config.TitleSubtitle;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@PageTitle("DashBoard")
@Route(value = "dashboard", layout = MainLayout.class)
@AnonymousAllowed
//@RolesAllowed("USER")
public class DashBoard extends VerticalLayout {
    DatePicker fechaInicio = new DatePicker("Fecha Inicio");
    DatePicker fechaFin = new DatePicker("Fecha Fin");

    public DashBoard(FolioService folioService) {
        FormLayout dashBoard = new FormLayout();
        dashBoard.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 2));

        dashBoard.add(fechaInicio,fechaFin);


        List<FoliosxUnidadDTO> foliosXUnidadDTOList = folioService.getFoliosXUnidad();
        String nombre[] = foliosXUnidadDTOList.stream().map(FoliosxUnidadDTO :: getNombre2).toArray(String[] :: new);
        Long folios[] = foliosXUnidadDTOList.stream().map(FoliosxUnidadDTO :: getCantidadFolios).toArray(Long[] :: new);

        int sumaTotalFolios = 0;
        for(FoliosxUnidadDTO folio : foliosXUnidadDTOList) {
            log.info("getIdUnidad:" + folio.getNombre() + " getCantidadFolios:" + folio.getCantidadFolios());
            sumaTotalFolios = sumaTotalFolios + folio.getCantidadFolios().intValue();
        }



        TitleSubtitle titleSubtitleFoliosUnidades = new TitleSubtitle();
        titleSubtitleFoliosUnidades.setText("Folios por Unidades. Total: "+sumaTotalFolios+" folios generados");
        ApexCharts pchart = new PieChartExample(nombre,folios).withTitle(titleSubtitleFoliosUnidades).build();
        pchart.setHeight("400px");
        dashBoard.setColspan(pchart, 2);
        dashBoard.add(pchart);


        add(dashBoard);

        //setSpacing(false);

        //setSizeFull();
        //setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        //getStyle().set("text-align", "center");
    }

}
