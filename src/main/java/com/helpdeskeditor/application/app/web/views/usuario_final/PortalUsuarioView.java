package com.helpdeskeditor.application.app.web.views.usuario_final;

import com.helpdeskeditor.application.app.data.DAO.SolicitudDAO;
import com.helpdeskeditor.application.app.data.entity.AreaEntity;
import com.helpdeskeditor.application.app.data.entity.BienEntity;
import com.helpdeskeditor.application.app.data.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.data.entity.SolicitudEntity;
import com.helpdeskeditor.application.app.data.entity.UnidadEntity;
import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.helpdeskeditor.application.app.data.repository.SolicitudesRepository;
import com.helpdeskeditor.application.app.service.AreasService;
import com.helpdeskeditor.application.app.service.BienesService;
import com.helpdeskeditor.application.app.service.FoliosService;
import com.helpdeskeditor.application.app.service.IncidenciasService;
import com.helpdeskeditor.application.app.service.SolicitudesService;
import com.helpdeskeditor.application.app.service.UnidadesService;
import com.helpdeskeditor.application.app.web.MainLayout;
import com.helpdeskeditor.application.configuration.AuthenticatedUser;
import com.helpdeskeditor.application.util.UIutils;
import com.helpdeskeditor.application.util.UIutils.PanelPaginacion;
import com.helpdeskeditor.application.util.signaturepad.SignaturePad;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoIcon;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Slf4j
@PageTitle("Portal de Soporte a Usuarios")
@Route(value = "portal-usuario", layout = MainLayout.class)
//@RouteAlias(value = "/portal-usuario", layout = MainLayout.class)
//@AnonymousAllowed
@RolesAllowed("PORTAL")
public class PortalUsuarioView extends VerticalLayout {

    private Tab tabSolicitud = new Tab("SOLICITUD");
    private Tab tabHistorial = new Tab("HISTORIAL");
    private Tab tabDatosTitular = new Tab("DATOS DE TITULAR DE LA UNIDAD");
    private Tabs tabs = new Tabs(tabSolicitud, tabHistorial,tabDatosTitular);
    private VerticalLayout contenidoTab = new VerticalLayout();;

    private TextField TF_Unidad = new TextField("Unidad");
    private TextField TF_Cargo = new TextField("Cargo");
    private TextField TF_InicialesTitulo = new TextField("Iniciales Titulo");
    private TextField TF_Nombre = new TextField("Nombre Completo");
    private SignaturePad SP_Firma = new SignaturePad();

    private Select<AreaEntity> CB_Area = new Select<>();
    private Select<IncidenciaEntity> CB_TipoIncidencia = new Select<>();
    private Select<BienEntity> CB_TipoBien = new Select<>();
    private ComboBox<String> CB_Marca = new ComboBox<>("Marca");
    private ComboBox<String> CB_Modelo = new ComboBox<>("Modelo");
    private ComboBox<String> CB_NumeroSerie = new ComboBox<>("Numero Serie");
    private ComboBox<String> CB_NumeroInventaro = new ComboBox<>("Numero Inventario");
    private TextArea TA_Motivo = new TextArea("Motivo");

    private AuthenticatedUser authenticatedUser;
    private UnidadesService unidadesService;
    private AreasService areasService;
    private IncidenciasService incidenciasService;
    private BienesService bienesService;
    private FoliosService foliosService;
    private UnidadEntity unidadEntity;
    private UsuarioSoporteEntity usuarioSoporte;

    private SolicitudesService solicitudesService;

    public PortalUsuarioView(AuthenticatedUser authenticatedUser,
                             UnidadesService unidadesService,
                             AreasService areasService,
                             IncidenciasService incidenciasService,
                             BienesService bienesService,
                             FoliosService foliosService,
                             SolicitudesService solicitudesService){

        this.authenticatedUser = authenticatedUser;
        this.unidadesService = unidadesService;
        this.areasService = areasService;
        this.incidenciasService = incidenciasService;
        this.bienesService = bienesService;
        this.foliosService = foliosService;
        this.solicitudesService = solicitudesService;

        usuarioSoporte = authenticatedUser.get().get();

        unidadEntity = unidadesService.findById(usuarioSoporte.getIdUnidad()).get();

        layoutTabs();

        this.add(tabs, contenidoTab);


    }

    private void limpiarLayoutSolicitud(){
        CB_Area.clear();
        CB_TipoIncidencia.clear();
        CB_TipoBien.clear();
        CB_Marca.clear();
        CB_Modelo.clear();
        CB_NumeroSerie.clear();
        CB_NumeroInventaro.clear();
        TA_Motivo.clear();
    }

