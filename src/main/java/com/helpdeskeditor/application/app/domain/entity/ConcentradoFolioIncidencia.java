package com.helpdeskeditor.application.app.domain.entity;

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
public class ConcentradoFolioIncidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Folio")
    private Integer id;

    @Column(name = "UsuarioReporta")
    private String usuarioReporta;
}
