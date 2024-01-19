package com.helpdeskeditor.application.app.web.graficas.Dasboard1;

import com.helpdeskeditor.application.app.data.DAO.FoliosxUnidadDTO;
import com.helpdeskeditor.application.app.data.DAO.IncidenciaXUnidad;
import com.helpdeskeditor.application.app.service.FoliosService;
import com.helpdeskeditor.application.util.ApexCharts.ApexCharts;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class GraficasIntegralesXBien extends FormLayout {

    public GraficasIntegralesXBien(FoliosService foliosService, Date LDfechaInicio, Date LDfechaFin) {
        this.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 3));

        this.add(new H2("INCIDENCIAS POR BIEN"));

        List<IncidenciaXUnidad> incidenciaXUnidadList = foliosService.getFoliosIncidenciaXBien(LDfechaInicio, LDfechaFin);

        List<FoliosxUnidadDTO> foliosxUnidadDTOList = new ArrayList<>();

        String unidadActual = null;
        String unidadNueva;
        for(IncidenciaXUnidad incidenciaXUnidad : incidenciaXUnidadList){

            if(unidadActual == null) {
                unidadActual = incidenciaXUnidad.getUnidad();
            }

            unidadNueva = incidenciaXUnidad.getUnidad();

            if(unidadActual.equals(unidadNueva)){
                FoliosxUnidadDTO foliosxUnidadDTO = new FoliosxUnidadDTO();
                foliosxUnidadDTO.setNombre(incidenciaXUnidad.getBien());
                foliosxUnidadDTO.setCantidadFolios(incidenciaXUnidad.getCantidadFolios());

                foliosxUnidadDTOList.add(foliosxUnidadDTO);
            }
            else{
                String etiquetas[] = foliosxUnidadDTOList.stream().map(FoliosxUnidadDTO:: getNombre2).toArray(String[] :: new);
                Double valores[] = foliosxUnidadDTOList.stream().map(FoliosxUnidadDTO :: getCantidadFoliosDouble).toArray(Double[] :: new);

                ApexCharts apexCharts = GraficaPastelIncidenciasXUnidad.get(unidadActual,etiquetas,valores);
                apexCharts.setHeight("400px");

                this.setColspan(apexCharts, 3);

                this.add(apexCharts);
                foliosxUnidadDTOList = new ArrayList<>();
                FoliosxUnidadDTO foliosxUnidadDTO = new FoliosxUnidadDTO();
                foliosxUnidadDTO.setNombre(incidenciaXUnidad.getBien());
                foliosxUnidadDTO.setCantidadFolios(incidenciaXUnidad.getCantidadFolios());

                foliosxUnidadDTOList.add(foliosxUnidadDTO);

                unidadActual = unidadNueva;

            }
        }
        String etiquetas[] = foliosxUnidadDTOList.stream().map(FoliosxUnidadDTO:: getNombre2).toArray(String[] :: new);
        Double valores[] = foliosxUnidadDTOList.stream().map(FoliosxUnidadDTO :: getCantidadFoliosDouble).toArray(Double[] :: new);

        ApexCharts apexCharts = GraficaPastelIncidenciasXUnidad.get(unidadActual,etiquetas,valores);
        apexCharts.setHeight("400px");

        this.setColspan(apexCharts, 3);

        this.add(apexCharts);
    }
}