    private VerticalLayout layoutDatosSolicitud(){

        CB_Area.setItems(areasService.findByidUnidad(unidadEntity.getId()));

        CB_TipoIncidencia.setItems(incidenciasService.findAll());

        FormLayout FL_principal = new FormLayout();
        FormLayout FL_principal2 = new FormLayout();

        Button Btt_generarSolcitud = new Button("GENERAR SOLICITUD");
        Btt_generarSolcitud.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Btt_generarSolcitud.addClickListener(e -> {
            AreaEntity areaEntity = CB_Area.getValue();
            IncidenciaEntity incidenciaEntity = CB_TipoIncidencia.getValue();
            BienEntity bienEntity = CB_TipoBien.getValue();
            String marca = CB_Marca.getValue();
            String modelo = CB_Modelo.getValue();
            String numeroSerie = CB_NumeroSerie.getValue();
            String numeroInventaro = CB_NumeroInventaro.getValue();
            String motivo = TA_Motivo.getValue();

            if(marca == null)
                marca = "NO ESPECIFICADO";

            if(modelo == null)
                modelo = "NO ESPECIFICADO";

            if(numeroSerie == null)
                numeroSerie = "NO ESPECIFICADO";

            if(numeroInventaro == null)
                numeroInventaro = "NO ESPECIFICADO";

            if(areaEntity != null && incidenciaEntity != null && bienEntity != null && motivo != null && motivo.length() > 0){

                if(marca.length() == 0)
                    marca = "NO ESPECIFICADO";

                if(modelo.length() == 0)
                    modelo = "NO ESPECIFICADO";

                if(numeroSerie.length() == 0)
                    numeroSerie = "NO ESPECIFICADO";

                if(numeroInventaro.length() == 0)
                    numeroInventaro = "NO ESPECIFICADO";

                SolicitudEntity solicitudEntity = new SolicitudEntity();
                solicitudEntity.setIdUnidad(unidadEntity.getId());
                solicitudEntity.setIdArea(areaEntity.getId());
                solicitudEntity.setIdTipoIncidencia(incidenciaEntity.getId());
                solicitudEntity.setIdTipoBien(bienEntity.getId());
                solicitudEntity.setMarca(marca);
                solicitudEntity.setModelo(modelo);
                solicitudEntity.setNumeroSerie(numeroSerie);
                solicitudEntity.setNumeroInventario(numeroInventaro);
                solicitudEntity.setMotivo(motivo);

                solicitudEntity = solicitudesService.save(solicitudEntity);

                if(solicitudEntity.getId() > 0) {
                    UIutils.notificacionSUCCESS("Se agrego la solicitud").open();
                    limpiarLayoutSolicitud();
                }
                else
                    UIutils.notificacionERROR("No se puede generar la solicitud, verifique los datos, o contacte a soporte por telefono").open();
            }
            else
                UIutils.notificacionERROR("No se puede generar la solicitud faltan datos").open();
        });

        Button Btt_cancelar = new Button("CANCELAR");
        Btt_cancelar.addClickListener(e -> {
            limpiarLayoutSolicitud();
        });

        HorizontalLayout layoutBotones = new HorizontalLayout();
        layoutBotones.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        layoutBotones.add(Btt_generarSolcitud,Btt_cancelar);

        FL_principal.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        FL_principal2.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        FL_principal2.setColspan(TA_Motivo, 2);

        CB_Area.setItemLabelGenerator(AreaEntity::getNombre);
        CB_Area.setLabel("Area");
        CB_Area.addValueChangeListener(e ->{});

        CB_TipoIncidencia.setItemLabelGenerator(IncidenciaEntity::getNombre);
        CB_TipoIncidencia.setLabel("Tipo Incidencia");
        CB_TipoIncidencia.addValueChangeListener(e ->{
            if(e.getValue() != null){
                CB_TipoBien.setItems(bienesService.findByIdTipoIncidenciaOrderByNombreAsc(e.getValue().getId()));

                CB_Marca.clear();
                CB_Modelo.clear();
                CB_NumeroSerie.clear();
                CB_NumeroInventaro.clear();
            }
        });

        CB_TipoBien.setItemLabelGenerator(BienEntity::getNombre);
        CB_TipoBien.setLabel("Tipo Bien");
        CB_TipoBien.addValueChangeListener(e ->{
            if(e.getValue() != null){
                CB_Marca.setItems(foliosService.findMarcaByIdIncidenciaAndIdBien(
                        CB_TipoIncidencia.getValue().getId(),
                        e.getValue().getId()));

                CB_Marca.clear();
                CB_Modelo.clear();
                CB_NumeroSerie.clear();
                CB_NumeroInventaro.clear();
            }
        });

        CB_Marca.addValueChangeListener(e ->{
            if (e.getValue() != null) {
                CB_Modelo.setItems(foliosService.findModeloByIdIncidenciaAndIdBienAndMarca(
                        CB_TipoIncidencia.getValue().getId(),
                        CB_TipoBien.getValue().getId(),
                        CB_Marca.getValue()));

                CB_Modelo.clear();
                CB_NumeroSerie.clear();
                CB_NumeroInventaro.clear();
            }
        });

        CB_Marca.addCustomValueSetListener(e -> {
            List<String> allItems = (List<String>) ((ListDataProvider) CB_Marca.getDataProvider()).getItems();
            String customValue = e.getDetail();
            allItems.add(customValue);
            CB_Marca.setItems(allItems);
            CB_Marca.setValue(customValue);

            CB_Modelo.clear();
            CB_NumeroSerie.clear();
            CB_NumeroInventaro.clear();
        });

        CB_Modelo.addValueChangeListener(e ->{
            if (e.getValue() != null) {
                CB_NumeroSerie.setItems(foliosService.findSerieByIdIncidenciaAndIdBienAndMarcaAndModelo(
                        CB_TipoIncidencia.getValue().getId(),
                        CB_TipoBien.getValue().getId(),
                        CB_Marca.getValue(),
                        CB_Modelo.getValue()
                ));

                CB_NumeroSerie.clear();
                CB_NumeroInventaro.clear();
            }
        });

        CB_Modelo.addCustomValueSetListener(e -> {
            List<String> allItems = (List<String>) ((ListDataProvider) CB_Modelo.getDataProvider()).getItems();
            String customValue = e.getDetail();
            allItems.add(customValue);
            CB_Modelo.setItems(allItems);
            CB_Modelo.setValue(customValue);

            CB_NumeroSerie.clear();
            CB_NumeroInventaro.clear();
        });

        CB_NumeroSerie.addValueChangeListener(e ->{
            if (e.getValue() != null) {
                CB_NumeroInventaro.setItems(foliosService.findSerieByIdIncidenciaAndIdBienAndMarcaAndModeloAndNumeroSerie(
                        CB_TipoIncidencia.getValue().getId(),
                        CB_TipoBien.getValue().getId(),
                        CB_Marca.getValue(),
                        CB_Modelo.getValue(),
                        CB_NumeroSerie.getValue()
                ));

                CB_NumeroInventaro.clear();
            }
        });

        CB_NumeroSerie.addCustomValueSetListener(e -> {
            List<String> allItems = (List<String>) ((ListDataProvider) CB_NumeroSerie.getDataProvider()).getItems();
            String customValue = e.getDetail();
            allItems.add(customValue);
            CB_NumeroSerie.setItems(allItems);
            CB_NumeroSerie.setValue(customValue);

            CB_NumeroInventaro.clear();
        });

        CB_NumeroInventaro.addCustomValueSetListener(e -> {
            List<String> allItems = (List<String>) ((ListDataProvider) CB_NumeroInventaro.getDataProvider()).getItems();
            String customValue = e.getDetail();
            allItems.add(customValue);
            CB_NumeroInventaro.setItems(allItems);
            CB_NumeroInventaro.setValue(customValue);
        });


        FL_principal.add(CB_Area);

        FL_principal2.add(CB_TipoIncidencia);
        FL_principal2.add(CB_TipoBien);
        FL_principal2.add(CB_Marca);
        FL_principal2.add(CB_Modelo);
        FL_principal2.add(CB_NumeroSerie);
        FL_principal2.add(CB_NumeroInventaro);
        FL_principal2.add(TA_Motivo);

        return new VerticalLayout(FL_principal,FL_principal2,layoutBotones);
    }

