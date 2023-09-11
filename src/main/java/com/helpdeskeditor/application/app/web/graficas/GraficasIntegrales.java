package com.helpdeskeditor.application.app.web.graficas;

import com.helpdeskeditor.application.app.data.DAO.FoliosxUnidadDTO;
import com.helpdeskeditor.application.app.data.DAO.IncidenciaXUnidad;
import com.helpdeskeditor.application.app.service.FolioService;
import com.helpdeskeditor.application.util.ApexCharts.ApexCharts;
import com.vaadin.flow.component.formlayout.FormLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GraficasIntegrales extends FormLayout {

    public GraficasIntegrales(FolioService folioService, Date LDfechaInicio, Date LDfechaFin) {
        this.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 3));

        List<IncidenciaXUnidad> incidenciaXUnidadList = folioService.getFoliosIncidenciaXUnidad(LDfechaInicio, LDfechaFin);

        List<FoliosxUnidadDTO> foliosxUnidadDTOList = new ArrayList<>();

        String nombreUnidad = incidenciaXUnidadList.get(0).getUnidad();
        for(IncidenciaXUnidad incidenciaXUnidad : incidenciaXUnidadList){
            FoliosxUnidadDTO foliosxUnidadDTO = new FoliosxUnidadDTO();
            if(nombreUnidad.equals(incidenciaXUnidad.getUnidad())){
                foliosxUnidadDTO.setNombre(incidenciaXUnidad.getBien());
                foliosxUnidadDTO.setCantidadFolios(incidenciaXUnidad.getCantidadFolios());

                foliosxUnidadDTOList.add(foliosxUnidadDTO);
            }
            else{
                String etiquetas[] = foliosxUnidadDTOList.stream().map(FoliosxUnidadDTO:: getNombre2).toArray(String[] :: new);
                Double valores[] = foliosxUnidadDTOList.stream().map(FoliosxUnidadDTO :: getCantidadFoliosDouble).toArray(Double[] :: new);

                ApexCharts apexCharts = GraficaPastelIncidenciasXUnidad.get(nombreUnidad,etiquetas,valores);
                apexCharts.setHeight("400px");

                this.setColspan(apexCharts, 3);

                this.add(apexCharts);

                nombreUnidad = incidenciaXUnidad.getUnidad();
            }
        }


    }
}
