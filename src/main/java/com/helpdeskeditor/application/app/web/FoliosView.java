package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.data.DAO.EstatusDAO;
import com.helpdeskeditor.application.app.data.entity.AreaEntity;
import com.helpdeskeditor.application.app.data.entity.BiendEntity;
import com.helpdeskeditor.application.app.data.entity.CatalogoEstatusEntity;
import com.helpdeskeditor.application.app.data.entity.EstatusEntity;
import com.helpdeskeditor.application.app.data.entity.FolioIncidenciaEntity;
import com.helpdeskeditor.application.app.data.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.data.entity.PrioridadEntity;
import com.helpdeskeditor.application.app.data.entity.UnidadEntity;
import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.helpdeskeditor.application.app.service.AreaService;
import com.helpdeskeditor.application.app.service.BienService;
import com.helpdeskeditor.application.app.service.CatalogoEstatusService;
import com.helpdeskeditor.application.app.service.EstatusService;
import com.helpdeskeditor.application.app.service.FolioIncidenciaService;
import com.helpdeskeditor.application.app.service.IncidenciaService;
import com.helpdeskeditor.application.app.service.PrioridadService;
import com.helpdeskeditor.application.app.service.UnidadService;
import com.helpdeskeditor.application.app.service.UsuarioSoporteService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
import java.util.List;
import java.util.Optional;

