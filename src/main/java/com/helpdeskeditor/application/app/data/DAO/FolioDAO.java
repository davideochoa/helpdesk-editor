package com.helpdeskeditor.application.app.data.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class FolioDAO {
    Integer id;
    Date fecha;
    String unidad;
    String usuarioReporta;
    String marca;
    String modelo;
    String numeroSerie;
    String numeroInventario;
    String estado;

}
