package com.helpdeskeditor.application.app.web.graficas;

import com.helpdeskeditor.application.app.data.DAO.FoliosxUnidadDTO;
import com.helpdeskeditor.application.app.data.DAO.IncidenciaXUnidad;
import com.helpdeskeditor.application.app.service.FolioService;
import com.helpdeskeditor.application.util.ApexCharts.ApexCharts;
import com.vaadin.flow.component.formlayout.FormLayout;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class GraficasIntegrales extends FormLayout {

    public GraficasIntegrales(FolioService folioService, Date LDfechaInicio, Date LDfechaFin) {
        this.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 3));

        List<IncidenciaXUnidad> incidenciaXUnidadList = folioService.getFoliosIncidenciaXUnidad(LDfechaInicio, LDfechaFin);

        List<FoliosxUnidadDTO> foliosxUnidadDTOList = new ArrayList<>();

        String nombreUnidad = null;
        for(IncidenciaXUnidad incidenciaXUnidad : incidenciaXUnidadList){
            if(nombreUnidad == null)
                nombreUnidad = incidenciaXUnidad.getUnidad();

            if(nombreUnidad.equals(incidenciaXUnidad.getUnidad())){
                FoliosxUnidadDTO foliosxUnidadDTO = new FoliosxUnidadDTO();
                foliosxUnidadDTO.setNombre(incidenciaXUnidad.getBien());
                foliosxUnidadDTO.setCantidadFolios(incidenciaXUnidad.getCantidadFolios());

                foliosxUnidadDTOList.add(foliosxUnidadDTO);

                log.info("nombreUnidad:"+nombreUnidad+" : "+incidenciaXUnidad.getUnidad()+" : "+foliosxUnidadDTO.getNombre()+" : "+foliosxUnidadDTO.getCantidadFolios());
            }
            else{
                String etiquetas[] = foliosxUnidadDTOList.stream().map(FoliosxUnidadDTO:: getNombre2).toArray(String[] :: new);
                Double valores[] = foliosxUnidadDTOList.stream().map(FoliosxUnidadDTO :: getCantidadFoliosDouble).toArray(Double[] :: new);

                log.info(nombreUnidad+" : " +etiquetas + " : "+ valores);

                ApexCharts apexCharts = GraficaPastelIncidenciasXUnidad.get(nombreUnidad,etiquetas,valores);
                apexCharts.setHeight("400px");

                this.setColspan(apexCharts, 3);

                this.add(apexCharts);

                nombreUnidad = incidenciaXUnidad.getUnidad();
            }
        }


    }
}
