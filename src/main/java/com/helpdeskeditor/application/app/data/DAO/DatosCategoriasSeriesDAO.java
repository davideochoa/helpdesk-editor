package com.helpdeskeditor.application.app.data.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DatosCategoriasSeriesDAO {
    int year;
    String categoria;
    long data;
    public String getNombre(){
        return Integer.toString(year) + " " + categoria;
    }
}
