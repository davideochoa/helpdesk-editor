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
import java.util.Date;

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

    @Column(name = "UsuarioReporta")
    private String usuarioReporta;

    @Column(name = "TelefonoContacto")
    private String telefonoContacto;

    @Column(name = "IdTipoIncidencia")
    private Integer idTipoIncidencia;

    @Column(name = "IdTipoBien")
    private Integer idTipoBien;

    @Column(name = "Marca")
    private String marca;

    @Column(name = "Modelo")
    private String modelo;

    @Column(name = "NumeroSerie")
    private String numeroSerie;

    @Column(name = "NumeroInventario")
    private String numeroInventario;

    @Column(name = "Motivo")
    private String motivo;

    @Column(name = "Fecha")
    private Date fecha = new Date();

    public String getUsuarioReporta() {
        if(usuarioReporta == null)
            usuarioReporta = "NO ESPECIFICADO";

        if(usuarioReporta.length() == 0)
            usuarioReporta = "NO ESPECIFICADO";

        this.usuarioReporta = usuarioReporta.toUpperCase();

        return usuarioReporta;
    }

    public String getTelefonoContacto() {
        if(telefonoContacto == null)
            telefonoContacto = "NO ESPECIFICADO";

        if(telefonoContacto.length() == 0)
            telefonoContacto = "NO ESPECIFICADO";

        this.telefonoContacto = telefonoContacto.toUpperCase();

        return telefonoContacto;
    }

    public void setMarca(String marca) {
        if(marca == null)
            marca = "NO ESPECIFICADO";

        if(marca.length() == 0)
            marca = "NO ESPECIFICADO";

        this.marca = marca.toUpperCase();
    }

    public void setModelo(String modelo) {
        if(modelo == null)
            modelo = "NO ESPECIFICADO";

        if(modelo.length() == 0)
            modelo = "NO ESPECIFICADO";

        this.modelo = modelo.toUpperCase();
    }

    public void setNumeroSerie(String numeroSerie) {
        if(numeroSerie == null)
            numeroSerie = "NO ESPECIFICADO";

        if(numeroSerie.length() == 0)
            numeroSerie = "NO ESPECIFICADO";

        this.numeroSerie = numeroSerie.toUpperCase();
    }

    public void setNumeroInventario(String numeroInventario) {
        if(numeroInventario == null)
            numeroInventario = "NO ESPECIFICADO";

        if(numeroInventario.length() == 0)
            numeroInventario = "NO ESPECIFICADO";

        this.numeroInventario = numeroInventario.toUpperCase();
    }

    public void setMotivo(String motivo) {
        if(motivo == null)
            motivo = "NO ESPECIFICADO";

        if(motivo.length() == 0)
            motivo = "NO ESPECIFICADO";

        this.motivo = motivo.toUpperCase();
    }
}
