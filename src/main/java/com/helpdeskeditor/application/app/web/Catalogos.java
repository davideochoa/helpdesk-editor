package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.helpdeskeditor.application.app.service.UsuarioSoporteService;
import com.helpdeskeditor.application.util.UIutils;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
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
public class Catalogos extends VerticalLayout{

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
    private VerticalLayout VL_CatalogoIncidenciaBien = new VerticalLayout();

    private UsuarioSoporteService usuarioSoporteService;

    private UsuarioSoporteEntity usuarioSoporteEntity;

    public Catalogos(UsuarioSoporteService usuarioSoporteService) {
        this.usuarioSoporteService = usuarioSoporteService;

        layoutCatalogousuario();

        this.setHeight("100%");

        layoutTabs();

        this.add(tabs, contenidoTab);
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
                        usuarioSoporteEntity.setNombreUsuario(TF_userName.getValue());
                        usuarioSoporteEntity.setEsReseteadoPassword(true);
                    }
                    else {
                        usuarioSoporteEntity.setEsReseteadoPassword(CKB_resetPassword.getValue());
                    }
                    usuarioSoporteEntity.setRol(CB_tipoUsuario.getValue());


                    log.info(usuarioSoporteEntity.toString());

                    usuarioSoporteService.save(usuarioSoporteEntity);

                    CB_usuario.clear();
                    TF_userName.clear();
                    CB_tipoUsuario.clear();
                    CKB_resetPassword.clear();

                    CB_usuario.setItems(usuarioSoporteService.findByOrderBynombreUsuarioAsc());
                }
            }
        });


        FL_principal.add(CB_usuario,TF_userName,CB_tipoUsuario,CKB_resetPassword);

        VL_CatalogoUsuairios.add(new H3("Reset Datos"),FL_principal,Btt_SalvarCatalogoUsuario, UIutils.lineaDivision());

        FL_principal2.add(TF_nuevoUsuario,TF_nuevoUserName);

        VL_CatalogoUsuairios.add(new H3("Nuevo Usuario"),FL_principal2,Btt_salvarNuevoUsuario);
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
