package com.helpdeskeditor.application.app.web.views.soporte;

import com.helpdeskeditor.application.app.data.entity.AreaEntity;
import com.helpdeskeditor.application.app.data.entity.BienEntity;
import com.helpdeskeditor.application.app.data.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.data.entity.UnidadEntity;
import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.helpdeskeditor.application.app.service.AreasService;
import com.helpdeskeditor.application.app.service.BienesService;
import com.helpdeskeditor.application.app.service.IncidenciasService;
import com.helpdeskeditor.application.app.service.UnidadesService;
import com.helpdeskeditor.application.app.service.UsuariosSoporteService;
import com.helpdeskeditor.application.app.web.MainLayout;
import com.helpdeskeditor.application.configuration.SecurityConfiguration;
import com.helpdeskeditor.application.util.UIutils;
import com.helpdeskeditor.application.util.signaturepad.SignaturePad;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
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
@RolesAllowed("ADMIN")
@Slf4j
public class CatalogosView extends VerticalLayout{

    private Tabs tabs;
    private Tab tabUsuario;
    private Tab tabUnidadArea;
    private Tab tabIncideciaBien;
    private VerticalLayout contenidoTab;

    private String nuevoUsuario;

    private VerticalLayout VL_CatalogoUsuairios = new VerticalLayout();
        private FormLayout FL_principal = new FormLayout();
            private ComboBox<UsuarioSoporteEntity> CB_usuario = new ComboBox<UsuarioSoporteEntity>("Nombre Usuario");
            private TextField TF_userName = new TextField("UserName");
            private Select<String> CB_tipoUsuario = new Select<String>();
            private Checkbox CKB_resetPassword = new Checkbox("ResetConstraseña");

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

    SignaturePad signature = new SignaturePad();

    private UsuariosSoporteService usuariosSoporteService;
    private UnidadesService unidadesService;
    private AreasService areasService;
    private IncidenciasService incidenciasService;
    private BienesService bienesService;

    private UsuarioSoporteEntity usuarioSoporteEntity;

    @Autowired
    SecurityConfiguration securityConfiguration;

