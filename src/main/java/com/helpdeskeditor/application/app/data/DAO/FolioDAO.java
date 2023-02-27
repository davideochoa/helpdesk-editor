package com.helpdeskeditor.application.app.data.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FolioDAO {
    String folio;
    String Unidad;
    String  Area;
    String UsuarioReporta;
    String Incidencia;
    String Bien;
    String Marca;
    String Modelo;
    String NumeroSerie;
    String NumeroInventario;
    String MotivoReporte;
    String SoporteAsignado;
    String Estado;
    String Estatus;
    String Fecha;
}
