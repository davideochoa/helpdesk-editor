package com.helpdeskeditor.application.views;

import com.helpdeskeditor.application.data.modelo.*;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Folioss")
@Route(value = "folioss", layout = MainLayout.class)
@RolesAllowed("USER")
public class FoliossView extends FormLayout {
    VerticalLayout VL_Unidad = new VerticalLayout();
    VerticalLayout VL_Objeto = new VerticalLayout();

    IntegerField IF_Folio = new IntegerField();
    ComboBox<Unidad> CB_Unidad = new ComboBox<Unidad>("Unidad");
    ComboBox<Area> CB_Area = new ComboBox<Area>("Area");
    ComboBox<UsuarioReporta> CB_UsuarioReporta = new ComboBox<UsuarioReporta>("Usuario Reporta");

    TextField TF_Telefono = new TextField();

    TextField TF_ReferenciaDocumental = new TextField();


    ComboBox<Incidencia> CB_Incidencia = new ComboBox<Incidencia>("Incidencia");

    ComboBox<Bien> CB_Bien = new ComboBox<Bien>("Bien");

    ComboBox<Marca> CB_Marca = new ComboBox<Marca>("Marca");

    ComboBox<Modelo> CB_Modelo = new ComboBox<Modelo>("Modelo");

    TextField TF_NumeroSerie = new TextField("Numero Serie");

    TextField TF_NumeroInventario = new TextField("Numero Inventario");

    public FoliossView() {
        layoutUnidad();
        layoutObjeto();

        this.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 2));


        this.add(VL_Unidad,VL_Objeto);
    }

    private void layoutObjeto(){
        CB_Incidencia.setItemLabelGenerator(Incidencia::getNombre);

        VL_Objeto.add(CB_Incidencia);
        VL_Objeto.add(CB_Bien);
        VL_Objeto.add(CB_Marca);
        VL_Objeto.add(CB_Modelo);
        VL_Objeto.add(TF_NumeroSerie);
        VL_Objeto.add(TF_NumeroInventario);
    }

    private void layoutUnidad(){
        IF_Folio.setLabel("Numero Folio");

        //TF_Telefono.setAllowedCharPattern("^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$");
        TF_Telefono.setHelperText("Formato:+(123)456-7890");
        TF_Telefono.setLabel("Numero Telefonico");
        TF_Telefono.setWidth("240px");

        CB_Unidad.setItemLabelGenerator(Unidad::getNombre);
        CB_Area.setItemLabelGenerator(Area::getNombre);
        CB_UsuarioReporta.setItemLabelGenerator(UsuarioReporta::getNombre);

        TF_ReferenciaDocumental.setLabel("Referencia Documental");
        TF_ReferenciaDocumental.setHelperText("Numero de oficio/orden/folio de seguimiento");

        VL_Unidad.add(IF_Folio);
        VL_Unidad.add(CB_Unidad);
        VL_Unidad.add(CB_Area);
        VL_Unidad.add(CB_UsuarioReporta);
        VL_Unidad.add(TF_Telefono);
        VL_Unidad.add(TF_ReferenciaDocumental);
    }

}
