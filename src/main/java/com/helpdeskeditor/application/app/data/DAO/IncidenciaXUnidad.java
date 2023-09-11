package com.helpdeskeditor.application.app.data.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class IncidenciaXUnidad{
    String unidad;
    String bien;
    Long cantidadFolios;

    public String getNombre2(){
        return getUnidad()+" ("+getCantidadFolios()+")";
    }
    public Double getCantidadFoliosDouble(){
        return Double.parseDouble(Long.toString(getCantidadFolios()));
    }
}
