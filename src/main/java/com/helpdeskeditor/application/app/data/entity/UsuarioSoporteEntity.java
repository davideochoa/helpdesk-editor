package com.helpdeskeditor.application.app.data.entity;

import com.helpdeskeditor.application.app.web.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
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

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
