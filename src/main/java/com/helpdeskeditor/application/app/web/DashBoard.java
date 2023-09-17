package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.service.FolioService;
import com.helpdeskeditor.application.app.web.graficas.GraficaPastelFoliosXIncidecia;
import com.helpdeskeditor.application.app.web.graficas.GraficaPastelFoliosXUnidad;
import com.helpdeskeditor.application.app.web.graficas.GraficasIntegralesXBien;
import com.helpdeskeditor.application.app.web.graficas.GraficasIntegralesXUnidad;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@PageTitle("DashBoard")
@Route(value = "dashboard", layout = MainLayout.class)
@AnonymousAllowed
//@RolesAllowed("USER")
public class DashBoard extends VerticalLayout {
    DatePicker DP_fechaInicio = new DatePicker("Fecha Inicio");
    DatePicker DP_fechaFin = new DatePicker("Fecha Fin");
    Button B_GenerarGrafico = new Button("Generar Grafico");

    public DashBoard(FolioService folioService) {
        FormLayout flFechas = new FormLayout();
        flFechas.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 3));

        FormLayout flgraficas = new FormLayout();
        flgraficas.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 3));

        flFechas.add(DP_fechaInicio, DP_fechaFin,B_GenerarGrafico);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30);

        DP_fechaInicio.setValue( new Date(calendar.getTimeInMillis()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        DP_fechaFin.setValue(LocalDate.now(ZoneId.systemDefault()));

        Date inicio = Date.from(DP_fechaInicio.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date fin = Date.from(DP_fechaFin.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        final FormLayout[] dash = new FormLayout[4];
        dash[0] = new GraficaPastelFoliosXUnidad(folioService, inicio, fin);
        dash[1] = new GraficaPastelFoliosXIncidecia(folioService, inicio, fin);
        dash[2] = new GraficasIntegralesXUnidad(folioService, inicio, fin);
        dash[3] = new GraficasIntegralesXBien(folioService, inicio, fin);

        flgraficas.add(dash[0]);
        flgraficas.add(dash[1]);
        flgraficas.add(dash[2]);
        flgraficas.add(dash[3]);

        flgraficas.setColspan(dash[0], 3);
        flgraficas.setColspan(dash[1], 3);
        flgraficas.setColspan(dash[2], 3);
        flgraficas.setColspan(dash[3], 3);

        B_GenerarGrafico.addClickListener(clickEvent -> {
            flgraficas.removeAll();

            Date inicio2 = Date.from(DP_fechaInicio.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            Date fin2 = Date.from(DP_fechaFin.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

            dash[0] = new GraficaPastelFoliosXUnidad(folioService, inicio2, fin2);
            dash[1] = new GraficaPastelFoliosXIncidecia(folioService, inicio2, fin2);
            dash[2] = new GraficasIntegralesXUnidad(folioService, inicio, fin);
            dash[3] = new GraficasIntegralesXBien(folioService, inicio, fin);

            flgraficas.setColspan(dash[0], 3);
            flgraficas.setColspan(dash[1], 3);
            flgraficas.setColspan(dash[2], 3);
            flgraficas.setColspan(dash[3], 3);

            flgraficas.add(dash[0]);
            flgraficas.add(dash[1]);
            flgraficas.add(dash[2]);
            flgraficas.add(dash[3]);
        });

        add(flFechas);

        add(flgraficas);

    }

}
