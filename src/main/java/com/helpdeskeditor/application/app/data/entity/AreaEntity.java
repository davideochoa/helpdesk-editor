package com.helpdeskeditor.application.app.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "catalogo_areas")
public class AreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "IdUnidad")
    private Integer idUnidad;

    @Column(name = "Nombre")
    private String nombre;
}
