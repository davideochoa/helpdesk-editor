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
    Integer id;
    String unidad;
    String usuarioReporta;
    String marca;
    String modelo;
    String numeroSerie;
    String numeroInventario;
    String estado;

    /*

    String  Area;
    String Incidencia;
    String Bien;
     */
}