    public CatalogosView(UsuariosSoporteService usuariosSoporteService,
                         UnidadesService unidadesService,
                         AreasService areasService,
                         IncidenciasService incidenciasService,
                         BienesService bienesService) {

        this.usuariosSoporteService = usuariosSoporteService;
        this.unidadesService = unidadesService;
        this.areasService = areasService;
        this.incidenciasService = incidenciasService;
        this.bienesService = bienesService;

        //layoutCatalogousuario();
        layoutCatalogousuario2();
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

        CB_incidencia.setItems(incidenciasService.findAll());
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
        CB_incidencia.setItems(incidenciasService.findAll());
        CB_incidencia.addValueChangeListener(e ->{
            if(e != null){
                IncidenciaEntity incidenciaEntity = e.getValue();
                if(incidenciaEntity != null) {
                    TF_nombreIncidencia.setValue(e.getValue().getNombre());
                    CB_Bien.setItems(bienesService.findByIdTipoIncidenciaOrderByNombreAsc(incidenciaEntity.getId()));
                }
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
                        incidenciasService.save(incidenciaEntity);

                        limpiarlayoutCatalogoIncidenciaBien();

                        UIutils.notificacionSUCCESS("Se modifico el nombre de la Incidencia").open();
                    }
                    else
                        UIutils.notificacionERROR("Se debe escribir el nombre de la Incidencia").open();
                }
                else
                    UIutils.notificacionERROR("Se debe escribir el nombre de la Incidencia").open();
            }
            else{
                if(nombreIncidencia != null){
                    if(nombreIncidencia.length() > 0){
                        IncidenciaEntity newIncidenciaEntity = new IncidenciaEntity();
                        newIncidenciaEntity.setNombre(nombreIncidencia.toUpperCase());
                        incidenciasService.save(newIncidenciaEntity);

                        limpiarlayoutCatalogoIncidenciaBien();

                        UIutils.notificacionSUCCESS("Se agrego el nombre de la Incidencia").open();
                    }
                    else
                        UIutils.notificacionERROR("Se debe escribir el nombre de la Incidencia").open();
                }
                else
                    UIutils.notificacionERROR("Se debe escribir el nombre de la Incidencia").open();
            }

        });

        Btt_limpiarIncidencia.addClickListener(e -> {
            limpiarlayoutCatalogoIncidenciaBien();
        });

        HL_botonesIncidencia.add(Btt_salvarIncidencia,Btt_limpiarIncidencia);

        VL_CatalogoIncidenciaBien.add(new H3("INCIDENCIA"), FL_principalIncidencia,HL_botonesIncidencia,UIutils.lineaDivision());

        CB_Bien.setItemLabelGenerator(BienEntity::getNombre);
        CB_Bien.addValueChangeListener(e ->{
            if(e != null){
                BienEntity bienEntity = e.getValue();
                if(bienEntity != null){
                    TF_nombreBien.setValue(bienEntity.getNombre());
                }
            }
        });

        FL_principalCatalogoBien.add(CB_Bien,TF_nombreBien);

        Btt_salvarBien.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Btt_salvarBien.addClickListener(e -> {
            IncidenciaEntity incidenciaEntity = CB_incidencia.getValue();
            BienEntity bienEntity = CB_Bien.getValue();
            String nombreBienValue = TF_nombreBien.getValue();

            if(bienEntity != null){
                if(nombreBienValue != null){
                    if(!bienEntity.getNombre().equals(nombreBienValue)){
                        if(nombreBienValue.length() > 0){
                            bienEntity.setNombre(nombreBienValue.toUpperCase());
                            bienesService.save(bienEntity);

                            limpiarlayoutCatalogoIncidenciaBien();

                            UIutils.notificacionSUCCESS("Se modifico el nombre del Bien").open();
                        }
                        else
                            UIutils.notificacionERROR("Se debe escribir el nombre del Bien").open();
                    }
                    else
                        UIutils.notificacionERROR("Se debe escribir el nombre del Bien diferente").open();
                }
                else
                    UIutils.notificacionERROR("Se debe escribir el nombre del Bien").open();
            }
            else{
                if(nombreBienValue != null){
                    if(nombreBienValue.length() > 0){
                        BienEntity bienEntitynuevo = new BienEntity();
                        bienEntitynuevo.setIdTipoIncidencia(CB_incidencia.getValue().getId());
                        bienEntitynuevo.setNombre(nombreBienValue.toUpperCase());
                        bienesService.save(bienEntitynuevo);

                        limpiarlayoutCatalogoIncidenciaBien();

                        UIutils.notificacionSUCCESS("Se agrega el nombre del Bien").open();
                    }
                }
            }

        });

        Btt_limpiarBien.addClickListener(e -> {
            limpiarlayoutCatalogoIncidenciaBien();
        });

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

        CB_unidad.setItems(unidadesService.findAll());
    }

    private void layoutCatalogoUnidadArea(){
        VL_CatalogoUnidadArea.setMargin(false);
        VL_CatalogoUnidadArea.setPadding(false);

        FL_principalCatalogoUnidad.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        CB_unidad.setItems(unidadesService.findAll());
        CB_unidad.setItemLabelGenerator(UnidadEntity::getNombre);
        CB_unidad.addValueChangeListener(e -> {
            if(e.getValue() != null){
                CB_unidad.setValue(e.getValue());
                CB_area.setItems(areasService.findByidUnidad(CB_unidad.getValue().getId()));
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

                        unidadEntity = unidadesService.save(unidadEntity);

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

                        unidadEntity = unidadesService.save(unidadEntity);

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
                        areaEntity = areasService.save(areaEntity);

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

                        areaEntity = areasService.save(areaEntity);

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

    private void limpiarLayoutCatalogoUsuario2(){
        CB_usuario.clear();
        TF_userName.clear();
        CB_tipoUsuario.clear();
        CKB_resetPassword.clear();
        signature.setImage(null);
        signature.clear();
    }

    private void layoutCatalogousuario2(){

        Button Btt_cancelar_limpiar = new Button("CANCELAR / LIMPIAR");
        Button Btt_guardar = new Button("GUARDAR");

        VL_CatalogoUsuairios.setMargin(false);
        VL_CatalogoUsuairios.setPadding(false);

        FL_principal.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        signature.setHeight("300px");
        signature.setBackgroundColor(0, 0, 0, 0);
        signature.setPenColor("#000000");
        signature.setVisible(true);

        CB_usuario.setItems(usuariosSoporteService.findByOrderBynombreUsuarioAsc());
        CB_usuario.setItemLabelGenerator(UsuarioSoporteEntity::getNombrePropio);
        CB_usuario.addValueChangeListener(e -> {

            usuarioSoporteEntity = e.getValue();

            if(usuarioSoporteEntity != null){
                TF_userName.setValue(usuarioSoporteEntity.getNombreUsuario());
                CB_tipoUsuario.setValue(usuarioSoporteEntity.getRol());
                CKB_resetPassword.setValue(usuarioSoporteEntity.getEsReseteadoPassword());

                if(usuarioSoporteEntity.getFirma() != null ){
                    signature.setImage(signature.getImagen642URI(usuarioSoporteEntity.getFirma()));
                }
                else {
                    signature.setImage(null);
                    signature.clear();
                }
            }

        });
        CB_usuario.addCustomValueSetListener(e -> {
            if(e.getDetail() != null){
                nuevoUsuario = new String();
                nuevoUsuario = e.getDetail();

                TF_userName.clear();
                CB_tipoUsuario.clear();
                CKB_resetPassword.clear();
                signature.clear();
                signature.setImage(null);

                usuarioSoporteEntity = null;
            }
            else{
                nuevoUsuario = null;
            }


        });

        CB_tipoUsuario.setLabel("Tipo Usuario");
        CB_tipoUsuario.setItems("ADMIN","USER","PORTAL");

        Button Btt_borrarFirma = new Button ("Borrar Firma");
        Btt_borrarFirma.addClickListener(e -> {
            signature.clear();
            signature.setImage(null);
        });

        HorizontalLayout buttonLayoutBorrarFirma = new HorizontalLayout(Btt_borrarFirma);

        Btt_cancelar_limpiar.addClickListener(e -> {
            limpiarLayoutCatalogoUsuario2();
        });

        Btt_guardar.addClickListener(e -> {
            String nombrePropio;
            if(nuevoUsuario != null)
                nombrePropio = nuevoUsuario.toUpperCase();
            else
                nombrePropio = usuarioSoporteEntity.getNombrePropio().toUpperCase();

            String userName = TF_userName.getValue();
            byte[] firma = signature.getImageBase64();
            boolean resetPassword = CKB_resetPassword.getValue();
            String rol = CB_tipoUsuario.getValue();

            if(nombrePropio != null && userName != null && firma.length > 0){
                if(usuarioSoporteEntity != null){
                    usuarioSoporteEntity.setNombreUsuario(userName);
                    usuarioSoporteEntity.setEsReseteadoPassword(resetPassword);
                    usuarioSoporteEntity.setRol(rol);

                    if(resetPassword)
                        usuarioSoporteEntity.setPassword(securityConfiguration.passwordEncoder().encode(userName));

                    usuariosSoporteService.save(usuarioSoporteEntity);

                    UIutils.notificacionSUCCESS("Se modifico el usuario").open();
                }
                else{
                    usuarioSoporteEntity = new UsuarioSoporteEntity();
                    usuarioSoporteEntity.setNombrePropio(nombrePropio);
                    usuarioSoporteEntity.setNombreUsuario(userName);
                    usuarioSoporteEntity.setPassword(securityConfiguration.passwordEncoder().encode(userName));
                    usuarioSoporteEntity.setEsReseteadoPassword(true);
                    usuarioSoporteEntity.setRol("USER");
                    usuarioSoporteEntity.setFirma(firma);
                    usuarioSoporteEntity.setCorreo("NO ESPECIFICAOO");
                    usuarioSoporteEntity.setEsAdministrador(false);

                    usuarioSoporteEntity = usuariosSoporteService.save(usuarioSoporteEntity);

                    if(usuarioSoporteEntity.getId() > 0)
                        UIutils.notificacionSUCCESS("Se agrego el usuario").open();
                    else
                        UIutils.notificacionERROR("Hubo algun problema, No se agrego el usuario").open();
                }

            }

            limpiarLayoutCatalogoUsuario2();
        });

        HorizontalLayout buttonLayoutCancelar_Grabar = new HorizontalLayout();
        buttonLayoutCancelar_Grabar.add(Btt_cancelar_limpiar,Btt_guardar);

        FL_principal.setColspan(signature, 2);
        FL_principal.add(CB_usuario,TF_userName,CB_tipoUsuario,CKB_resetPassword,signature);

        VL_CatalogoUsuairios.add(FL_principal,buttonLayoutBorrarFirma,buttonLayoutCancelar_Grabar);

    }

    private Boolean verificarExisteUsername(String userName){
        log.info("userName:"+userName);
        UsuarioSoporteEntity usuario = usuariosSoporteService.findByNombreUsuario(userName);

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
