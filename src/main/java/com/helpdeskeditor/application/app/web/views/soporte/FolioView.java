package com.helpdeskeditor.application.app.web.views.soporte;

import com.helpdeskeditor.application.app.data.DAO.EstatusDAO;
import com.helpdeskeditor.application.app.data.entity.AreaEntity;
import com.helpdeskeditor.application.app.data.entity.BienEntity;
import com.helpdeskeditor.application.app.data.entity.CatalogoEstatusEntity;
import com.helpdeskeditor.application.app.data.entity.EstatusEntity;
import com.helpdeskeditor.application.app.data.entity.FolioEntity;
import com.helpdeskeditor.application.app.data.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.data.entity.PrioridadEntity;
import com.helpdeskeditor.application.app.data.entity.SolicitudEntity;
import com.helpdeskeditor.application.app.data.entity.UnidadEntity;
import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.helpdeskeditor.application.app.service.AreasService;
import com.helpdeskeditor.application.app.service.BienesService;
import com.helpdeskeditor.application.app.service.CatalogosEstatusService;
import com.helpdeskeditor.application.app.service.EstatusService;
import com.helpdeskeditor.application.app.service.FoliosService;
import com.helpdeskeditor.application.app.service.IncidenciasService;
import com.helpdeskeditor.application.app.service.PrioridadesService;
import com.helpdeskeditor.application.app.service.SolicitudesService;
import com.helpdeskeditor.application.app.service.UnidadesService;
import com.helpdeskeditor.application.app.service.UsuariosSoporteService;
import com.helpdeskeditor.application.app.web.MainLayout;
import com.helpdeskeditor.application.configuration.AuthenticatedUser;
import com.helpdeskeditor.application.util.EmailService;
import com.helpdeskeditor.application.util.UIutils;
import com.helpdeskeditor.application.util.signaturepad.SignaturePad;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinSession;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.security.RolesAllowed;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.helpdeskeditor.application.configuration.DriverManagerDataSource.SQLDataSource;

