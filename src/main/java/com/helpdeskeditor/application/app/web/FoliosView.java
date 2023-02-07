package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.data.DAO.EstatusDAO;
import com.helpdeskeditor.application.app.data.entity.AreaEntity;
import com.helpdeskeditor.application.app.data.entity.BiendEntity;
import com.helpdeskeditor.application.app.data.entity.CatalogoEstatusEntity;
import com.helpdeskeditor.application.app.data.entity.FolioEntity;
import com.helpdeskeditor.application.app.data.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.data.entity.PrioridadEntity;
import com.helpdeskeditor.application.app.data.entity.UnidadEntity;
import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.helpdeskeditor.application.app.service.AreaService;
import com.helpdeskeditor.application.app.service.BienService;
import com.helpdeskeditor.application.app.service.CatalogoEstatusService;
import com.helpdeskeditor.application.app.service.EstatusService;
import com.helpdeskeditor.application.app.service.FolioService;
import com.helpdeskeditor.application.app.service.IncidenciaService;
import com.helpdeskeditor.application.app.service.PrioridadService;
import com.helpdeskeditor.application.app.service.UnidadService;
import com.helpdeskeditor.application.app.service.UsuarioSoporteService;
import com.helpdeskeditor.application.util.DisplayInfo;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@PageTitle("Folios")
@Route(value = "folios", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed({"USER","ADMIN"})
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
            private DatePicker DtePikr_fechaApertura = new DatePicker("Fecha Apertura");
        Button Btt_SalvarUnidad = new Button("GUARDAR");

    private VerticalLayout VL_Incidencia = new VerticalLayout();
        private FormLayout FL_Incidencia = new FormLayout();
            private ComboBox<IncidenciaEntity> CB_Incidencia = new ComboBox<IncidenciaEntity>("Incidencia");
            private ComboBox<BiendEntity> CB_Bien = new ComboBox<BiendEntity>("Bien");
            private ComboBox<String> CB_Marca = new ComboBox<String>("Marca");
            private ComboBox<String> CB_Modelo = new ComboBox<String>("Modelo");
            private TextField TF_NumeroSerie = new TextField("Numero Serie");
            private TextField TF_NumeroInventario = new TextField("Numero Inventario");
        Button Btt_SalvarIncidencia = new Button("GUARDAR");


    private VerticalLayout VL_Motivo = new VerticalLayout();
        private FormLayout FL_Motivo = new FormLayout();
            private TextArea TA_MotivoReporte = new TextArea();
        private ComboBox<PrioridadEntity> CB_Prioridad = new ComboBox<PrioridadEntity>("Prioridad");
        Button Btt_SalvarMotivo = new Button("GUARDAR");

    private VerticalLayout VL_Estatus = new VerticalLayout();
        private FormLayout FL_Estatus = new FormLayout();
            private ComboBox<CatalogoEstatusEntity> CB_Estaus = new ComboBox<CatalogoEstatusEntity>("Estatus");
            private TextArea TA_Anotacion = new TextArea("Anotacion");
            private ComboBox<UsuarioSoporteEntity> CB_SoporteAsignado = new ComboBox<UsuarioSoporteEntity>("Soporte Asignado");
            private ComboBox<IncidenciaEntity> CB_TipoIncidenciaFinal = new ComboBox<IncidenciaEntity>("Incidencia Final");
        private Button Btt_AgregarEstatus = new Button("AGREGAR");
        private Grid<EstatusDAO> GridEstatus = new Grid<>(EstatusDAO.class, false);
        private Button Btt_SalvarEstatus = new Button("GUARDAR");

    private Tabs tabs;
        private Tab tabUnidad;
        private Tab tabIncidencia;
        private Tab tabMotivo;
        private Tab tabEstatus;
        private VerticalLayout contenidoTab;

    private UnidadService unidadService;
    private AreaService areaService;
    private final FolioService folioService;
    private IncidenciaService incidenciaService;
    private IncidenciaService incidenciaServiceFinal;
    private BienService bienService;
    private PrioridadService prioridadService;
    private EstatusService estatusService;
    private CatalogoEstatusService catalogoEstatusService;
    private UsuarioSoporteService usuarioSoporteService;

    @Value("${charLimit}")
    private int charLimit;

    FolioEntity folioEntity = null;
    Dialog dialogEspere = DisplayInfo.dialogPorgressBarIndeterminate("Modificando Folio", "Espere mientras se modifica el folio");

    public FoliosView(UnidadService unidadService,
                      AreaService areaService,
                      FolioService folioService,
                      IncidenciaService incidenciaService,
                      BienService bienService,
                      PrioridadService prioridadService,
                      EstatusService estatusService,
                      CatalogoEstatusService catalogoEstatusService,
                      UsuarioSoporteService usuarioSoporteService,
                      IncidenciaService incidenciaServiceFinal) {

        this.unidadService = unidadService;
        this.areaService = areaService;
        this.folioService = folioService;
        this.incidenciaService = incidenciaService;
        this.bienService = bienService;
        this.prioridadService = prioridadService;
        this.estatusService = estatusService;
        this.catalogoEstatusService = catalogoEstatusService;
        this.usuarioSoporteService = usuarioSoporteService;
        this.incidenciaServiceFinal = incidenciaServiceFinal;

        folioEntity = new FolioEntity();

        layoutUnidad();
        layoutIncidencia();
        layoutMotivo();
        layoutEstatus();

        layoutTabs();

        this.add(tabs, contenidoTab);
    }

    private void borrar(){
        IF_Folio.clear();
        CB_Unidad.clear();
        CB_Area.clear();
        CB_UsuarioReporta.clear();
        TF_Telefono.clear();
        TF_ReferenciaDocumental.clear();
        DtePikr_fechaApertura.clear();
        DtePikr_fechaApertura.setValue(LocalDate.now(ZoneId.systemDefault()));

        CB_Incidencia.clear();
        CB_Bien.clear();
        CB_Marca.clear();
        CB_Modelo.clear();
        TF_NumeroSerie.clear();
        TF_NumeroInventario.clear();

        TA_MotivoReporte.clear();
        CB_Prioridad.clear();
    }

    private boolean cargarFolio(Integer folio){
        folioEntity = folioService.findById(folio).get();

        if(folioEntity.getId() > 0){
            //************************** UNIDAD *************************
            CB_Unidad.setValue(unidadService.findById(folioEntity.getIdUnidad()).get());
            CB_Area.setValue(areaService.findByIdAndIdUnidad(folioEntity.getIdArea(), unidadService.findById(folioEntity.getIdUnidad()).get().getId()));
            CB_UsuarioReporta.setValue(folioEntity.getUsuarioReporta());

            String valor_str = folioEntity.getTelefonoContacto();
            if(valor_str == null || valor_str.length() == 0 ||
                    valor_str.equals("null") || valor_str.equals("NULL") ||
                    valor_str.equals("Null"))
                valor_str = "NO ESPECIFICADO";
            else
                valor_str = folioEntity.getTelefonoContacto();

            TF_Telefono.setValue(valor_str);

            valor_str = folioEntity.getReferenciaDocumental();
            if(valor_str == null || valor_str.length() == 0 ||
                    valor_str.equals("null") || valor_str.equals("NULL") ||
                    valor_str.equals("Null"))
                valor_str = "NO ESPECIFICADO";
            else
                valor_str = folioEntity.getReferenciaDocumental();

            TF_ReferenciaDocumental.setValue(valor_str);
            DtePikr_fechaApertura.setValue(LocalDate.ofInstant(folioEntity.getFecha().toInstant(), ZoneId.systemDefault()));

            //************************************************************************
            IncidenciaEntity incidenciaEntity = incidenciaService.findById(folioEntity.getIdTipoIncidencia()).get();
            BiendEntity biendEntity = bienService.findByIdAndIdTipoIncidencia(folioEntity.getIdBien(), folioEntity.getIdTipoIncidencia());
            String marca = folioEntity.getMarca();
            String modelo = folioEntity.getModelo();
            String numSerie = folioEntity.getNumeroSerie();
            String numInventario = folioEntity.getNumeroInventario();

            String motivoReporte = folioEntity.getMotivoReporte();
            PrioridadEntity prioridad = prioridadService.findById(folioEntity.getIdPrioridad()).get();

            List<EstatusDAO> estatusEntityList = estatusService.findAllDAO(folioEntity.getId());

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

    private Boolean guardar(){
        dialogEspere.open();

        //****************** UNIDAD ******************************************************
        UnidadEntity unidadEntity = CB_Unidad.getValue();
        if(unidadEntity != null)
            folioEntity.setIdUnidad(unidadEntity.getId());

        AreaEntity areaEntity = CB_Area.getValue();
        if(areaEntity != null)
            folioEntity.setIdArea(areaEntity.getId());

        String valor_str = CB_UsuarioReporta.getValue();
        if(valor_str == null)
            valor_str = "NO ESPECIFICADO";

        folioEntity.setUsuarioReporta(valor_str);

        valor_str = TF_Telefono.getValue();
        if(valor_str == null)
            valor_str = "NO ESPECIFICADO";

        folioEntity.setTelefonoContacto(valor_str);

        valor_str = TF_ReferenciaDocumental.getValue();
        if(valor_str == null)
            valor_str = "NO ESPECIFICADO";

        folioEntity.setReferenciaDocumental(valor_str);

        folioEntity.setFecha(Date.from(DtePikr_fechaApertura.getValue().atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));

        //**************** INCIDENCIA *******************************
        IncidenciaEntity incidenciaEntity = CB_Incidencia.getValue();
        if(incidenciaEntity != null)
            folioEntity.setIdTipoIncidencia(incidenciaEntity.getId());

        BiendEntity biendEntity = CB_Bien.getValue();
        if(biendEntity != null)
            folioEntity.setIdBien(biendEntity.getId());

        valor_str = CB_Marca.getValue();
        if(valor_str == null || valor_str.length() == 0)
            valor_str = "NO ESPECIFICADO";

        folioEntity.setMarca(valor_str);

        valor_str = CB_Modelo.getValue();
        if(valor_str == null || valor_str.length() == 0)
            valor_str = "NO ESPECIFICADO";

        folioEntity.setModelo(valor_str);

        valor_str = TF_NumeroSerie.getValue();
        if(valor_str == null || valor_str.length() == 0)
            valor_str = "NO ESPECIFICADO";

        valor_str = TF_NumeroInventario.getValue();
        if(valor_str == null || valor_str.length() == 0)
            valor_str = "NO ESPECIFICADO";

        folioEntity.setNumeroInventario(valor_str);

        //****************** MOTIVO *******************************
        valor_str = TA_MotivoReporte.getValue();
        if(valor_str == null || valor_str.length() == 0)
            valor_str = "NO ESPECIFICADO";

        folioEntity.setMotivoReporte(valor_str);

        folioEntity = folioService.save(folioEntity);

        dialogEspere.close();

        if(folioEntity.getId() > 0) {
            borrar();
            DisplayInfo.notificacion("Folio modificado!",
                    NotificationVariant.LUMO_SUCCESS,
                    Notification.Position.MIDDLE).setVisible(true);

        }
        else
            DisplayInfo.notificacion("Error al modificar Folio!",
                                        NotificationVariant.LUMO_ERROR,
                                        Notification.Position.MIDDLE).setVisible(true);


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
        Button Btt_nuevo = new Button ("Nuevo");
        Btt_nuevo.addClickListener(e -> {
            Notification.show("NUEVO ");
        });*/

        HL_Folio_BotnoCargar.setVerticalComponentAlignment(Alignment.BASELINE,IF_Folio,B_cargar);//,B_borrar);
        HL_Folio_BotnoCargar.add(IF_Folio,B_cargar);//,B_borrar);

        //TF_Telefono.setAllowedCharPattern("^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$");
        TF_Telefono.setHelperText("Formato:+(123)456-7890");
        TF_Telefono.setLabel("Numero Telefonico");
        TF_Telefono.setWidth("240px");

        CB_Unidad.setItemLabelGenerator(UnidadEntity::getNombre);
        CB_Unidad.setItems(unidadService.findAll());
        CB_Unidad.addValueChangeListener(e -> {
            if(e.getValue() != null){
                CB_Area.setItems(areaService.findByidUnidad(CB_Unidad.getValue().getId()));
            }
        });

        CB_Area.setItemLabelGenerator(AreaEntity::getNombre);

        CB_UsuarioReporta.setItems(folioService.getAllUsuarioReporta());

        TF_ReferenciaDocumental.setLabel("Referencia Documental");
        TF_ReferenciaDocumental.setHelperText("Numero de oficio/orden/folio de seguimiento");

        DtePikr_fechaApertura.setPlaceholder("yyyy-MM-dd");

        Btt_SalvarUnidad.addClickListener(e -> { guardar(); });
        Btt_SalvarUnidad.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        FL_Unidad.add(CB_Unidad);
        FL_Unidad.add(CB_Area);
        FL_Unidad.add(CB_UsuarioReporta);
        FL_Unidad.add(TF_Telefono);
        FL_Unidad.add(TF_ReferenciaDocumental);
        FL_Unidad.add(DtePikr_fechaApertura);

        VL_Unidad.add(HL_Folio_BotnoCargar,FL_Unidad,Btt_SalvarUnidad);
    }

    private void layoutIncidencia(){
        VL_Incidencia.setMargin(false);
        VL_Incidencia.setPadding(false);

        FL_Incidencia.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        CB_Incidencia.setItems(incidenciaService.findAll());
        CB_Incidencia.setItemLabelGenerator(IncidenciaEntity::getNombre);
        CB_Incidencia.addValueChangeListener(e -> {
            if(e.getValue() != null){
                folioEntity.setIdBien(0);
                folioEntity.setMarca("NO ESPECIFICADO");
                folioEntity.setModelo("NO ESPECIFICADO");

                CB_Bien.setItems(bienService.findByIdTipoIncidenciaOrderByNombreAsc(CB_Incidencia.getValue().getId()));

                CB_Marca.clear();
                CB_Modelo.clear();
                TF_NumeroSerie.clear();
                TF_NumeroInventario.clear();
            }
        });

        CB_Bien.setItemLabelGenerator(BiendEntity::getNombre);
        CB_Bien.addValueChangeListener(e -> {
            if(e.getValue() != null){
                folioEntity.setMarca("NO ESPECIFICADO");
                folioEntity.setModelo("NO ESPECIFICADO");

                CB_Marca.setItems(folioService.findMarcaByIdIncidenciaAndIdBien(CB_Incidencia.getValue().getId(),
                                                                                e.getValue().getId()));

                CB_Modelo.clear();
                TF_NumeroSerie.clear();
                TF_NumeroInventario.clear();
            }
        });

        CB_Marca.addValueChangeListener(e -> {
            if (e.getValue() != null) {

                CB_Modelo.setItems(folioService.findModeloByIdIncidenciaAndIdBienAndMarca(
                                    CB_Incidencia.getValue().getId(),
                                    CB_Bien.getValue().getId(),
                                    CB_Marca.getValue()));
                TF_NumeroSerie.clear();
                TF_NumeroInventario.clear();
            }
        });

        CB_Modelo.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                TF_NumeroSerie.clear();
                TF_NumeroInventario.clear();
            }
        });

        Btt_SalvarIncidencia.addClickListener(e -> {
            guardar();
        });
        Btt_SalvarIncidencia.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        FL_Incidencia.add(CB_Incidencia);
        FL_Incidencia.add(CB_Bien);
        FL_Incidencia.add(CB_Marca);
        FL_Incidencia.add(CB_Modelo);
        FL_Incidencia.add(TF_NumeroSerie);
        FL_Incidencia.add(TF_NumeroInventario);

        VL_Incidencia.add(FL_Incidencia,Btt_SalvarIncidencia);
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
        //TA_MotivoReporte.setValueChangeMode(ValueChangeMode.EAGER);
        TA_MotivoReporte.addValueChangeListener(e -> {
            e.getSource().setHelperText(e.getValue().length() + "/" + charLimit);
            if(e.getValue() != null)
                folioEntity.setMotivoReporte(e.getValue());
        });

        CB_Prioridad.setItems(prioridadService.findAll());
        CB_Prioridad.setItemLabelGenerator(PrioridadEntity::getNombre);
        CB_Prioridad.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                folioEntity.setIdPrioridad(e.getValue().getId());
            }
        });
        FL_Motivo.add(TA_MotivoReporte);

        Btt_SalvarMotivo.addClickListener(e -> {
            guardar();
        });
        Btt_SalvarMotivo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        VL_Motivo.add(FL_Motivo,CB_Prioridad,Btt_SalvarMotivo);

    }

    private void layoutEstatus(){
        VL_Estatus.setMargin(false);
        VL_Estatus.setPadding(false);

        FL_Estatus.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        CB_Estaus.setItems(catalogoEstatusService.findAll());
        CB_Estaus.setItemLabelGenerator(CatalogoEstatusEntity::getNombre);
        CB_Estaus.addValueChangeListener(e -> {
            if(e.getValue() != null){
                //folioEntity.set(e.getValue().getId());
            }
        });

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

        GridEstatus.addColumn(EstatusDAO::getNombre).setHeader("Estatus");//.setAutoWidth(true);
        GridEstatus.addColumn(EstatusDAO::getAnotacion).setHeader("Anotacion");//.setAutoWidth(true);
        GridEstatus.addColumn(EstatusDAO::getNombrePropio).setHeader("Usuario").setWidth("15em");
        GridEstatus.addColumn(EstatusDAO::getFecha).setHeader("Fecha").setWidth("15em");

        GridEstatus.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        GridEstatus.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        GridEstatus.setAllRowsVisible(true);

        //FL_Estatus.setColspan(GridEstatus, 2);

        Btt_SalvarEstatus.addClickListener(e -> {
            guardar();
        });
        Btt_SalvarEstatus.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        FL_Estatus.add(CB_Estaus,TA_Anotacion,CB_SoporteAsignado,CB_TipoIncidenciaFinal);

        VL_Estatus.add(FL_Estatus,Btt_AgregarEstatus,GridEstatus,Btt_SalvarEstatus);

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
                contenidoTab.add(VL_Incidencia);
            else
                if (tab.equals(tabMotivo))
                    contenidoTab.add(VL_Motivo);
                else
                    if (tab.equals(tabEstatus))
                        contenidoTab.add(VL_Estatus);

    }

}
