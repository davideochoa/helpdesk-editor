package com.helpdeskeditor.application.app.data.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DatosParaGraficaLineal {
    String idUnidad;
    String nombre;
    Long anno;
    Long mesNumero;
    String mesNombre;
    Long valor;

    public DatosParaGraficaLineal(String idUnidad, String nombre, Long anno, Long mesNumero, String mesNombre, Long valor) {
        this.idUnidad = idUnidad;
        this.nombre = nombre;
        this.anno = anno;
        this.mesNumero = mesNumero;
        this.mesNombre = mesNombre;
        this.valor = valor;
    }
}