@PageTitle("Folios")
@Route(value = "folio", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed({"USER","ADMIN"})
@Slf4j
public class FolioView extends VerticalLayout implements HasUrlParameter<String> {
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
            private ComboBox<BienEntity> CB_Bien = new ComboBox<BienEntity>("Bien");
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
            private DatePicker DtePikr_fechaMovimiento = new DatePicker("Fecha Movimiento");
        private Button Btt_AgregarEstatus = new Button("AGREGAR");
        private Button Btt_ModificarAnotacion = new Button("Modificar Anotacion");
        private Grid<EstatusDAO> GridEstatus = new Grid<>(EstatusDAO.class, false);
        //private Button Btt_SalvarEstatus = new Button("GUARDAR");
    private VerticalLayout VL_Firma = new VerticalLayout();
        private FormLayout FL_Firma = new FormLayout();
            private TextField TF_nombreFirma = new TextField("Nombre de quien firma");
            private TextField TF_cargo = new TextField("Cargo");
            private TextField TF_email = new TextField("Email");
            private TextField TF_email2 = new TextField("copia Email");
            private SignaturePad signature = new SignaturePad();

    private Tabs tabs;
        private Tab tabUnidad;
        private Tab tabIncidencia;
        private Tab tabMotivo;
        private Tab tabEstatus;
        private Tab tabFirma;
        private VerticalLayout contenidoTab;

    private UnidadesService unidadesService;
    private AreasService areasService;
    private final FoliosService foliosService;
    private IncidenciasService incidenciasService;
    private IncidenciasService incidenciasServiceFinal;
    private BienesService bienesService;
    private PrioridadesService prioridadesService;
    private final EstatusService estatusService;
    private CatalogosEstatusService catalogosEstatusService;
    private UsuariosSoporteService usuariosSoporteService;
    private AuthenticatedUser authenticatedUser;
    private SolicitudesService solicitudesService;

    @Value("${charLimit}")
    private int charLimit;
    @Value("${app.datasource.jdbc-url}")
    private String url;
    @Value("${app.datasource.username}")
    private String userName;
    @Value("${app.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String className;
    @Value("${server.servlet.context-path}")
    private String context;

    @Autowired
    private static Environment env;
    @Autowired
    private EmailService emailService;

    List<EstatusDAO> estatusEntityList = null;
    EstatusDAO estatusDAO;
    FolioEntity folioEntity = null;
    Dialog dialogProgressBarModificandoFolio = UIutils.dialogPorgressBarIndeterminate("Modificando Folio", "Espere mientras se modifica el folio");

    @Autowired
    private ResourceLoader resourceLoader;

    public FolioView(UnidadesService unidadesService,
                     AreasService areasService,
                     FoliosService foliosService,
                     IncidenciasService incidenciasService,
                     BienesService bienesService,
                     PrioridadesService prioridadesService,
                     EstatusService estatusService,
                     CatalogosEstatusService catalogosEstatusService,
                     UsuariosSoporteService usuariosSoporteService,
                     IncidenciasService incidenciasServiceFinal,
                     AuthenticatedUser authenticatedUser,
                     SolicitudesService solicitudesService) {

        this.unidadesService = unidadesService;
        this.areasService = areasService;
        this.foliosService = foliosService;
        this.incidenciasService = incidenciasService;
        this.bienesService = bienesService;
        this.prioridadesService = prioridadesService;
        this.estatusService = estatusService;
        this.catalogosEstatusService = catalogosEstatusService;
        this.usuariosSoporteService = usuariosSoporteService;
        this.incidenciasServiceFinal = incidenciasServiceFinal;
        this.authenticatedUser = authenticatedUser;
        this.solicitudesService = solicitudesService;

        folioEntity = new FolioEntity();

        layoutMotivo();
        layoutIncidencia();
        layoutUnidad();

        layoutFirma();
        layoutEstatus();

        layoutTabs();

        this.add(tabs, contenidoTab);

        borrar();
    }

    private HorizontalLayout panelCargaSolicitud(){

        IntegerField IF_Solicitud = new IntegerField();
        IF_Solicitud.setLabel("Solicitud");
        IF_Solicitud.setHelperText("Solicitud a cargar");

        Button Btt_CargaSolicitud = new Button("Carga Solicitud");
        Btt_CargaSolicitud.addClickListener( evento ->{
            Optional<SolicitudEntity> solicitudEntity = solicitudesService.findById(IF_Solicitud.getValue());

            if(solicitudEntity.isPresent()){
                SolicitudEntity solicitud = solicitudEntity.get();

                CB_Unidad.setValue(unidadesService.findById(solicitud.getIdUnidad()).get());
                CB_Area.setValue(   areasService.findByIdAndIdUnidad(solicitud.getIdArea(),
                                    unidadesService.findById(solicitud.getIdUnidad()).get().getId()));
                CB_UsuarioReporta.setValue(solicitud.getUsuarioReporta());
                TF_Telefono.setValue(solicitud.getTelefonoContacto());

                CB_Incidencia.setValue(incidenciasService.findById(solicitud.getIdTipoIncidencia()).get());

                CB_Bien.setValue(
                        bienesService.findByIdAndIdTipoIncidencia(
                                solicitud.getIdTipoBien(),
                                incidenciasService.findById(solicitud.getIdTipoIncidencia()).get().getId()
                        )
                );

                CB_Marca.setValue(solicitud.getMarca());
                CB_Modelo.setValue(solicitud.getModelo());

                TF_NumeroSerie.setValue(solicitud.getNumeroSerie());
                TF_NumeroInventario.setValue(solicitud.getNumeroInventario());

                TA_MotivoReporte.setValue(solicitud.getMotivo());

            }
            else{
                UIutils.notificacionERROR("No se encontro la solicitud");
            }
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setMargin(false);
        horizontalLayout.setPadding(false);
        horizontalLayout.setWidthFull();
        horizontalLayout.setVerticalComponentAlignment(Alignment.BASELINE,IF_Solicitud,Btt_CargaSolicitud);

        horizontalLayout.add(IF_Solicitud,Btt_CargaSolicitud);

        return horizontalLayout;
    }

    private void layoutFirma(){
        VL_Firma.setMargin(false);
        VL_Firma.setPadding(false);

        signature.setHeight("300px");
        signature.setBackgroundColor(0, 0, 0, 0);
        signature.setPenColor("#000000");
        signature.setVisible(true);

        Button Btt_borrar = new Button ("Borrar");
        Btt_borrar.addClickListener(e -> {
            signature.clear();
        });

        Button Btt_guardar = new Button ("Guardar");
        Btt_guardar.addClickListener(e -> {
            String nombre = TF_nombreFirma.getValue();
            String cargo = TF_cargo.getValue();
            String email = TF_email.getValue();
            String email2 = TF_email2.getValue();

            byte[] firma = signature.getImageBase64();

            Integer folio = IF_Folio.getValue();

            if(nombre != null && cargo != null && email != null &&
                    folio != null && firma != null){
                if(nombre.length() > 0 && cargo.length() > 0 && email.length() > 0 &&
                        folio > 0 && firma.length > 0){
                    folioEntity = foliosService.findById(folio).get();

                    folioEntity.setNombreFirma(nombre);
                    folioEntity.setCargoFirma(cargo);
                    folioEntity.setEmail(email);
                    folioEntity.setEmail2(email2);
                    folioEntity.setFirma(firma);

                    foliosService.save(folioEntity);

                    UIutils.notificacionSUCCESS("La firma fue guardada en el folio!").open();
                }
                else
                    UIutils.notificacionERROR("Faltan datos!").open();
            }
            else
                UIutils.notificacionERROR("Faltan datos!").open();


        });

        HorizontalLayout buttonLayout = new HorizontalLayout(Btt_borrar,Btt_guardar);

        FL_Firma.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        FL_Firma.setColspan(signature, 2);

        FL_Firma.add(TF_nombreFirma,TF_cargo,TF_email,TF_email2);
        FL_Firma.add(signature);
        FL_Firma.add(buttonLayout);

        VL_Firma.add(FL_Firma);
    }

    private void borrar(){
        IF_Folio.clear();

        CB_Unidad.clear();

        CB_Area.clear();
        CB_Area.setItems();

        CB_UsuarioReporta.clear();

        TF_Telefono.clear();
        TF_ReferenciaDocumental.clear();
        DtePikr_fechaApertura.clear();

        CB_Incidencia.clear();

        CB_Bien.clear();
        CB_Area.setItems();

        CB_Marca.clear();
        CB_Marca.setItems();

        CB_Modelo.clear();
        CB_Modelo.setItems();

        TF_NumeroSerie.clear();
        TF_NumeroInventario.clear();

        TA_MotivoReporte.clear();
        CB_Prioridad.clear();

        CB_Estaus.clear();
        TA_Anotacion.clear();
        CB_SoporteAsignado.clear();
        CB_TipoIncidenciaFinal.clear();

        DtePikr_fechaApertura.setValue(LocalDate.now(ZoneId.systemDefault()));

        TF_nombreFirma.clear();
        TF_cargo.clear();
        TF_email.clear();
        signature.clear();
        signature.setImage(null);

        estatusEntityList = estatusService.findAllDAO(0);
        GridEstatus.setItems(estatusEntityList);

        folioEntity = null;
    }

    private boolean cargarFolio(Integer folio){
        Optional<FolioEntity> OptionalfolioEntity = foliosService.findById(folio);
        if(!OptionalfolioEntity.isEmpty()){
            folioEntity = OptionalfolioEntity.get();

            if(folioEntity.getId() > 0){
                //************************** UNIDAD *************************
                CB_Unidad.setValue(unidadesService.findById(folioEntity.getIdUnidad()).get());
                CB_Area.setValue(areasService.findByIdAndIdUnidad(folioEntity.getIdArea(), unidadesService.findById(folioEntity.getIdUnidad()).get().getId()));
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
                IncidenciaEntity incidenciaEntity = incidenciasService.findById(folioEntity.getIdTipoIncidencia()).get();
                BienEntity bienEntity = bienesService.findByIdAndIdTipoIncidencia(folioEntity.getIdBien(), folioEntity.getIdTipoIncidencia());
                String marca = folioEntity.getMarca();
                String modelo = folioEntity.getModelo();
                String numSerie = folioEntity.getNumeroSerie();
                String numInventario = folioEntity.getNumeroInventario();

                String motivoReporte = folioEntity.getMotivoReporte();
                PrioridadEntity prioridad = prioridadesService.findById(folioEntity.getIdPrioridad()).get();

                estatusEntityList = estatusService.findAllDAO(folioEntity.getId());

                CB_Incidencia.setValue(incidenciaEntity);
                CB_Bien.setValue(bienEntity);
                CB_Marca.setValue(marca);
                CB_Modelo.setValue(modelo);
                TF_NumeroSerie.setValue(numSerie);
                TF_NumeroInventario.setValue(numInventario);

                TA_MotivoReporte.setValue(motivoReporte);
                CB_Prioridad.setValue(prioridad);

                GridEstatus.setItems(estatusEntityList);

                if(folioEntity.getNombreFirma() != null)
                    TF_nombreFirma.setValue(folioEntity.getNombreFirma());

                if(folioEntity.getCargoFirma() != null)
                    TF_cargo.setValue(folioEntity.getCargoFirma());

                if(folioEntity.getEmail() != null)
                    TF_email.setValue(folioEntity.getEmail());

                if(folioEntity.getFirma() != null)
                    signature.setImage(signature.getImagen642URI(folioEntity.getFirma()));
            }
        }
        else{
            UIutils.confirmDialog("Error al cargar el Folio!","El folio no fue encontrado").open();
            borrar();
            folioEntity = null;
        }

        return true;
    }

    private void guardar(){
        UIutils.notificacionNeutral("Guardando Folio!").open();

        if(folioEntity == null)
            folioEntity = new FolioEntity();

        //****************** UNIDAD ******************************************************
        UnidadEntity unidadEntity = CB_Unidad.getValue();
        if(unidadEntity != null)
            folioEntity.setIdUnidad(unidadEntity.getId());
        else
            folioEntity.setIdUnidad(0);

        AreaEntity areaEntity = CB_Area.getValue();
        if(areaEntity != null)
            folioEntity.setIdArea(areaEntity.getId());
        else
            folioEntity.setIdArea(0);

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

        if(DtePikr_fechaApertura.getValue() == null)
            DtePikr_fechaApertura.setValue(LocalDate.now(ZoneId.systemDefault()));

        folioEntity.setFecha(Date.from(DtePikr_fechaApertura.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

        //**************** INCIDENCIA *******************************
        IncidenciaEntity incidenciaEntity = CB_Incidencia.getValue();
        if(incidenciaEntity != null)
            folioEntity.setIdTipoIncidencia(incidenciaEntity.getId());
        else
            folioEntity.setIdTipoIncidencia(0);


        BienEntity bienEntity = CB_Bien.getValue();
        if(bienEntity != null)
            folioEntity.setIdBien(bienEntity.getId());
        else
            folioEntity.setIdBien(0);

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

        folioEntity.setNumeroSerie(valor_str);

        valor_str = TF_NumeroInventario.getValue();
        if(valor_str == null || valor_str.length() == 0)
            valor_str = "NO ESPECIFICADO";

        folioEntity.setNumeroInventario(valor_str);

        //****************** MOTIVO *******************************
        valor_str = TA_MotivoReporte.getValue();
        if(valor_str == null)
            valor_str = "";

        folioEntity.setMotivoReporte(valor_str);

        PrioridadEntity prioridadEntity = CB_Prioridad.getValue();
        if(prioridadEntity != null)
            folioEntity.setIdPrioridad(prioridadEntity.getId());
        else
            folioEntity.setIdPrioridad(0);

        boolean apertura = false;
        if(folioEntity.getId() == null) {
            apertura = true;
            folioEntity.setActivo(true);
            folioEntity.setIdTipoIncidenciaFinal(0);
            folioEntity.setIdUsuarioSoporteAsignado(authenticatedUser.get().get().getId());
        }

        if(folioEntity.getIdUnidad() > 0 && folioEntity.getIdArea() > 0 &&
                folioEntity.getIdTipoIncidencia() > 0 && folioEntity.getIdBien() > 0 &&
                folioEntity.getMotivoReporte().length() > 0 && folioEntity.getIdPrioridad() > 0 &&
                folioEntity.getActivo()){

            folioEntity = foliosService.save(folioEntity);

            if(apertura){
                List<CatalogoEstatusEntity> catalogoEstatusEntityList = catalogosEstatusService.findAll();

                for(CatalogoEstatusEntity catalogoEstatusEntity1 : catalogoEstatusEntityList){
                    if(catalogoEstatusEntity1.getAbrir())
                        agregarEstatus(catalogoEstatusEntity1.getId(),"Apertura de Folio",
                                folioEntity.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                }
            }
            //borrar();
            IF_Folio.setValue(folioEntity.getId());
            UIutils.notificacionSUCCESS("Folio creado/modificado: "+folioEntity.getId()).open();
        }
        else{
            UIutils.notificacionERROR("Error al crear/modificar Folio!").open();

        }
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

        Button Btt_imprimir = new Button ("Imprimir");
        Btt_imprimir.addClickListener(e -> {
            Integer folio = IF_Folio.getValue();

            if(folio != null){
                try {
                    Connection conn = SQLDataSource(className,url,userName,password).getConnection();

                    Map<String, Object> parameters = new HashMap<String, Object>();
                    parameters.put("Folio", IF_Folio.getValue());
                    parameters.put("IdUsuarioSoporte", authenticatedUser.get().get().getId());

                    JasperReport report = JasperCompileManager.compileReport(getClass()
                                                                                        .getClassLoader()
                                                                                        .getResourceAsStream("reportes/HelpDeskRPTIncidencia.jrxml"));

                    JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);

                    byte[] output;// = JasperExportManager.exportReportToPdf(print);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                    JRPdfExporter exporter = new JRPdfExporter();
                    exporter.setExporterInput(new SimpleExporterInput(print));
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                    exporter.exportReport();
                    output = outputStream.toByteArray();

                    StreamResource streamResource = new StreamResource("Folio_"+IF_Folio.getValue()+".pdf", () ->new ByteArrayInputStream(output));
                    streamResource.setContentType("application/pdf");

                    VaadinSession.getCurrent().getResourceRegistry().registerResource(streamResource);

                    getUI().get().getPage().open(VaadinRequest.getCurrent().getHeader("origin")+context+"/"+VaadinSession.getCurrent().getResourceRegistry().getTargetURI(streamResource).getPath(), "_blank");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (JRException ex) {
                    log.info(ex.getMessage());
                    log.info(ex.getMessageKey());
                    throw new RuntimeException(ex);
                } catch (NullPointerException ex) {
                    log.info(ex.getMessage());
                    throw new RuntimeException(ex);
                }
            }
            else
                UIutils.notificacionERROR("No se encontro o folio!").open();
        });

        Button Btt_enviarEmail = new Button ("Enviar por Email");
        Btt_enviarEmail.addClickListener(e -> {
            UIutils.notificacionNeutral("Enviando correo!").open();
            Connection conn = null;
            try {
                conn = SQLDataSource(className,url,userName,password).getConnection();

                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("Folio", IF_Folio.getValue());
                parameters.put("IdUsuarioSoporte", authenticatedUser.get().get().getId());

                JasperPrint print = JasperFillManager.fillReport("C://reportes//HelpDeskRPTIncidencia.jasper", parameters, conn);

                byte[] output = JasperExportManager.exportReportToPdf(print);

                InputStreamSource attachment = new ByteArrayResource(output);

                if(folioEntity.getEmail2() != null){
                    if(folioEntity.getEmail2().length() > 0){
                        emailService.sendWithAttach("ti.indesalud@gmail.com",
                                folioEntity.getEmail()+"",
                                folioEntity.getEmail2()+"",
                                "HelpDesk - Folio de Servicio: "+IF_Folio.getValue().toString(),
                                "Folio de Servicio: "+IF_Folio.getValue().toString(),
                                "Folio de Servicio: "+IF_Folio.getValue().toString()+".pdf",attachment);
                    }
                    else{
                        emailService.sendWithAttach("ti.indesalud@gmail.com",
                                folioEntity.getEmail()+"",
                                "HelpDesk - Folio de Servicio: "+IF_Folio.getValue().toString(),
                                "Folio de Servicio: "+IF_Folio.getValue().toString(),
                                "Folio de Servicio: "+IF_Folio.getValue().toString()+".pdf",attachment);
                    }
                }
                else{
                    emailService.sendWithAttach("ti.indesalud@gmail.com",
                            folioEntity.getEmail()+"",
                            "HelpDesk - Folio de Servicio: "+IF_Folio.getValue().toString(),
                            "Folio de Servicio: "+IF_Folio.getValue().toString(),
                            "Folio de Servicio: "+IF_Folio.getValue().toString()+".pdf",attachment);
                }

                UIutils.notificacionSUCCESS("Correo enviado!").open();

            } catch (SQLException ex) {
                UIutils.notificacionERROR("Error al enviar correo!").open();
                throw new RuntimeException(ex);
            } catch (JRException ex) {
                UIutils.notificacionERROR("Error al enviar correo!").open();
                throw new RuntimeException(ex);
            }
        });

        Button Btt_nuevo = new Button ("Nuevo");
        Btt_nuevo.addClickListener(e -> {
            borrar();
        });

        HL_Folio_BotnoCargar.setVerticalComponentAlignment(Alignment.BASELINE,IF_Folio,B_cargar,Btt_imprimir,Btt_enviarEmail,Btt_nuevo);
        HL_Folio_BotnoCargar.add(IF_Folio,B_cargar,Btt_imprimir,Btt_enviarEmail,Btt_nuevo);

        //TF_Telefono.setAllowedCharPattern("^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$");
        TF_Telefono.setHelperText("Formato:+(123)456-7890");
        TF_Telefono.setLabel("Numero Telefonico");
        TF_Telefono.setWidth("240px");

        CB_Unidad.setItemLabelGenerator(UnidadEntity::getNombre);
        CB_Unidad.setItems(unidadesService.findAll());
        CB_Unidad.addValueChangeListener(e -> {
            if(e.getValue() != null){
                CB_Unidad.setValue(e.getValue());
                CB_Area.setItems(areasService.findByidUnidad(CB_Unidad.getValue().getId()));
            }
        });

        CB_Area.setItemLabelGenerator(AreaEntity::getNombre);
        CB_Area.addValueChangeListener(e -> {
            CB_Area.setValue(e.getValue());
        });

        CB_UsuarioReporta.setItems(foliosService.getAllUsuarioReporta());

        CB_UsuarioReporta.addCustomValueSetListener(e -> {
            List<String> allItems = (List<String>) ((ListDataProvider) CB_UsuarioReporta.getDataProvider()).getItems();
            String customValue = e.getDetail();
            allItems.add(customValue);
            CB_UsuarioReporta.setItems(allItems);
            CB_UsuarioReporta.setValue(customValue);
        });

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

        VL_Unidad.add(  new H5("SOLICITUD"),
                        panelCargaSolicitud(),
                        new H5("FOLIO"),
                        HL_Folio_BotnoCargar,
                        new H5("UNIDAD"),
                        FL_Unidad,UIutils.lineaDivision(),
                        new H5("INCIDENCIA"),
                        FL_Incidencia,UIutils.lineaDivision(),
                        new H5("MOTIVO"),
                        FL_Motivo,Btt_SalvarUnidad);
    }

    private void layoutIncidencia(){
        VL_Incidencia.setMargin(false);
        VL_Incidencia.setPadding(false);

        FL_Incidencia.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        CB_Incidencia.setItems(incidenciasService.findAll());
        CB_Incidencia.setItemLabelGenerator(IncidenciaEntity::getNombre);
        CB_Incidencia.addValueChangeListener(e -> {
            if(e.getValue() != null){

                CB_Bien.setItems(bienesService.findByIdTipoIncidenciaOrderByNombreAsc(CB_Incidencia.getValue().getId()));

                CB_Marca.clear();
                CB_Modelo.clear();
                TF_NumeroSerie.clear();
                TF_NumeroInventario.clear();

            }
        });

        CB_Bien.setItemLabelGenerator(BienEntity::getNombre);
        CB_Bien.addValueChangeListener(e -> {
            if(e.getValue() != null){
                CB_Marca.setItems(foliosService.findMarcaByIdIncidenciaAndIdBien(CB_Incidencia.getValue().getId(),
                                                                                e.getValue().getId()));

                CB_Modelo.clear();
                TF_NumeroSerie.clear();
                TF_NumeroInventario.clear();
            }
        });

        CB_Marca.addValueChangeListener(e -> {
            if (e.getValue() != null && CB_Incidencia.getValue() != null &&
                    CB_Bien.getValue() != null && CB_Marca.getValue() != null) {
                //CB_Marca.setValue(e.getValue());

                CB_Modelo.setItems(foliosService.findModeloByIdIncidenciaAndIdBienAndMarca(
                                    CB_Incidencia.getValue().getId(),
                                    CB_Bien.getValue().getId(),
                                    CB_Marca.getValue()));
                TF_NumeroSerie.clear();
                TF_NumeroInventario.clear();
            }
        });

        CB_Marca.addCustomValueSetListener(e -> {
            List<String> allItems = (List<String>) ((ListDataProvider) CB_Marca.getDataProvider()).getItems();
            String customValue = e.getDetail();
            allItems.add(customValue);
            CB_Marca.setItems(allItems);
            CB_Marca.setValue(customValue);
        });

        CB_Modelo.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                //CB_Modelo.setValue(e.getValue());
                TF_NumeroSerie.clear();
                TF_NumeroInventario.clear();
            }
        });

        CB_Modelo.addCustomValueSetListener(e -> {
            List<String> allItems = (List<String>) ((ListDataProvider) CB_Modelo.getDataProvider()).getItems();
            String customValue = e.getDetail();
            allItems.add(customValue);
            CB_Modelo.setItems(allItems);
            CB_Modelo.setValue(customValue);
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

        //VL_Incidencia.add(FL_Incidencia,Btt_SalvarIncidencia);
    }

    private void layoutMotivo(){
        VL_Motivo.setMargin(false);
        VL_Motivo.setPadding(false);

        FL_Motivo.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        TA_MotivoReporte.setLabel("Description");
        TA_MotivoReporte.setMaxLength(charLimit);
        TA_MotivoReporte.setValueChangeMode(ValueChangeMode.EAGER);
        TA_MotivoReporte.addValueChangeListener(e -> {
            e.getSource().setHelperText(e.getValue().length() + "/" + charLimit);
        });

        CB_Prioridad.setItems(prioridadesService.findAll());
        CB_Prioridad.setItemLabelGenerator(PrioridadEntity::getNombre);

        FL_Motivo.add(TA_MotivoReporte,CB_Prioridad);

        Btt_SalvarMotivo.addClickListener(e -> {
            guardar();
        });
        Btt_SalvarMotivo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        //VL_Motivo.add(FL_Motivo,CB_Prioridad,Btt_SalvarMotivo);
    }

    private void layoutEstatus(){
        VL_Estatus.setMargin(false);
        VL_Estatus.setPadding(false);

        FL_Estatus.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 2));

        CB_Estaus.setItems(catalogosEstatusService.findAll());
        CB_Estaus.setItemLabelGenerator(CatalogoEstatusEntity::getNombre);
        CB_Estaus.addValueChangeListener(e -> {
            if(e.getValue() != null){
                //folioEntity.set(e.getValue().getId());
            }
        });

        CB_SoporteAsignado.setItems(usuariosSoporteService.findByOrderBynombreUsuarioAsc());
        CB_SoporteAsignado.setItemLabelGenerator(UsuarioSoporteEntity::getNombrePropio);

        CB_TipoIncidenciaFinal.setItems(incidenciasServiceFinal.findAll());
        CB_TipoIncidenciaFinal.setItemLabelGenerator(IncidenciaEntity::getNombre);

        TA_Anotacion.setLabel("Anotacion");
        TA_Anotacion.setMaxLength(charLimit);
        TA_Anotacion.setValueChangeMode(ValueChangeMode.EAGER);
        TA_Anotacion.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText(e.getValue().length() + "/" + charLimit);
        });

        Btt_AgregarEstatus.addClickListener(e -> {
            Integer idApertura = 0;
            Integer idDiagnosticoInicial = 0;
            Integer idDiagnosticoFinal = 0;
            Integer idCerrar = 0;
            Integer idReasignar = 0;

            CatalogoEstatusEntity catalogoEstatusEntity = CB_Estaus.getValue();
            String anotacion = TA_Anotacion.getValue();
            if(anotacion == null)
                anotacion = "NO ESPECIFICADO";
            UsuarioSoporteEntity usuarioSoporteEntity = CB_SoporteAsignado.getValue();
            IncidenciaEntity incidenciaEntity = CB_TipoIncidenciaFinal.getValue();
            List<EstatusDAO> estatusEntityList = estatusService.findAllDAO(folioEntity.getId());

            List<CatalogoEstatusEntity> catalogoEstatusEntityList = catalogosEstatusService.findAll();

            for(CatalogoEstatusEntity catalogoEstatusEntity1 : catalogoEstatusEntityList){
                if(catalogoEstatusEntity1.getAbrir())
                    idApertura = catalogoEstatusEntity1.getId();
                else
                    if(catalogoEstatusEntity1.getDiagnostinoInicial())
                        idDiagnosticoInicial = catalogoEstatusEntity1.getId();
                    else
                        if(catalogoEstatusEntity1.getDiagnostinoFinal())
                            idDiagnosticoFinal = catalogoEstatusEntity1.getId();
                        else
                            if(catalogoEstatusEntity1.getReasignar())
                                idReasignar = catalogoEstatusEntity1.getId();
                            else
                                if(catalogoEstatusEntity1.getCerrar())
                                    idCerrar = catalogoEstatusEntity1.getId();
                                else
                                    if(catalogoEstatusEntity1.getReasignar())
                                        idReasignar = catalogoEstatusEntity1.getId();
            }

            Boolean existeApertura = false;
            Boolean existeDiagnosticoInicial = false;
            Boolean existeDiagnosticoFinal = false;
            Boolean existeListaParaEntrega = false;
            Boolean existeCerrar = false;

            for(EstatusDAO estatusDAO : estatusEntityList){
                if(estatusDAO.getIdEstatus() == idApertura)
                    existeApertura = true;
                else
                    if(estatusDAO.getIdEstatus() == idDiagnosticoInicial)
                        existeDiagnosticoInicial = true;
                    else
                        if(estatusDAO.getIdEstatus() == idDiagnosticoFinal)
                            existeDiagnosticoFinal = true;
                        else
                            if(estatusDAO.getIdEstatus() == idCerrar)
                                existeCerrar = true;
            }

            if(catalogoEstatusEntity.getId() == idApertura){
                if(!existeApertura && !existeDiagnosticoInicial && !existeDiagnosticoFinal && !existeCerrar){
                    agregarEstatus(catalogoEstatusEntity.getId(), anotacion,DtePikr_fechaMovimiento.getValue());
                }
                else
                    UIutils.confirmDialog("Error en Estatus","El estatus ya existe o falta su correlativo anterior").open();
            }
            else{
                if(catalogoEstatusEntity.getId() == idDiagnosticoInicial){
                    if(existeApertura && !existeDiagnosticoInicial && !existeDiagnosticoFinal && !existeCerrar && anotacion.length() > 0){
                        agregarEstatus(catalogoEstatusEntity.getId(), anotacion,DtePikr_fechaMovimiento.getValue());
                    }
                    else
                        UIutils.confirmDialog("Error en Estatus","El estatus ya existe, falta su correlativo anterior o falta la anotacion").open();
                }
                else{
                    if(catalogoEstatusEntity.getId() == idDiagnosticoFinal){
                        if(existeApertura && existeDiagnosticoInicial && !existeDiagnosticoFinal && !existeCerrar && anotacion.length() > 0){
                            agregarEstatus(catalogoEstatusEntity.getId(), anotacion,DtePikr_fechaMovimiento.getValue());

                        }
                        else
                            UIutils.confirmDialog("Error en Estatus","El estatus ya existe o falta su correlativo anterior").open();
                    }
                    else{
                        if(catalogoEstatusEntity.getId() == idCerrar){
                            if(existeApertura && existeDiagnosticoInicial && existeDiagnosticoFinal && !existeCerrar && anotacion.length() > 0){
                                agregarEstatus(catalogoEstatusEntity.getId(), anotacion,DtePikr_fechaMovimiento.getValue());
                                folioEntity.setActivo(false);
                                foliosService.save(folioEntity);
                            }
                            else
                                UIutils.confirmDialog("Error en Estatus","El estatus ya existe o falta su correlativo anterior").open();
                        }
                        else{
                            if(catalogoEstatusEntity.getId() == idReasignar){
                                if(existeApertura && !existeCerrar){
                                    folioEntity.setIdUsuarioSoporteAsignado(usuarioSoporteEntity.getId());
                                    folioEntity = foliosService.save(folioEntity);

                                    agregarEstatus(catalogoEstatusEntity.getId(), "Nuevo usuario de soporte asignado"+usuarioSoporteEntity.getNombrePropio(),DtePikr_fechaMovimiento.getValue());
                                }
                                else
                                    UIutils.confirmDialog("Error en Estatus","El estatus ya existe o falta su correlativo anterior").open();
                            }
                            else{
                                if(existeApertura && !existeCerrar && anotacion.length() > 0){
                                    agregarEstatus(catalogoEstatusEntity.getId(), anotacion,DtePikr_fechaMovimiento.getValue());
                                }
                                else
                                    UIutils.confirmDialog("Error en Estatus","El folio no tiene apertura o ya esta cerrado").open();
                            }
                        }
                    }
                }
            }
        });

        GridEstatus.addColumn(EstatusDAO::getNombre).setHeader("Estatus").setResizable(true);//.setAutoWidth(true);
        GridEstatus.addColumn(EstatusDAO::getAnotacion).setHeader("Anotacion").setResizable(true);//.setAutoWidth(true);
        GridEstatus.addColumn(EstatusDAO::getNombrePropio).setHeader("Usuario").setResizable(true);
        GridEstatus.addColumn(EstatusDAO::getFecha).setHeader("Fecha").setTextAlign(ColumnTextAlign.CENTER).setAutoWidth(true).setFlexGrow(0);

        GridEstatus.addCellFocusListener(event -> {
            estatusDAO = event.getItem().get();
            TA_Anotacion.setValue(estatusDAO.getAnotacion());
        });

        Btt_ModificarAnotacion.addClickListener(e -> {
            if(TA_Anotacion.getValue() != null){
                EstatusEntity estatusEntity = estatusService.findById(estatusDAO.getId()).get();
                estatusEntity.setAnotacion(TA_Anotacion.getValue());
                estatusService.save(estatusEntity);

                estatusEntityList = estatusService.findAllDAO(folioEntity.getId());
                GridEstatus.setItems(estatusEntityList);
                TA_Anotacion.clear();
            }
        });

        GridEstatus.addColumn(
                new ComponentRenderer<>(Button::new, (button, estatus) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> {
                        Integer idApertura = 0;
                        Integer idDiagnosticoInicial = 0;
                        Integer idDiagnosticoFinal = 0;
                        Integer idCerrar = 0;
                        Integer idReasignar = 0;

                        List<CatalogoEstatusEntity> catalogoEstatusEntityList = catalogosEstatusService.findAll();

                        for(CatalogoEstatusEntity catalogoEstatusEntity1 : catalogoEstatusEntityList){
                            if(catalogoEstatusEntity1.getAbrir())
                                idApertura = catalogoEstatusEntity1.getId();
                            else
                            if(catalogoEstatusEntity1.getDiagnostinoInicial())
                                idDiagnosticoInicial = catalogoEstatusEntity1.getId();
                            else
                            if(catalogoEstatusEntity1.getDiagnostinoFinal())
                                idDiagnosticoFinal = catalogoEstatusEntity1.getId();
                            else
                            if(catalogoEstatusEntity1.getReasignar())
                                idReasignar = catalogoEstatusEntity1.getId();
                            else
                            if(catalogoEstatusEntity1.getCerrar())
                                idCerrar = catalogoEstatusEntity1.getId();
                            else
                            if(catalogoEstatusEntity1.getReasignar())
                                idReasignar = catalogoEstatusEntity1.getId();
                        }

                        Boolean existeApertura = false;
                        Boolean existeDiagnosticoInicial = false;
                        Boolean existeDiagnosticoFinal = false;
                        Boolean existeListaParaEntrega = false;
                        Boolean existeCerrar = false;

                        List<EstatusDAO> estatusEntityList = estatusService.findAllDAO(folioEntity.getId());

                        for(EstatusDAO estatusDAO : estatusEntityList){
                            if(estatusDAO.getIdEstatus() == idApertura)
                                existeApertura = true;
                            else
                            if(estatusDAO.getIdEstatus() == idDiagnosticoInicial)
                                existeDiagnosticoInicial = true;
                            else
                            if(estatusDAO.getIdEstatus() == idDiagnosticoFinal)
                                existeDiagnosticoFinal = true;
                            else
                            if(estatusDAO.getIdEstatus() == idCerrar)
                                existeCerrar = true;
                        }

                        if(estatus.getIdEstatus() == idApertura){
                            if(!existeDiagnosticoInicial && !existeDiagnosticoFinal && !existeListaParaEntrega && !existeCerrar){
                                EstatusEntity estatusEntity = estatus.getEntity();
                                estatusService.delete(estatusEntity);
                                estatusEntityList = estatusService.findAllDAO(folioEntity.getId());
                                GridEstatus.setItems(estatusEntityList);
                            }
                            else
                                UIutils.confirmDialog("Error al borrar estatus!","El estatus tiene estatus consecutivo)").open();
                        }
                        else{
                            if(estatus.getIdEstatus() == idDiagnosticoInicial){
                                if(!existeDiagnosticoFinal && !existeListaParaEntrega && !existeCerrar){
                                    EstatusEntity estatusEntity = estatus.getEntity();
                                    estatusService.delete(estatusEntity);
                                    estatusEntityList = estatusService.findAllDAO(folioEntity.getId());
                                    GridEstatus.setItems(estatusEntityList);
                                }
                                else
                                    UIutils.confirmDialog("Error al borrar estatus!","El estatus tiene estatus consecutivo)").open();
                            }
                            else{
                                if(estatus.getIdEstatus() == idDiagnosticoFinal){
                                    if(!existeListaParaEntrega && !existeCerrar){
                                        EstatusEntity estatusEntity = estatus.getEntity();
                                        estatusService.delete(estatusEntity);
                                        estatusEntityList = estatusService.findAllDAO(folioEntity.getId());
                                        GridEstatus.setItems(estatusEntityList);
                                    }
                                    else
                                        UIutils.confirmDialog("Error al borrar estatus!","El estatus tiene estatus consecutivo)").open();
                                }
                                else{
                                    if(estatus.getIdEstatus() == idCerrar){
                                        folioEntity.setActivo(true);
                                        foliosService.save(folioEntity);
                                    }
                                    EstatusEntity estatusEntity = estatus.getEntity();
                                    estatusService.delete(estatusEntity);
                                    estatusEntityList = estatusService.findAllDAO(folioEntity.getId());
                                    GridEstatus.setItems(estatusEntityList);
                                }
                            }
                        }
                    });
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })
        ).setHeader("Eliminar").setTextAlign(ColumnTextAlign.CENTER).setAutoWidth(true).setFlexGrow(0);

        GridEstatus.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        GridEstatus.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        GridEstatus.setAllRowsVisible(true);

        FL_Estatus.add(CB_Estaus,TA_Anotacion,CB_SoporteAsignado,CB_TipoIncidenciaFinal,DtePikr_fechaMovimiento);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(Btt_AgregarEstatus,Btt_ModificarAnotacion);

        VL_Estatus.add(FL_Estatus,horizontalLayout,GridEstatus);//,UIutils.lineaDivision(),
                        //new H5("FIRMA"),FL_Firma);//,Btt_SalvarEstatus);
    }

    private void agregarEstatus(Integer idEstatus, String anotacion, LocalDate fecha){
        EstatusEntity estatusEntity = new EstatusEntity();
        estatusEntity.setFolio(folioEntity.getId());
        estatusEntity.setFecha(fecha);
        estatusEntity.setIdEstatus(idEstatus);
        estatusEntity.setAnotacion(anotacion);
        estatusEntity.setIdUsuario(authenticatedUser.get().get().getId());

        estatusService.save(estatusEntity);

        List<EstatusDAO> estatusEntityList = estatusService.findAllDAO(folioEntity.getId());
        GridEstatus.setItems(estatusEntityList);

        CB_Estaus.clear();
        TA_Anotacion.clear();
        CB_SoporteAsignado.clear();
        CB_TipoIncidenciaFinal.clear();
    }

    private void layoutTabs(){
        tabUnidad = new Tab("FOLIO");
        //tabIncidencia = new Tab("INCIDENCIA");
        //tabMotivo = new Tab("MOTIVO");
        tabEstatus = new Tab("ESTATUS");
        tabFirma = new Tab("FIRMA");

        tabs = new Tabs(tabUnidad,tabEstatus,tabFirma);

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
            if (tab.equals(tabEstatus))
                contenidoTab.add(VL_Estatus);
            else
                if (tab.equals(tabFirma))
                    contenidoTab.add(VL_Firma);

    }

    @Override
    public void setParameter(BeforeEvent event, @WildcardParameter String parameter) {
        if (!parameter.isEmpty()) {
            cargarFolio(Integer.parseInt(parameter));
            IF_Folio.setValue(Integer.parseInt(parameter));
        }
    }

}
