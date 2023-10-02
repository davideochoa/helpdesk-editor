package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.service.FolioService;
import com.helpdeskeditor.application.app.web.charts.LineMultiYAxesChartExample;
import com.helpdeskeditor.application.app.web.graficas.Dasboard1.GraficaPastelFoliosXUnidad;
import com.helpdeskeditor.application.app.web.graficas.Dasboard2.GraficaLineaFoliosGenerados;
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

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@PageTitle("DashBoard2")
@Route(value = "dashboard2", layout = MainLayout.class)
//@AnonymousAllowed
@RolesAllowed("ADMIN")
public class DashBoard2 extends VerticalLayout {
    DatePicker DP_fechaInicio = new DatePicker("Fecha Inicio");
    DatePicker DP_fechaFin = new DatePicker("Fecha Fin");
    Button B_GenerarGrafico = new Button("Generar Grafico");

    public DashBoard2(FolioService folioService) {
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
        calendar.add(Calendar.MONTH, -12);

        DP_fechaInicio.setValue( new Date(calendar.getTimeInMillis()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        DP_fechaFin.setValue(LocalDate.now(ZoneId.systemDefault()));

        Date inicio = Date.from(DP_fechaInicio.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date fin = Date.from(DP_fechaFin.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        final FormLayout[] dash = new FormLayout[4];
        dash[0] = new GraficaLineaFoliosGenerados(folioService, inicio, fin);

        flgraficas.add(dash[0]);

        flgraficas.setColspan(dash[0], 3);

        B_GenerarGrafico.addClickListener(clickEvent -> {
            flgraficas.removeAll();

            Date inicio2 = Date.from(DP_fechaInicio.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            Date fin2 = Date.from(DP_fechaFin.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

            dash[0] = new GraficaLineaFoliosGenerados(folioService, inicio2, fin2);

            flgraficas.setColspan(dash[0], 3);

            flgraficas.add(dash[0]);
        });

        add(flFechas);

        add(flgraficas);

    }

}
