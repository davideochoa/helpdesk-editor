package com.helpdeskeditor.application.app.data.DAO.GraficaLineal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class DatosParaGraficaLineal {
    public Integer idUnidad;
    public String nombreUnidad;
    public Integer anno;
    public Integer mesNumero;
    public String mesNombre;
    public Integer valor;
}
