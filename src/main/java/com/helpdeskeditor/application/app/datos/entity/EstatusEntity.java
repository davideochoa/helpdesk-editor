package com.helpdeskeditor.application.app.datos.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "estatus")
public class EstatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    Integer id;

    @Column(name = "Folio")
    Integer folio;

    @Column(name = "IdEstatus")
    Integer idEstatus;

    @Column(name = "Anotacion")
    String anotacion;

    @Column(name = "IdUsuario")
    Integer idUsuario;

    @Column(name = "Fecha")
    Date fecha;
}
