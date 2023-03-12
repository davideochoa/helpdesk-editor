package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.data.entity.AreaEntity;
import com.helpdeskeditor.application.app.data.entity.BienEntity;
import com.helpdeskeditor.application.app.data.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.data.entity.UnidadEntity;
import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.helpdeskeditor.application.app.service.AreaService;
import com.helpdeskeditor.application.app.service.BienService;
import com.helpdeskeditor.application.app.service.IncidenciaService;
import com.helpdeskeditor.application.app.service.UnidadService;
import com.helpdeskeditor.application.app.service.UsuarioSoporteService;
import com.helpdeskeditor.application.configuration.SecurityConfiguration;
import com.helpdeskeditor.application.util.UIutils;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;

@PageTitle("Catalogos")
@Route(value = "catalogos", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed("ADMIN")
@Slf4j
public class CatalogosView extends VerticalLayout{

    private Tabs tabs;
    private Tab tabUsuario;
    private Tab tabUnidadArea;
    private Tab tabIncideciaBien;
    private VerticalLayout contenidoTab;

    private VerticalLayout VL_CatalogoUsuairios = new VerticalLayout();
        private FormLayout FL_principal = new FormLayout();
            private ComboBox<UsuarioSoporteEntity> CB_usuario = new ComboBox<UsuarioSoporteEntity>("Nombre Usuario");
            private TextField TF_userName = new TextField("UserName");
            private ComboBox<String> CB_tipoUsuario = new ComboBox<String>("Tipo Usuario");
            private Checkbox CKB_resetPassword = new Checkbox("ResetConstraseña");

        private Button Btt_SalvarCatalogoUsuario = new Button("GUARDAR");
        private HorizontalLayout VL_separador = new HorizontalLayout();
        private FormLayout FL_principal2 = new FormLayout();
            private TextField TF_nuevoUsuario = new TextField("Nombre Usuario");
            private TextField TF_nuevoUserName = new TextField("UserName");
        private Button Btt_salvarNuevoUsuario = new Button("GUARDAR");

    private VerticalLayout VL_CatalogoUnidadArea = new VerticalLayout();
        private FormLayout FL_principalCatalogoUnidad = new FormLayout();
            private ComboBox<UnidadEntity> CB_unidad = new ComboBox<UnidadEntity>("Unidad");
            private TextField TF_nombreUnidad = new TextField("Nueva/Modificar Unidad");
        private HorizontalLayout HL_botonesUnidad = new HorizontalLayout();
            private Button Btt_salvarUnidad = new Button("GUARDAR");
            private Button Btt_limpiarUnidad = new Button("LIMPIAR");
        private FormLayout FL_principalCatalogoArea = new FormLayout();
            private ComboBox<AreaEntity> CB_area = new ComboBox<AreaEntity>("Area");
            private TextField TF_nombreArea = new TextField("Nueva/Modificar Area");
        private HorizontalLayout HL_botonesArea = new HorizontalLayout();
            private Button Btt_salvarArea = new Button("GUARDAR");
            private Button Btt_limpiarArea = new Button("LIMPIAR");

    private VerticalLayout VL_CatalogoIncidenciaBien = new VerticalLayout();
        private FormLayout FL_principalIncidencia = new FormLayout();
            private ComboBox<IncidenciaEntity> CB_incidencia = new ComboBox<IncidenciaEntity>("Unidad");
            private TextField TF_nombreIncidencia = new TextField("Nueva/Modificar Incidencia");
        private HorizontalLayout HL_botonesIncidencia = new HorizontalLayout();
            private Button Btt_salvarIncidencia = new Button("GUARDAR");
            private Button Btt_limpiarIncidencia = new Button("LIMPIAR");
        private FormLayout FL_principalCatalogoBien = new FormLayout();
            private ComboBox<BienEntity> CB_Bien = new ComboBox<BienEntity>("Area");
            private TextField TF_nombreBien = new TextField("Nueva/Modificar Bien");
        private HorizontalLayout HL_botonesBien = new HorizontalLayout();
            private Button Btt_salvarBien = new Button("GUARDAR");
            private Button Btt_limpiarBien = new Button("LIMPIAR");

    private UsuarioSoporteService usuarioSoporteService;
    private UnidadService unidadService;
    private AreaService areaService;
    private IncidenciaService incidenciaService;
    private BienService bienService;

    private UsuarioSoporteEntity usuarioSoporteEntity;

    @Autowired
    SecurityConfiguration securityConfiguration;

    public CatalogosView(UsuarioSoporteService usuarioSoporteService,
                         UnidadService unidadService,
                         AreaService areaService,
                         IncidenciaService incidenciaService,
                         BienService bienService) {

        this.usuarioSoporteService = usuarioSoporteService;
        this.unidadService = unidadService;
        this.areaService = areaService;
        this.incidenciaService = incidenciaService;
        this.bienService = bienService;

        layoutCatalogousuario();
        layoutCatalogoUnidadArea();
        layoutCatalogoIncidenciaBien();

        this.setHeight("100%");

        layoutTabs();

        this.add(tabs, contenidoTab);
    }

    private void limpiarlayoutCatalogoIncidenciaBien(){
        TF_nombreIncidencia.clear();
        TF_nombreBien.clear();

        TF_nombreIncidencia.setValue("");
        TF_nombreBien.setValue("");

        CB_incidencia.clear();
        CB_Bien.clear();

        CB_incidencia.setItems(incidenciaService.findAll());
    }

    private void layoutCatalogoIncidenciaBien(){
        VL_CatalogoIncidenciaBien.setMargin(false);
        VL_CatalogoIncidenciaBien.setPadding(false);

        FL_principalIncidencia.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        CB_incidencia.setItemLabelGenerator(IncidenciaEntity::getNombre);
        CB_incidencia.setItems(incidenciaService.findAll());
        CB_incidencia.addValueChangeListener(e ->{
            if(e != null){
                TF_nombreIncidencia.setValue(e.getValue().getNombre());
            }
        });

        FL_principalIncidencia.add(CB_incidencia,TF_nombreIncidencia);

        Btt_salvarIncidencia.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Btt_salvarIncidencia.addClickListener(e -> {
            IncidenciaEntity incidenciaEntity = CB_incidencia.getValue();
            String nombreIncidencia = TF_nombreIncidencia.getValue();

            if(incidenciaEntity != null){
                if(nombreIncidencia != null){
                    if(!incidenciaEntity.getNombre().equals(nombreIncidencia) && nombreIncidencia.length() > 0){
                        incidenciaEntity.setNombre(nombreIncidencia.toUpperCase());
                        incidenciaService.save(incidenciaEntity);

                        limpiarlayoutCatalogoIncidenciaBien();

                        UIutils.notificacionSUCCESS("Se agrego el nombre de la Incidencia").open();
                    }
                }
            }
            else{
                if(nombreIncidencia != null){
                    if(nombreIncidencia.length() > 0){
                        IncidenciaEntity newIncidenciaEntity = new IncidenciaEntity();
                        newIncidenciaEntity.setNombre(nombreIncidencia.toUpperCase());
                        incidenciaService.save(incidenciaEntity);

                        limpiarlayoutCatalogoIncidenciaBien();

                        UIutils.notificacionSUCCESS("Se modifico el nombre de la Incidencia").open();
                    }
                }
            }

        });

        Btt_limpiarIncidencia.addClickListener(e -> {
            limpiarlayoutCatalogoIncidenciaBien();
        });

        HL_botonesIncidencia.add(Btt_salvarIncidencia,Btt_limpiarIncidencia);

        VL_CatalogoIncidenciaBien.add(new H3("INCIDENCIA"), FL_principalIncidencia,HL_botonesIncidencia,UIutils.lineaDivision());

        FL_principalCatalogoBien.add(CB_Bien,TF_nombreBien);

        Btt_salvarBien.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HL_botonesBien.add(Btt_salvarBien,Btt_limpiarBien);

        VL_CatalogoIncidenciaBien.add(new H3("BIEN"), FL_principalCatalogoBien,HL_botonesBien);
    }

    private void limpiarlayoutCatalogoUnidadArea(){
        TF_nombreUnidad.clear();
        TF_nombreArea.clear();

        TF_nombreUnidad.setValue("");
        TF_nombreArea.setValue("");

        CB_unidad.clear();
        CB_area.clear();

        CB_unidad.setItems(unidadService.findAll());
    }

    private void layoutCatalogoUnidadArea(){
        VL_CatalogoUnidadArea.setMargin(false);
        VL_CatalogoUnidadArea.setPadding(false);

        FL_principalCatalogoUnidad.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        CB_unidad.setItems(unidadService.findAll());
        CB_unidad.setItemLabelGenerator(UnidadEntity::getNombre);
        CB_unidad.addValueChangeListener(e -> {
            if(e.getValue() != null){
                CB_unidad.setValue(e.getValue());
                CB_area.setItems(areaService.findByidUnidad(CB_unidad.getValue().getId()));
                TF_nombreUnidad.setValue(CB_unidad.getValue().getNombre());
            }
        });

        FL_principalCatalogoUnidad.add(CB_unidad,TF_nombreUnidad);

        Btt_salvarUnidad.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Btt_salvarUnidad.addClickListener(e -> {
            UnidadEntity unidadEntity = CB_unidad.getValue();
            String nombreUnidad = TF_nombreUnidad.getValue();

            if(unidadEntity != null){
                if(nombreUnidad != null){
                    if(!unidadEntity.getNombre().equals(nombreUnidad) && nombreUnidad.length() > 0){
                        unidadEntity.setNombre(nombreUnidad.toUpperCase());

                        unidadEntity = unidadService.save(unidadEntity);

                        limpiarlayoutCatalogoUnidadArea();
                        UIutils.notificacionSUCCESS("Se modifico el nombre de la Unidad").open();
                    }
                    else
                        UIutils.notificacionERROR("Se debe escribir el nuevo nombre de la Unidad").open();
                }
                else
                    UIutils.notificacionERROR("Se debe escribir el nuevo nombre de la Unidad").open();
            }
            else{
                if(nombreUnidad != null){
                    if(nombreUnidad.length() > 0){
                        unidadEntity = new UnidadEntity();
                        unidadEntity.setNombre(nombreUnidad.toUpperCase());

                        unidadEntity = unidadService.save(unidadEntity);

                        limpiarlayoutCatalogoUnidadArea();
                        UIutils.notificacionSUCCESS("Se agrega el nombre de la Unidad").open();
                    }
                    else
                        UIutils.notificacionERROR("Se debe escribir el nuevo nombre de la Unidad").open();
                }
                else
                    UIutils.notificacionERROR("Se debe escribir el nuevo nombre de la Unidad").open();
            }

        });

        Btt_limpiarUnidad.addClickListener(e ->{
            limpiarlayoutCatalogoUnidadArea();
        });

        HL_botonesUnidad.add(Btt_salvarUnidad,Btt_limpiarUnidad);

        VL_CatalogoUnidadArea.add(new H3("UNIDAD"), FL_principalCatalogoUnidad,HL_botonesUnidad,UIutils.lineaDivision());

        CB_area.setItemLabelGenerator(AreaEntity::getNombre);
        CB_area.addValueChangeListener(e -> {
            if(CB_area.getValue() != null){
                TF_nombreArea.setValue(CB_area.getValue().getNombre());
            }
        });

        FL_principalCatalogoArea.add(CB_area,TF_nombreArea);

        Btt_salvarArea.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Btt_salvarArea.addClickListener(e ->{
            AreaEntity areaEntity = CB_area.getValue();
            String nombreArea = TF_nombreArea.getValue();

            if(areaEntity != null){
                if(nombreArea != null){
                    if(!areaEntity.getNombre().equals(nombreArea) && nombreArea.length() > 0){
                        areaEntity.setNombre(nombreArea.toUpperCase());
                        areaEntity.setIdUnidad(CB_unidad.getValue().getId());
                        areaEntity = areaService.save(areaEntity);

                        limpiarlayoutCatalogoUnidadArea();
                        UIutils.notificacionSUCCESS("Se modifico el nombre del Area").open();
                    }
                    else
                        UIutils.notificacionERROR("Se debe escribir el nuevo nombre de la Unidad").open();
                }
                else
                    UIutils.notificacionERROR("Se debe escribir el nuevo nombre de la Unidad").open();
            }
            else{
                if(nombreArea != null){
                    if(nombreArea.length() > 0){
                        areaEntity = new AreaEntity();
                        areaEntity.setNombre(nombreArea.toUpperCase());
                        areaEntity.setIdUnidad(CB_unidad.getValue().getId());

                        areaEntity = areaService.save(areaEntity);

                        limpiarlayoutCatalogoUnidadArea();
                        UIutils.notificacionSUCCESS("Se agrega el nombre del Area").open();
                    }
                    else
                        UIutils.notificacionERROR("Se debe escribir el nuevo nombre del Area").open();
                }
                else
                    UIutils.notificacionERROR("Se debe escribir el nuevo nombre del Area").open();
            }
        });

        Btt_limpiarArea.addClickListener(e ->{
            limpiarlayoutCatalogoUnidadArea();
        });

        HL_botonesArea.add(Btt_salvarArea,Btt_limpiarArea);

        VL_CatalogoUnidadArea.add(new H3("AREA"), FL_principalCatalogoArea,HL_botonesArea);
    }

    private void limpiarLayoutCatalogoUsuario(){
        CB_usuario.clear();
        TF_userName.clear();
        CB_tipoUsuario.clear();
        CKB_resetPassword.clear();

        CB_usuario.setItems(usuarioSoporteService.findByOrderBynombreUsuarioAsc());

        TF_nuevoUsuario.clear();
        TF_nuevoUserName.clear();
    }

    private void layoutCatalogousuario(){
        VL_CatalogoUsuairios.setMargin(false);
        VL_CatalogoUsuairios.setPadding(false);

        FL_principal.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        CB_usuario.setItems(usuarioSoporteService.findByOrderBynombreUsuarioAsc());
        CB_usuario.setItemLabelGenerator(UsuarioSoporteEntity::getNombrePropio);
        CB_usuario.addValueChangeListener(e -> {
            if(e.getValue() != null){
                usuarioSoporteEntity = e.getValue();
                TF_userName.setValue(usuarioSoporteEntity.getNombreUsuario());
                CB_tipoUsuario.setValue(usuarioSoporteEntity.getRol());
                CKB_resetPassword.setValue(usuarioSoporteEntity.getEsReseteadoPassword());
            }
        });

        CB_tipoUsuario.setItems("ADMIN","USER");

        Btt_SalvarCatalogoUsuario.addClickListener(e -> {
            usuarioSoporteEntity = CB_usuario.getValue();
            if(usuarioSoporteEntity != null){
                if(TF_userName.getValue() != null) {
                    if(!usuarioSoporteEntity.getNombreUsuario().equals(TF_userName.getValue())){
                        if(!verificarExisteUsername(TF_userName.getValue())){
                            usuarioSoporteEntity.setNombreUsuario(TF_userName.getValue());
                            usuarioSoporteEntity.setPassword(securityConfiguration.passwordEncoder().encode(TF_userName.getValue()));
                            usuarioSoporteEntity.setEsReseteadoPassword(true);

                            usuarioSoporteService.save(usuarioSoporteEntity);
                            limpiarLayoutCatalogoUsuario();
                            UIutils.notificacionSUCCESS("El userName fue modificado").open();
                        }
                        else
                            UIutils.notificacionERROR("El userName ya existe").open();
                    }
                    else{
                        if(CKB_resetPassword.getValue()) {
                            usuarioSoporteEntity.setEsReseteadoPassword(true);
                            usuarioSoporteEntity.setPassword(securityConfiguration.passwordEncoder().encode(usuarioSoporteEntity.getNombreUsuario()));

                            usuarioSoporteService.save(usuarioSoporteEntity);
                            limpiarLayoutCatalogoUsuario();
                            UIutils.notificacionSUCCESS("El usuario fue seteado a teclear una nueva contraseña").open();
                        }
                        if(CB_tipoUsuario.getValue() != null)
                            if(!usuarioSoporteEntity.getRol().equals(CB_tipoUsuario.getValue())) {
                                usuarioSoporteEntity.setRol(CB_tipoUsuario.getValue());

                                usuarioSoporteService.save(usuarioSoporteEntity);
                                limpiarLayoutCatalogoUsuario();
                                UIutils.notificacionSUCCESS("Se cambio el tipo de usuario").open();
                            }
                    }
                }
            }
        });

        Btt_SalvarCatalogoUsuario.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Btt_salvarNuevoUsuario.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        FL_principal.add(CB_usuario,TF_userName,CB_tipoUsuario,CKB_resetPassword);

        VL_CatalogoUsuairios.add(new H3("Reset Datos"),FL_principal,Btt_SalvarCatalogoUsuario, UIutils.lineaDivision());

        FL_principal2.add(TF_nuevoUsuario,TF_nuevoUserName);

        Btt_salvarNuevoUsuario.addClickListener(e -> {
            String nombrePropio = TF_nuevoUsuario.getValue();
            String userName = TF_nuevoUserName.getValue();

            if(nombrePropio != null && userName != null){
                if(!verificarExisteUsername(TF_userName.getValue())){
                    usuarioSoporteEntity = new UsuarioSoporteEntity();
                    usuarioSoporteEntity.setNombrePropio(nombrePropio.toUpperCase());
                    usuarioSoporteEntity.setNombreUsuario(userName);
                    usuarioSoporteEntity.setPassword(securityConfiguration.passwordEncoder().encode(userName));
                    usuarioSoporteEntity.setEsReseteadoPassword(true);
                    usuarioSoporteEntity.setRol("USER");

                    usuarioSoporteEntity.setCorreo("NO ESPECIFICAOO");
                    usuarioSoporteEntity.setEsAdministrador(false);

                    usuarioSoporteService.save(usuarioSoporteEntity);

                    UIutils.notificacionSUCCESS("Se creo el nuevo usuario").open();

                    limpiarLayoutCatalogoUsuario();
                }
                else
                    UIutils.notificacionERROR("El userName ya existe").open();
            }
            else
                UIutils.notificacionERROR("Falta el nombre propio o userName").open();
        });

        VL_CatalogoUsuairios.add(new H3("Nuevo Usuario"),FL_principal2,Btt_salvarNuevoUsuario);
    }

    private Boolean verificarExisteUsername(String userName){
        UsuarioSoporteEntity usuario = usuarioSoporteService.findByNombreUsuario(userName);

        if(usuario != null)
            return true;
        else
            return false;
    }

    private void layoutTabs(){
        tabUsuario = new Tab("USUARIO");
        tabUnidadArea = new Tab("UNIDAD - AREA");
        tabIncideciaBien = new Tab("INCIDENCIA - BIEN");

        tabs = new Tabs(tabUsuario, tabUnidadArea,tabIncideciaBien);

        tabs.addSelectedChangeListener(event -> setContent(event.getSelectedTab()));

        contenidoTab = new VerticalLayout();
        contenidoTab.setSpacing(false);
        setContent(tabs.getSelectedTab());
    }

    private void setContent(Tab tab) {
        contenidoTab.removeAll();

        if (tab.equals(tabUsuario))
            contenidoTab.add(VL_CatalogoUsuairios);
        else
            if (tab.equals(tabUnidadArea))
                contenidoTab.add(VL_CatalogoUnidadArea);
            else
                if (tab.equals(tabIncideciaBien))
                    contenidoTab.add(VL_CatalogoIncidenciaBien);
    }
}
