package com.helpdeskeditor.application.app.data.DAO;

import com.helpdeskeditor.application.app.data.entity.EstatusEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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

    Date fecha;

    public EstatusDAO(Integer id, Integer folio, Integer idEstatus, String nombre, String anotacion, Integer idUsuario, String nombrePropio, Date fecha) {
        this.id = id;
        this.folio = folio;
        this.idEstatus = idEstatus;
        this.nombre = nombre;
        this.anotacion = anotacion;
        this.idUsuario = idUsuario;
        this.nombrePropio = nombrePropio;
        this.fecha = fecha;
    }
}
