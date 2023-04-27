package com.helpdeskeditor.application.app.data.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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

    public Integer diasActivo(){
        LocalDate fechaInicial = getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaActual = LocalDate.now();

        Integer noOfDaysBetween = (Integer) Math.toIntExact(ChronoUnit.DAYS.between(fechaInicial, fechaActual));
        return noOfDaysBetween;
    }

}
