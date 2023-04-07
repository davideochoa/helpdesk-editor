package com.helpdeskeditor.application.app.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "estatus")
public class EstatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    Integer id;

    @Column(name = "Folio")
    Integer folio;

    @Column(name = "IdEstatus")
    Integer idEstatus;
/*
    @OneToOne
    CatalogoEstatusEntity catalogoEstatusEntity;*/

    @Column(name = "Anotacion")
    String anotacion;

    @Column(name = "IdUsuario")
    Integer idUsuario;

    //@Column(name = "Fecha", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    //Date fecha;

    @Column(name = "Fecha")
    @CreationTimestamp
    LocalDate fecha;

    @Override
    public String toString(){
        return "getId:"+getId()+
                ":getFolio:"+getFolio()+
                ":getIdEstatus:"+getIdEstatus()+
                ":getIdUsuario:"+getIdUsuario()+
                ":getFecha:"+getFecha()+
                ":getAnotacion:"+getAnotacion();
    }
}