    private VerticalLayout layoutHistorial(){
        VerticalLayout VL_Principal = new VerticalLayout();

        FormLayout FL_principal = new FormLayout();

        FL_principal.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 1));

        Grid<SolicitudDAO> grid = new Grid<>(SolicitudDAO.class, false);
        grid.addColumn(SolicitudDAO::getId).setHeader("Solicitud");
        grid.addColumn(SolicitudDAO::getArea).setHeader("Area");
        grid.addColumn(SolicitudDAO::getTipoBien).setHeader("Bien");
        grid.addColumn(SolicitudDAO::getMarca).setHeader("Marca");
        grid.addColumn(SolicitudDAO::getModelo).setHeader("Modelo");
        grid.addColumn(SolicitudDAO::getNumeroSerie).setHeader("Serie");
        grid.addColumn(SolicitudDAO::getNumeroInventario).setHeader("Inventario");
        grid.addColumn(SolicitudDAO::getMotivo).setHeader("Motivo");
        grid.addColumn(SolicitudDAO::getMotivo).setHeader("Estatus");
        grid.addColumn(SolicitudDAO::getMotivo).setHeader("Hoja Servicio");
        grid.addColumn(SolicitudDAO::getMotivo).setHeader("Oficio Baja");

        grid.setItems(solicitudesService.findAllByIdUnidad(unidadEntity.getId()));


        PanelPaginacion panelPaginacion = new PanelPaginacion();



        FL_principal.add(grid);

        VL_Principal.add(grid,panelPaginacion);

        return VL_Principal;
    }

    private VerticalLayout layoutDatosUnidad(){

        FormLayout FL_principal = new FormLayout();

            HorizontalLayout HL_TextoFirma = new HorizontalLayout(new H5("FIRMA"));

            Button Btt_borrarFirma = new Button ("Borrar Firma");
            HorizontalLayout HL_BotonBorrarFirma = new HorizontalLayout(Btt_borrarFirma);

            Button Btt_grabar = new Button("GRABAR DATOS");
            HorizontalLayout HL_BotonGrabar = new HorizontalLayout(Btt_grabar);

        FL_principal.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        FL_principal.setColspan(SP_Firma, 2);

        TF_Unidad.setReadOnly(true);

        TF_Cargo.setPlaceholder("Cargo como titular");
        TF_Cargo.setClearButtonVisible(true);

        TF_InicialesTitulo.setPlaceholder("Iniciales titulo academico o ciudadano (C.,DR., Lic.)");
        TF_InicialesTitulo.setClearButtonVisible(true);

        TF_Nombre.setPlaceholder("Nombre(s) Apellido Paterno Apellido Materno");
        TF_Nombre.setClearButtonVisible(true);

        SP_Firma.setHeight("300px");
        SP_Firma.setBackgroundColor(0, 0, 0, 0);
        SP_Firma.setPenColor("#000000");
        SP_Firma.setVisible(true);

        FL_principal.add(TF_Unidad,TF_Cargo,TF_InicialesTitulo,TF_Nombre,HL_TextoFirma,SP_Firma);

        Btt_borrarFirma.addClickListener(e -> {
            SP_Firma.clear();
            SP_Firma.setImage(null);
        });

        Btt_grabar.addClickListener(e -> {
            String cargoTitular = TF_Cargo.getValue();
            String inicialesTitular = TF_InicialesTitulo.getValue();
            String nombreTitular = TF_Nombre.getValue();
            byte[] firma = SP_Firma.getImageBase64();

            if(cargoTitular != null && inicialesTitular != null && nombreTitular != null){
                if(cargoTitular.length() > 0 && inicialesTitular.length() > 0  && nombreTitular.length() > 0 && firma.length > 0){
                    unidadEntity.setCargoTitular(cargoTitular.toUpperCase());
                    unidadEntity.setInicialesTitular(inicialesTitular.toUpperCase());
                    unidadEntity.setNombreTitular(nombreTitular.toUpperCase());
                    unidadEntity.setFirmaTitular(firma);

                    unidadEntity = unidadesService.save(unidadEntity);

                    if(unidadEntity.getId() > 0)
                        UIutils.notificacionSUCCESS("Los datos se guardaron con exito").open();
                    else
                        UIutils.notificacionERROR("No se realizo el guardado de los datos").open();

                }
            }

        });

        cargarDatosUnidad();

        return new VerticalLayout(FL_principal,HL_BotonBorrarFirma,HL_BotonGrabar);
    }

    private void cargarDatosUnidad(){
        unidadEntity = unidadesService.findById(usuarioSoporte.getIdUnidad()).get();

        TF_Unidad.setValue(unidadEntity.getNombre());

        if(unidadEntity.getCargoTitular() != null)
            TF_Cargo.setValue(unidadEntity.getCargoTitular());

        if(unidadEntity.getInicialesTitular() != null)
            TF_InicialesTitulo.setValue(unidadEntity.getInicialesTitular());

        if(unidadEntity.getNombreTitular() != null)
            TF_Nombre.setValue(unidadEntity.getNombreTitular());

        if(unidadEntity.getFirmaTitular() != null)
            SP_Firma.setImage(SP_Firma.getImagen642URI(unidadEntity.getFirmaTitular()));


        List<AreaEntity> areaEntityList = areasService.findByidUnidad(unidadEntity.getId());

    }

    private void layoutTabs(){

        tabs.addSelectedChangeListener(event -> setContent(event.getSelectedTab()));

        contenidoTab.setSpacing(false);

        setContent(tabs.getSelectedTab());

    }

    private void setContent(Tab tab) {

        contenidoTab.removeAll();

        if (tab.equals(tabSolicitud))
            contenidoTab.add(layoutDatosSolicitud());
        else
            if (tab.equals(tabHistorial))
                contenidoTab.add(layoutHistorial());
            else
                if (tab.equals(tabDatosTitular))
                    contenidoTab.add(layoutDatosUnidad());


    }

}
