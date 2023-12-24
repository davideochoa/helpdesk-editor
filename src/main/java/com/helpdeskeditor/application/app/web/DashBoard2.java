package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.data.DAO.GraficaLineal.DatosParaGraficaLineal;
import com.helpdeskeditor.application.app.data.DAO.GraficaLineal.DatosXYGrafica;
import com.helpdeskeditor.application.app.service.FolioService;
import com.helpdeskeditor.application.app.web.graficas.Dasboard2.GraficaLineaFoliosGenerados;
import com.helpdeskeditor.application.app.web.graficas.Dasboard2.GraficaLineaFoliosGenerados2;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@PageTitle("DashBoard2")
@Route(value = "dashboard2", layout = MainLayout.class)
//@AnonymousAllowed
@RolesAllowed("ADMIN")
public class DashBoard2 extends VerticalLayout {
    DatePicker DP_fechaInicio = new DatePicker("Fecha Inicio");
    DatePicker DP_fechaFin = new DatePicker("Fecha Fin");
    Button B_GenerarGrafico = new Button("Generar Grafico");
    FolioService folioService;
    FormLayout flgraficas = new FormLayout();
    public DashBoard2(FolioService folioService) {
        this.folioService = folioService;
        FormLayout flFechas = new FormLayout();
        flFechas.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 3));


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

        dash[0] = new GraficaLineaFoliosGenerados(folioService, inicio,fin);

        flgraficas.add(dash[0]);

        flgraficas.setColspan(dash[0], 3);

        B_GenerarGrafico.addClickListener(clickEvent -> {
            flgraficas.removeAll();

            Date inicio2 = Date.from(DP_fechaInicio.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            Date fin2 = Date.from(DP_fechaFin.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

            dash[0] = new GraficaLineaFoliosGenerados(folioService, inicio2,fin2);

            flgraficas.setColspan(dash[0], 3);

            flgraficas.add(dash[0]);

            graficosXunidad(inicio2,fin2);
        });

        graficosXunidad(inicio,fin);

        add(flFechas);
        add(flgraficas);

    }

    private void graficosXunidad(Date inicio, Date fin){
        List<DatosParaGraficaLineal> datosParaGraficaLinealList = folioService.getDatosGraficaLineal(inicio,fin);

        String unidadActual = datosParaGraficaLinealList.get(0).getNombreUnidad();
        List<DatosXYGrafica> datosXYGraficaList = new ArrayList<>();

        for(DatosParaGraficaLineal datosParaGraficaLineal : datosParaGraficaLinealList){

            if(unidadActual.equals(datosParaGraficaLineal.getNombreUnidad())){
                DatosXYGrafica datosXYGrafica = new DatosXYGrafica();
                datosXYGrafica.setEtiquetaX(datosParaGraficaLineal.getAnno()+" "+datosParaGraficaLineal.getMesNombre());
                datosXYGrafica.setValorY(datosParaGraficaLineal.getValor());

                datosXYGraficaList.add(datosXYGrafica);
            }
            else{
                String etiquetas[] = datosXYGraficaList.stream().map(DatosXYGrafica:: getEtiquetaX).toArray(String[] :: new);
                Integer valores[] = datosXYGraficaList.stream().map(DatosXYGrafica :: getValorY).toArray(Integer[] :: new);

                FormLayout grafico = new GraficaLineaFoliosGenerados2("Unidad "+datosParaGraficaLineal.getNombreUnidad(),"Folios",etiquetas,valores);
                //FormLayout grafico = new GraficaLineaFoliosGenerados(folioService, inicio,fin);

                flgraficas.setColspan(grafico, 3);
                flgraficas.add(grafico);

                unidadActual = datosParaGraficaLineal.getNombreUnidad();
                datosXYGraficaList = new ArrayList<>();

                DatosXYGrafica datosXYGrafica = new DatosXYGrafica();
                datosXYGrafica.setEtiquetaX(datosParaGraficaLineal.getAnno()+" "+datosParaGraficaLineal.getMesNombre());
                datosXYGrafica.setValorY(datosParaGraficaLineal.getValor());

                datosXYGraficaList.add(datosXYGrafica);
            }

        }
        String etiquetas[] = datosXYGraficaList.stream().map(DatosXYGrafica:: getEtiquetaX).toArray(String[] :: new);
        Integer valores[] = datosXYGraficaList.stream().map(DatosXYGrafica :: getValorY).toArray(Integer[] :: new);

        FormLayout grafico = new GraficaLineaFoliosGenerados2("Unidad "+unidadActual,"Folios",etiquetas,valores);
        //FormLayout grafico = new GraficaLineaFoliosGenerados(folioService, inicio,fin);

        flgraficas.setColspan(grafico, 3);
        flgraficas.add(grafico);
    }

}
