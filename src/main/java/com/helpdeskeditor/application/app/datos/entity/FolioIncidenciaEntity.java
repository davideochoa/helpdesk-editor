package com.helpdeskeditor.application.app.datos.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "concentrado_folios_incidencias")
public class FolioIncidenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Folio")
    private Integer id;

    @Column(name = "UsuarioReporta")
    private String usuarioReporta;

    @Column(name = "Marca")
    private String marca;
    @Column(name = "Modelo")
    private String modelo;
}
