package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.domain.entity.Unidad;
import com.helpdeskeditor.application.app.service.UnidadesService;
import com.helpdeskeditor.application.app.web.modelo.*;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@PageTitle("Folioss")
@Route(value = "folioss", layout = MainLayout.class)
@RolesAllowed("USER")
@Slf4j
public class FoliossView extends VerticalLayout {

    VerticalLayout VL_Unidad = new VerticalLayout();
    IntegerField IF_Folio = new IntegerField();
        FormLayout FL_Unidad = new FormLayout();
            ComboBox<Unidad> CB_Unidad = new ComboBox<Unidad>("Unidad");
            ComboBox<Area> CB_Area = new ComboBox<Area>("Area");
            ComboBox<UsuarioReporta> CB_UsuarioReporta = new ComboBox<UsuarioReporta>("Usuario Reporta");
            TextField TF_Telefono = new TextField();
            TextField TF_ReferenciaDocumental = new TextField();


    FormLayout FL_Incidencia = new FormLayout();
        ComboBox<Incidencia> CB_Incidencia = new ComboBox<Incidencia>("Incidencia");
        ComboBox<Bien> CB_Bien = new ComboBox<Bien>("Bien");
        ComboBox<Marca> CB_Marca = new ComboBox<Marca>("Marca");
        ComboBox<Modelo> CB_Modelo = new ComboBox<Modelo>("Modelo");
        TextField TF_NumeroSerie = new TextField("Numero Serie");
        TextField TF_NumeroInventario = new TextField("Numero Inventario");



    VerticalLayout VL_Objeto = new VerticalLayout();

    private UnidadesService unidadesService;

    VerticalLayout content;

    Tabs tabs;
    Tab tabUnidad;
    Tab tabObjeto;

    public FoliossView(UnidadesService unidadesService) {
        this.unidadesService = unidadesService;

        layoutUnidad();
        layoutIncidencia();
        layoutTabs();

        this.add(tabs,content);
    }

    private void layoutUnidad(){
        FL_Unidad.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        IF_Folio.setLabel("Numero Folio");

        //TF_Telefono.setAllowedCharPattern("^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$");
        TF_Telefono.setHelperText("Formato:+(123)456-7890");
        TF_Telefono.setLabel("Numero Telefonico");
        TF_Telefono.setWidth("240px");

        List<Unidad> listadoUnidades = unidadesService.getAllUnidades();

        CB_Unidad.setItems(listadoUnidades);

        CB_Unidad.setItemLabelGenerator(Unidad::getNombre);
        CB_Area.setItemLabelGenerator(Area::getNombre);
        CB_UsuarioReporta.setItemLabelGenerator(UsuarioReporta::getNombre);

        TF_ReferenciaDocumental.setLabel("Referencia Documental");
        TF_ReferenciaDocumental.setHelperText("Numero de oficio/orden/folio de seguimiento");

        //FL_Unidad.add(IF_Folio);
        FL_Unidad.add(CB_Unidad);
        FL_Unidad.add(CB_Area);
        FL_Unidad.add(CB_UsuarioReporta);
        FL_Unidad.add(TF_Telefono);
        FL_Unidad.add(TF_ReferenciaDocumental);

        VL_Unidad.add(IF_Folio,FL_Unidad);
    }

    private void layoutIncidencia(){
        FL_Incidencia.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        CB_Incidencia.setItemLabelGenerator(Incidencia::getNombre);

        FL_Incidencia.add(CB_Incidencia);
        FL_Incidencia.add(CB_Bien);
        FL_Incidencia.add(CB_Marca);
        FL_Incidencia.add(CB_Modelo);
        FL_Incidencia.add(TF_NumeroSerie);
        FL_Incidencia.add(TF_NumeroInventario);
    }

    private void layoutTabs(){
        tabUnidad = new Tab("UNIDAD");
        tabObjeto = new Tab("INCIDENCIA");

        tabs = new Tabs(tabUnidad,tabObjeto);

        tabs.addSelectedChangeListener(event -> setContent(event.getSelectedTab()));

        content = new VerticalLayout();
        content.setSpacing(false);
        setContent(tabs.getSelectedTab());
    }

    private void setContent(Tab tab) {
        content.removeAll();

        if (tab.equals(tabUnidad))
            content.add(VL_Unidad);
        else
            content.add(FL_Incidencia);
    }

}