@PageTitle("Folios")
@Route(value = "folios", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed("USER")
@Slf4j
public class FoliosView extends VerticalLayout {

    private VerticalLayout VL_Unidad = new VerticalLayout();
        private IntegerField IF_Folio = new IntegerField();
        private FormLayout FL_Unidad = new FormLayout();
            private ComboBox<UnidadEntity> CB_Unidad = new ComboBox<UnidadEntity>("Unidad");
            private ComboBox<AreaEntity> CB_Area = new ComboBox<AreaEntity>("Area");
            private ComboBox<String> CB_UsuarioReporta = new ComboBox<String>("Usuario Reporta");
            private TextField TF_Telefono = new TextField();
            private TextField TF_ReferenciaDocumental = new TextField();

    private FormLayout FL_Incidencia = new FormLayout();
        private ComboBox<IncidenciaEntity> CB_Incidencia = new ComboBox<IncidenciaEntity>("Incidencia");
        private ComboBox<BiendEntity> CB_Bien = new ComboBox<BiendEntity>("Bien");
        private ComboBox<String> CB_Marca = new ComboBox<String>("Marca");
        private ComboBox<String> CB_Modelo = new ComboBox<String>("Modelo");
        private TextField TF_NumeroSerie = new TextField("Numero Serie");
        private TextField TF_NumeroInventario = new TextField("Numero Inventario");

    private VerticalLayout VL_Motivo = new VerticalLayout();
        private FormLayout FL_Motivo = new FormLayout();
            private TextArea TA_MotivoReporte = new TextArea();
        private ComboBox<PrioridadEntity> CB_Prioridad = new ComboBox<PrioridadEntity>("Prioridad");

    private FormLayout FL_Estatus = new FormLayout();
        private ComboBox<CatalogoEstatusEntity> CB_Estaus = new ComboBox<CatalogoEstatusEntity>("Estatus");
        private TextArea TA_Anotacion = new TextArea("Anotacion");
        private ComboBox<UsuarioSoporteEntity> CB_SoporteAsignado = new ComboBox<UsuarioSoporteEntity>("Soporte Asignado");
        private ComboBox<IncidenciaEntity> CB_TipoIncidenciaFinal = new ComboBox<IncidenciaEntity>("Incidencia Final");
        private Grid<EstatusDAO> GridEstatus = new Grid<>(EstatusDAO.class, false);

    private Tabs tabs;
        private Tab tabUnidad;
        private Tab tabIncidencia;
        private Tab tabMotivo;
        private Tab tabEstatus;
        private VerticalLayout contenidoTab;

    private UnidadService unidadService;
    private AreaService areaService;
    private FolioIncidenciaService folioIncidenciaService;
    private IncidenciaService incidenciaService;
    private IncidenciaService incidenciaServiceFinal;
    private BienService bienService;
    private PrioridadService prioridadService;
    private EstatusService estatusService;
    private CatalogoEstatusService catalogoEstatusService;
    private UsuarioSoporteService usuarioSoporteService;

    @Value("${charLimit}")
    private int charLimit;

    UnidadEntity unidadEntity = null;

    AreaEntity areaEntity = null;

    String str_usuarioReporta = null;
    String str_telefonoContacto = null;
    String str_referenciaDocumental = null;

    FolioIncidenciaEntity incidencia = null;

    public FoliosView(UnidadService unidadService,
                      AreaService areaService,
                      FolioIncidenciaService folioIncidenciaService,
                      IncidenciaService incidenciaService,
                      BienService bienService,
                      PrioridadService prioridadService,
                      EstatusService estatusService,
                      CatalogoEstatusService catalogoEstatusService,
                      UsuarioSoporteService usuarioSoporteService,
                      IncidenciaService incidenciaServiceFinal) {

        this.unidadService = unidadService;
        this.areaService = areaService;
        this.folioIncidenciaService = folioIncidenciaService;
        this.incidenciaService = incidenciaService;
        this.bienService = bienService;
        this.prioridadService = prioridadService;
        this.estatusService = estatusService;
        this.catalogoEstatusService = catalogoEstatusService;
        this.usuarioSoporteService = usuarioSoporteService;
        this.incidenciaServiceFinal = incidenciaServiceFinal;

        layoutUnidad();
        layoutIncidencia();
        layoutMotivo();
        layoutEstatus();

        layoutTabs();

        this.add(tabs, contenidoTab);
    }

    private boolean cargarFolio(Integer folio){
        incidencia = folioIncidenciaService.findById(folio).get();

        if(incidencia.getId() > 0){
            //************************** UNIDAD *************************
            unidadEntity = unidadService.findById(incidencia.getIdUnidad()).get();
            areaEntity = areaService.findByIdAndIdUnidad(incidencia.getIdArea(),unidadEntity.getId());
            str_usuarioReporta = incidencia.getUsuarioReporta();

            str_telefonoContacto = incidencia.getTelefonoContacto();
            if(str_telefonoContacto == null || str_telefonoContacto.length() == 0 || str_telefonoContacto.equals("null") || telefonoContacto.equals("NULL") || telefonoContacto.equals("Null"))
                str_telefonoContacto = "NO ESPECIFICADO";
            else
                str_telefonoContacto = incidencia.getTelefonoContacto();

            str_referenciaDocumental = incidencia.getReferenciaDocumental();
            if(str_referenciaDocumental == null || str_referenciaDocumental.length() == 0 || str_referenciaDocumental.equals("null") || referenciaDocumental.equals("NULL") || telefonoContacto.equals("Null"))
                str_referenciaDocumental = "NO ESPECIFICADO";
            else
                str_referenciaDocumental = incidencia.getReferenciaDocumental();

            //************************************************************************
            IncidenciaEntity incidenciaEntity = incidenciaService.findById(incidencia.getIdTipoIncidencia()).get();
            BiendEntity biendEntity = bienService.findByIdAndIdTipoIncidencia(incidencia.getIdBien(),incidencia.getIdTipoIncidencia());
            String marca = incidencia.getMarca();
            String modelo = incidencia.getModelo();
            String numSerie = incidencia.getModelo();
            String numInventario = incidencia.getModelo();

            String motivoReporte = incidencia.getMotivoReporte();
            PrioridadEntity prioridad = prioridadService.findById(incidencia.getIdPrioridad()).get();

            List<EstatusDAO> estatusEntityList = estatusService.findAllDAO(incidencia.getId());






            CB_Unidad.setValue(unidadEntity);
            CB_Area.setValue(areaEntity);
            CB_UsuarioReporta.setValue(str_usuarioReporta);
            TF_Telefono.setValue(telefonoContacto);
            TF_ReferenciaDocumental.setValue(referenciaDocumental);

            CB_Incidencia.setValue(incidenciaEntity);
            CB_Bien.setValue(biendEntity);
            CB_Marca.setValue(marca);
            CB_Modelo.setValue(modelo);
            TF_NumeroSerie.setValue(numSerie);
            TF_NumeroInventario.setValue(numInventario);

            TA_MotivoReporte.setValue(motivoReporte);
            CB_Prioridad.setValue(prioridad);

            GridEstatus.setItems(estatusEntityList);
        }

        return true;
    }

    private void layoutUnidad(){
        VL_Unidad.setMargin(false);
        VL_Unidad.setPadding(false);

        FL_Unidad.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        HorizontalLayout HL_Folio_BotnoCargar = new HorizontalLayout();
        HL_Folio_BotnoCargar.setMargin(false);
        HL_Folio_BotnoCargar.setPadding(false);

        IF_Folio.setLabel("Folio");
        IF_Folio.setHelperText("Numero de folio a cargar");

        Button B_cargar = new Button ("Cargar");
        B_cargar.addClickListener(e -> {
            cargarFolio(IF_Folio.getValue());
        });
        B_cargar.addClickShortcut(Key.ENTER);
/*
        Button B_borrar = new Button ("Borrar");
        B_borrar.addClickListener(e -> {
            Notification.show("B_borrar ");
        });*/

        HL_Folio_BotnoCargar.setVerticalComponentAlignment(Alignment.BASELINE,IF_Folio,B_cargar);//,B_borrar);
        HL_Folio_BotnoCargar.add(IF_Folio,B_cargar);//,B_borrar);

        //TF_Telefono.setAllowedCharPattern("^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$");
        TF_Telefono.setHelperText("Formato:+(123)456-7890");
        TF_Telefono.setLabel("Numero Telefonico");
        TF_Telefono.setWidth("240px");

        CB_Unidad.setItems(unidadService.findAll());
        CB_Unidad.addValueChangeListener(e -> {
            unidadEntity = e.getValue();
            incidencia.setIdUnidad(unidadEntity.getId());
            CB_Area.setItems(areaService.findByidUnidad(unidadEntity.getId()));
        });

        CB_Area.addValueChangeListener(e -> {
            areaEntity = e.getValue();
            incidencia.setIdArea(areaEntity.getId());
        });

        CB_UsuarioReporta.setItems(folioIncidenciaService.getAllUsuarioReporta());
        CB_UsuarioReporta.addValueChangeListener(e -> {
            areaEntity = e.getValue();
        });

        CB_Unidad.setItemLabelGenerator(UnidadEntity::getNombre);
        CB_Area.setItemLabelGenerator(AreaEntity::getNombre);

        TF_ReferenciaDocumental.setLabel("Referencia Documental");
        TF_ReferenciaDocumental.setHelperText("Numero de oficio/orden/folio de seguimiento");

        Button Btt_Salvar = new Button("GUARDAR");
        Btt_Salvar.addClickListener(e -> {
            guardarFolio();
        });
        Btt_Salvar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        FL_Unidad.add(CB_Unidad);
        FL_Unidad.add(CB_Area);
        FL_Unidad.add(CB_UsuarioReporta);
        FL_Unidad.add(TF_Telefono);
        FL_Unidad.add(TF_ReferenciaDocumental);

        VL_Unidad.add(HL_Folio_BotnoCargar,FL_Unidad,Btt_Salvar);
    }

    private Boolean guardarFolio(){
        if(incidencia.getId() > 0){

            incidencia.setIdUnidad(unidadEntity.getId());
            incidencia.setIdArea(areaEntity.getId());

        }

        return true;
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
            CB_Bien.setItems(bienService.findByIdTipoIncidenciaOrderByNombreAsc(seleccion.getId()));
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
        VL_Motivo.setMargin(false);
        VL_Motivo.setPadding(false);

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

        CB_SoporteAsignado.setItems(usuarioSoporteService.findByOrderBynombreUsuarioAsc());
        CB_SoporteAsignado.setItemLabelGenerator(UsuarioSoporteEntity::getNombrePropio);

        CB_TipoIncidenciaFinal.setItems(incidenciaServiceFinal.findAll());
        CB_TipoIncidenciaFinal.setItemLabelGenerator(IncidenciaEntity::getNombre);

        TA_Anotacion.setLabel("Anotacion");
        TA_Anotacion.setMaxLength(charLimit);
        TA_Anotacion.setValueChangeMode(ValueChangeMode.EAGER);
        TA_Anotacion.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText(e.getValue().length() + "/" + charLimit);
        });

        //GridEstatus.addColumn(EstatusDAO::getId).setHeader("Id");
        //GridEstatus.addColumn(EstatusDAO::getFolio).setHeader("Folio");
        //GridEstatus.addColumn(EstatusDAO::getIdEstatus).setHeader("IdEstatus");
        GridEstatus.addColumn(EstatusDAO::getNombre).setHeader("Estatus");//.setAutoWidth(true);
        GridEstatus.addColumn(EstatusDAO::getAnotacion).setHeader("Anotacion");//.setAutoWidth(true);
        //GridEstatus.addColumn(EstatusDAO::getIdUsuario).setHeader("IdUsuario");
        GridEstatus.addColumn(EstatusDAO::getNombrePropio).setHeader("Usuario").setWidth("15em");
        GridEstatus.addColumn(EstatusDAO::getFecha).setHeader("Fecha").setWidth("15em");

        GridEstatus.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        GridEstatus.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        GridEstatus.setAllRowsVisible(true);

        FL_Estatus.setColspan(GridEstatus, 2);

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
