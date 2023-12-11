package com.helpdeskeditor.application.app.data.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "catalogo_usuarios_soporte")
public class UsuarioSoporteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "NombrePropio")
    private String nombrePropio;

    @Column(name = "Correo")
    private String correo;

    @Column(name = "NombreUsuario")
    private String nombreUsuario;

    @Column(name = "Password")
    private String password;

    @Column(name = "EsAdministrador")
    private Boolean esAdministrador;

    @Column(name = "EsReseteadoPassword")
    private Boolean esReseteadoPassword;

    @Column(name = "rol")
    private String rol;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "firma")
    private byte[] firma;

    @Override
    public String toString(){
        return getId()+" : "+getNombreUsuario()+" : "+getNombrePropio()+" : "+getRol()+" : "+getEsReseteadoPassword();
    }
}
