package com.helpdeskeditor.application.app.data.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstatusDAO{
    Integer id;
    Integer folio;
    Integer idEstatus;
    String nombre;
    String anotacion;
    Integer idUsuario;
    String nombrePropio;
    //Date fecha;
    LocalDate fecha;
}
