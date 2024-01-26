package com.helpdeskeditor.application.app.data.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class SolicitudDAO {

    private Integer id;

    private String area;

    private String tipoBien;

    private String marca;

    private String modelo;

    private String numeroSerie;

    private String numeroInventario;

    private String motivo;

    private Date fecha;

    private String status;

}
