package com.helpdeskeditor.application.app.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "concentrado_folios_incidencias")
public class FolioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Folio")
    private Integer id;

    @Column(name = "IdUnidad")
    private Integer idUnidad;

    @Column(name = "IdArea")
    private Integer idArea;

    @Column(name = "UsuarioReporta")
    private String usuarioReporta;

    @Column(name = "TelefonoContacto")
    private String telefonoContacto;

    @Column(name = "ReferenciaDocumental")
    private String referenciaDocumental;

    @Column(name = "IdTipoIncidencia")
    private Integer idTipoIncidencia;

    @Column(name = "IdBien")
    private Integer idBien;

    @Column(name = "Marca")
    private String marca;

    @Column(name = "Modelo")
    private String modelo;

    @Column(name = "NumeroSerie")
    private String numeroSerie;

    @Column(name = "NumeroInventario")
    private String numeroInventario;

    @Column(name = "MotivoReporte")
    private String motivoReporte;

    @Column(name = "IdPrioridad")
    private Integer idPrioridad;

    @Column(name = "IdUsuarioSoporteAsignado")
    private Integer idUsuarioSoporteAsignado;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "Activo")
    private Boolean activo;

    @Column(name = "IdTipoIncidenciaFinal")
    private Integer idTipoIncidenciaFinal;
/*
    public void setInitValues(){
        setId(0);
        setIdUnidad(0);
        setIdArea(0);
        setUsuarioReporta("NO ESPECIFICADO");
        setTelefonoContacto("NO ESPECIFICADO");
        setReferenciaDocumental("NO ESPECIFICADO");
        setIdTipoIncidencia(0);
        setIdBien(0);
        setMarca("NO ESPECIFICADO");
        setModelo("NO ESPECIFICADO");
        setNumeroSerie("NO ESPECIFICADO");
        setNumeroInventario("NO ESPECIFICADO");
        setMotivoReporte("");
        setIdPrioridad(0);
        setIdUsuarioSoporteAsignado(0);
        setFecha(new Date());
        setActivo(false);
        setIdTipoIncidenciaFinal(0);
    }*/
}
