package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.datos.entity.AreaEntity;
import com.helpdeskeditor.application.app.datos.entity.BiendEntity;
import com.helpdeskeditor.application.app.datos.entity.CatalogoEstatusEntity;
import com.helpdeskeditor.application.app.datos.entity.EstatusEntity;
import com.helpdeskeditor.application.app.datos.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.datos.entity.PrioridadEntity;
import com.helpdeskeditor.application.app.datos.entity.UnidadEntity;
import com.helpdeskeditor.application.app.service.AreaService;
import com.helpdeskeditor.application.app.service.BienService;
import com.helpdeskeditor.application.app.service.CatalogoEstatusService;
import com.helpdeskeditor.application.app.service.EstatusService;
import com.helpdeskeditor.application.app.service.FolioIncidenciaService;
import com.helpdeskeditor.application.app.service.IncidenciaService;
import com.helpdeskeditor.application.app.service.PrioridadService;
import com.helpdeskeditor.application.app.service.UnidadService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

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
        ComboBox<String> CB_Marca = new ComboBox<String>("Marca");
        ComboBox<String> CB_Modelo = new ComboBox<String>("Modelo");
        TextField TF_NumeroSerie = new TextField("Numero Serie");
        TextField TF_NumeroInventario = new TextField("Numero Inventario");

    VerticalLayout VL_Motivo = new VerticalLayout();
        FormLayout FL_Motivo = new FormLayout();
            TextArea TA_MotivoReporte = new TextArea();
        ComboBox<PrioridadEntity> CB_Prioridad = new ComboBox<PrioridadEntity>("Prioridad");

    FormLayout FL_Estatus = new FormLayout();
        ComboBox<CatalogoEstatusEntity> CB_Estaus = new ComboBox<CatalogoEstatusEntity>("Estatus");
        TextArea TA_Anotacion = new TextArea("Anotacion");
        ComboBox<String> CB_SoporteAsignado = new ComboBox<String>("Soporte Asignado");
        ComboBox<String> CB_TipoIncidenciaFinal = new ComboBox<String>("Incidencia Final");

        Grid<EstatusEntity> GridEstatus = new Grid<>(EstatusEntity.class, false);

    Tabs tabs;
        Tab tabUnidad;
        Tab tabIncidencia;
        Tab tabMotivo;
        Tab tabEstatus;
        VerticalLayout contenidoTab;

    private UnidadService unidadService;
    private AreaService areaService;
    private FolioIncidenciaService folioIncidenciaService;
    private IncidenciaService incidenciaService;
    private BienService bienService;
    private PrioridadService prioridadService;
    private EstatusService estatusService;
    private CatalogoEstatusService catalogoEstatusService;

    @Value("${charLimit}")
    private int charLimit;

    public FoliosView(UnidadService unidadService,
                      AreaService areaService,
                      FolioIncidenciaService folioIncidenciaService,
                      IncidenciaService incidenciaService,
                      BienService bienService,
                      PrioridadService prioridadService,
                      EstatusService estatusService,
                      CatalogoEstatusService catalogoEstatusService) {
        this.unidadService = unidadService;
        this.areaService = areaService;
        this.folioIncidenciaService = folioIncidenciaService;
        this.incidenciaService = incidenciaService;
        this.bienService = bienService;
        this.prioridadService = prioridadService;
        this.estatusService = estatusService;
        this.catalogoEstatusService = catalogoEstatusService;

        layoutUnidad();
        layoutIncidencia();
        layoutMotivo();
        layoutEstatus();

        layoutTabs();

        this.add(tabs, contenidoTab);
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

        CB_Unidad.setItems(unidadService.findAll());
        CB_Unidad.addValueChangeListener(e -> {
            UnidadEntity seleccion = e.getValue();
            CB_Area.setItems(areaService.findByidUnidad(seleccion.getId()));
        });

        //CB_Area.setItems(areaService.getAllAreas());
        CB_UsuarioReporta.setItems(folioIncidenciaService.getAllUsuarioReporta());

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

        CB_Incidencia.setItems(incidenciaService.findAll());
        CB_Incidencia.setItemLabelGenerator(IncidenciaEntity::getNombre);
        CB_Incidencia.addValueChangeListener(e -> {
            IncidenciaEntity seleccion = e.getValue();
            CB_Bien.setItems(bienService.findByidTipoIncidencia(seleccion.getId()));
        });

        //CB_Bien.setItems(bienService.getAllBienes());
        CB_Bien.setItemLabelGenerator(BiendEntity::getNombre);

        CB_Marca.setItems(folioIncidenciaService.getAllMarca());
        CB_Marca.addValueChangeListener(e -> {
            String seleccion = e.getValue();
            CB_Modelo.setItems(folioIncidenciaService.findModeloByMarca(seleccion));
        });

        //CB_Modelo.setItems(folioIncidenciaService.getAllModelo());

        FL_Incidencia.add(CB_Incidencia);
        FL_Incidencia.add(CB_Bien);
        FL_Incidencia.add(CB_Marca);
        FL_Incidencia.add(CB_Modelo);
        FL_Incidencia.add(TF_NumeroSerie);
        FL_Incidencia.add(TF_NumeroInventario);
    }

    private void layoutMotivo(){

        FL_Motivo.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        //TA_MotivoReporte.setWidthFull();
        TA_MotivoReporte.setLabel("Description");
        TA_MotivoReporte.setMaxLength(charLimit);
        TA_MotivoReporte.setValueChangeMode(ValueChangeMode.EAGER);
        TA_MotivoReporte.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText(e.getValue().length() + "/" + charLimit);
        });

        CB_Prioridad.setItems(prioridadService.findAll());
        CB_Prioridad.setItemLabelGenerator(PrioridadEntity::getNombre);

        FL_Motivo.add(TA_MotivoReporte);

        VL_Motivo.add(FL_Motivo,CB_Prioridad);

    }

    private void layoutEstatus(){
        FL_Estatus.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        CB_Estaus.setItems(catalogoEstatusService.findAll());
        CB_Estaus.setItemLabelGenerator(CatalogoEstatusEntity::getNombre);

        TA_Anotacion.setLabel("Anotacion");
        TA_Anotacion.setMaxLength(charLimit);
        TA_Anotacion.setValueChangeMode(ValueChangeMode.EAGER);
        TA_Anotacion.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText(e.getValue().length() + "/" + charLimit);
        });

        GridEstatus.addColumn(EstatusEntity::getId).setHeader("Id");
        GridEstatus.addColumn(EstatusEntity::getFolio).setHeader("Folio");
        GridEstatus.addColumn(EstatusEntity::getIdEstatus).setHeader("IdEstatus");
        GridEstatus.addColumn(EstatusEntity::getAnotacion).setHeader("Anotacion");
        GridEstatus.addColumn(EstatusEntity::getIdUsuario).setHeader("IdUsuario");
        GridEstatus.addColumn(EstatusEntity::getFecha).setHeader("Fecha");
        GridEstatus.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        FL_Estatus.add(CB_Estaus,TA_Anotacion,CB_SoporteAsignado,CB_TipoIncidenciaFinal,GridEstatus);
    }

    private void layoutTabs(){
        tabUnidad = new Tab("UNIDAD");
        tabIncidencia = new Tab("INCIDENCIA");
        tabMotivo = new Tab("MOTIVO");
        tabEstatus = new Tab("ESTATUS");

        tabs = new Tabs(tabUnidad, tabIncidencia,tabMotivo,tabEstatus);

        tabs.addSelectedChangeListener(event -> setContent(event.getSelectedTab()));

        contenidoTab = new VerticalLayout();
        contenidoTab.setSpacing(false);
        setContent(tabs.getSelectedTab());
    }

    private void setContent(Tab tab) {
        contenidoTab.removeAll();

        if (tab.equals(tabUnidad))
            contenidoTab.add(VL_Unidad);
        else
            if (tab.equals(tabIncidencia))
                contenidoTab.add(FL_Incidencia);
            else
                if (tab.equals(tabMotivo))
                    contenidoTab.add(VL_Motivo);
                else
                    if (tab.equals(tabEstatus))
                        contenidoTab.add(FL_Estatus);

    }

}
