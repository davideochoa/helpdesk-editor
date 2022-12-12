package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.domain.entity.AreaEntity;
import com.helpdeskeditor.application.app.domain.entity.BiendEntity;
import com.helpdeskeditor.application.app.domain.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.domain.entity.UnidadEntity;
import com.helpdeskeditor.application.app.service.AreaService;
import com.helpdeskeditor.application.app.service.BienService;
import com.helpdeskeditor.application.app.service.FolioIncidenciaService;
import com.helpdeskeditor.application.app.service.IncidenciaService;
import com.helpdeskeditor.application.app.service.UnidadService;
import com.helpdeskeditor.application.app.web.antigua.modelo.ModeloMarca;
import com.helpdeskeditor.application.app.web.antigua.modelo.ModeloModelo;
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
            ComboBox<UnidadEntity> CB_Unidad = new ComboBox<UnidadEntity>("Unidad");
            ComboBox<AreaEntity> CB_Area = new ComboBox<AreaEntity>("Area");
            ComboBox<String> CB_UsuarioReporta = new ComboBox<String>("Usuario Reporta");
            TextField TF_Telefono = new TextField();
            TextField TF_ReferenciaDocumental = new TextField();


    FormLayout FL_Incidencia = new FormLayout();
        ComboBox<IncidenciaEntity> CB_Incidencia = new ComboBox<IncidenciaEntity>("Incidencia");
        ComboBox<BiendEntity> CB_Bien = new ComboBox<BiendEntity>("Bien");
        ComboBox<ModeloMarca> CB_Marca = new ComboBox<ModeloMarca>("Marca");
        ComboBox<ModeloModelo> CB_Modelo = new ComboBox<ModeloModelo>("Modelo");
        TextField TF_NumeroSerie = new TextField("Numero Serie");
        TextField TF_NumeroInventario = new TextField("Numero Inventario");

    FormLayout FL_Motivo = new FormLayout();

    VerticalLayout VL_Objeto = new VerticalLayout();

    VerticalLayout content;

    Tabs tabs;
    Tab tabUnidad;
    Tab tabIncidencia;
    Tab tabMotivo;

    private UnidadService unidadService;
    private AreaService areaService;
    private FolioIncidenciaService folioIncidenciaService;
    IncidenciaService incidenciaService;

    BienService bienService;
    public FoliosView(UnidadService unidadService,
                      AreaService areaService,
                      FolioIncidenciaService folioIncidenciaService,
                      IncidenciaService incidenciaService,
                      BienService bienService) {
        this.unidadService = unidadService;
        this.areaService = areaService;
        this.folioIncidenciaService = folioIncidenciaService;
        this.incidenciaService = incidenciaService;
        this.bienService = bienService;

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

        CB_Unidad.setItems(unidadService.getAllUnidades());
        CB_Area.setItems(areaService.getAllAreas());
        CB_UsuarioReporta.setItems(folioIncidenciaService.getUsuarioReporta());

        CB_Unidad.setItemLabelGenerator(UnidadEntity::getNombre);
        CB_Area.setItemLabelGenerator(AreaEntity::getNombre);
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

        CB_Incidencia.setItems(incidenciaService.incidenciaFacade());
        CB_Incidencia.setItemLabelGenerator(IncidenciaEntity::getNombre);

        CB_Bien.setItems(bienService.getAllBienes());
        CB_Bien.setItemLabelGenerator(BiendEntity::getNombre);

        FL_Incidencia.add(CB_Incidencia);
        FL_Incidencia.add(CB_Bien);
        FL_Incidencia.add(CB_Marca);
        FL_Incidencia.add(CB_Modelo);
        FL_Incidencia.add(TF_NumeroSerie);
        FL_Incidencia.add(TF_NumeroInventario);
    }

    private void layoutTabs(){
        tabUnidad = new Tab("UNIDAD");
        tabIncidencia = new Tab("INCIDENCIA");
        tabMotivo = new Tab("MOTIVO");

        tabs = new Tabs(tabUnidad, tabIncidencia,tabMotivo);

        tabs.addSelectedChangeListener(event -> setContent(event.getSelectedTab()));

        content = new VerticalLayout();
        content.setSpacing(false);
        setContent(tabs.getSelectedTab());
    }

    private void setContent(Tab tab) {
        content.removeAll();

        if (tab.equals(tabUnidad))
            content.add(VL_Unidad);

        if (tab.equals(tabIncidencia))
            content.add(FL_Incidencia);

        if (tab.equals(tabMotivo))
            content.add(FL_Motivo);
    }

}
