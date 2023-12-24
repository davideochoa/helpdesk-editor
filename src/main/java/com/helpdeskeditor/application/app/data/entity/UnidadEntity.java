package com.helpdeskeditor.application.app.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "catalogo_unidades")
public class UnidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Cargo")
    private String cargoTitular;

    @Column(name = "InicialesTitular")
    private String inicialesTitular;

    @Column(name = "Nombretitular")
    private String nombreTitular;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "firmaTitular")
    private byte[] firmaTitular;
}
