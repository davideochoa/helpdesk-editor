package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.domain.entity.Area;
import com.helpdeskeditor.application.app.domain.entity.ConcentradoFolioIncidencia;
import com.helpdeskeditor.application.app.domain.entity.Unidad;
import com.helpdeskeditor.application.app.service.AreasService;
import com.helpdeskeditor.application.app.service.ConcentradoFoliosIncidenciasService;
import com.helpdeskeditor.application.app.service.UnidadesService;
import com.helpdeskeditor.application.app.web.antigua.modelo.ModeloBien;
import com.helpdeskeditor.application.app.web.antigua.modelo.ModeloIncidencia;
import com.helpdeskeditor.application.app.web.antigua.modelo.ModeloMarca;
import com.helpdeskeditor.application.app.web.antigua.modelo.ModeloModelo;
import com.helpdeskeditor.application.app.web.antigua.modelo.ModeloUsuarioReporta;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.RolesAllowed;

@PageTitle("Folios")
@Route(value = "folios", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed("USER")
@Slf4j
public class FoliosView extends VerticalLayout {

    VerticalLayout VL_Unidad = new VerticalLayout();
    IntegerField IF_Folio = new IntegerField();
        FormLayout FL_Unidad = new FormLayout();
            ComboBox<Unidad> CB_Unidad = new ComboBox<Unidad>("Unidad");
            ComboBox<Area> CB_Area = new ComboBox<Area>("Area");
            ComboBox<String> CB_UsuarioReporta = new ComboBox<String>("Usuario Reporta");
            TextField TF_Telefono = new TextField();
            TextField TF_ReferenciaDocumental = new TextField();


    FormLayout FL_Incidencia = new FormLayout();
        ComboBox<ModeloIncidencia> CB_Incidencia = new ComboBox<ModeloIncidencia>("Incidencia");
        ComboBox<ModeloBien> CB_Bien = new ComboBox<ModeloBien>("Bien");
        ComboBox<ModeloMarca> CB_Marca = new ComboBox<ModeloMarca>("Marca");
        ComboBox<ModeloModelo> CB_Modelo = new ComboBox<ModeloModelo>("Modelo");
        TextField TF_NumeroSerie = new TextField("Numero Serie");
        TextField TF_NumeroInventario = new TextField("Numero Inventario");

    VerticalLayout VL_Objeto = new VerticalLayout();

    VerticalLayout content;

    Tabs tabs;
    Tab tabUnidad;
    Tab tabObjeto;

    private UnidadesService unidadesService;
    private AreasService areasService;
    ConcentradoFoliosIncidenciasService concentradoFoliosIncidenciasService;

    public FoliosView(UnidadesService unidadesService, AreasService areasService,
                      ConcentradoFoliosIncidenciasService concentradoFoliosIncidenciasService) {
        this.unidadesService = unidadesService;
        this.areasService = areasService;
        this.concentradoFoliosIncidenciasService = concentradoFoliosIncidenciasService;

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

        CB_Unidad.setItems(unidadesService.getAllUnidades());
        CB_Area.setItems(areasService.getAllAreas());
        CB_UsuarioReporta.setItems(concentradoFoliosIncidenciasService.findusuarioReporta());

        CB_Unidad.setItemLabelGenerator(Unidad::getNombre);
        CB_Area.setItemLabelGenerator(Area::getNombre);
        //CB_UsuarioReporta.setItemLabelGenerator(ConcentradoFolioIncidencia::getUsuarioReporta);

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

        CB_Incidencia.setItemLabelGenerator(ModeloIncidencia::getNombre);

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
