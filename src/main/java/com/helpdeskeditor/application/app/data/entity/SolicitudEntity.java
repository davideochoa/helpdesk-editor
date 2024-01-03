package com.helpdeskeditor.application.app.data.entity;

import com.helpdeskeditor.application.util.anotaciones.mayusculas.ToUpperCase;
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
@Table(name = "solicitud")
public class SolicitudEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "IdUnidad")
    private Integer idUnidad;

    @Column(name = "IdArea")
    private Integer idArea;

    @Column(name = "IdTipoIncidencia")
    private Integer idTipoIncidencia;

    @Column(name = "IdTipoBien")
    private Integer idTipoBien;

    @Column(name = "Marca")
    private String marca;

    @Column(name = "Modelo")
    private String modelo;

    @Column(name = "NumeroSerie")
    private String NumeroSerie;

    @Column(name = "NumeroInventario")
    private String NumeroInventario;

    @Column(name = "Motivo")
    private String motivo;

    public void setMarca(String marca) {
        this.marca = marca.toUpperCase();
    }

    public void setModelo(String modelo) {
        this.modelo = modelo.toUpperCase();
    }

    public void setNumeroSerie(String numeroSerie) {
        NumeroSerie = numeroSerie.toUpperCase();
    }

    public void setNumeroInventario(String numeroInventario) {
        NumeroInventario = numeroInventario.toUpperCase();
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo.toUpperCase();
    }
}
