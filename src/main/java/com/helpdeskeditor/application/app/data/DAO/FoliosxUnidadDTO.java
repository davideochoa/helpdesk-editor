package com.helpdeskeditor.application.app.data.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FoliosxUnidadDTO {
    private String nombre;
    private Long cantidadFolios;
    public String getNombre2(){
        return getNombre()+" ("+getCantidadFolios()+")";
    }
}
