package com.helpdeskeditor.application.app.data.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoliosxUnidadDTO {
    private String nombre;
    private Long cantidadFolios;
    public String getNombre2(){
        return getNombre()+" ("+getCantidadFolios()+")";
    }
    public Double getCantidadFoliosDouble(){
        return Double.parseDouble(Long.toString(getCantidadFolios()));
    }
}
