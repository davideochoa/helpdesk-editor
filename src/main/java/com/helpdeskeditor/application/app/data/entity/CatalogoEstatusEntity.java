package com.helpdeskeditor.application.app.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "catalogo_estatus")
public class CatalogoEstatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
/*
    @OneToOne(mappedBy="catalogoEstatusEntity")
    private EstatusEntity estatusEntity;
*/
    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Abrir")
    private Boolean abrir;

    @Column(name = "Cerrar")
    private Boolean cerrar;

    @Column(name = "Reasignar")
    private Boolean reasignar;

    @Column(name = "DiagnostinoInicial")
    private Boolean diagnostinoInicial;

    @Column(name = "DiagnostinoFinal")
    private Boolean diagnostinoFinal;
}
