package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Catalogos")
@Route(value = "catalogos", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class Catalogos extends VerticalLayout{

    private Tabs tabs;
    private Tab tabUsuario;
    private Tab tabUnidadArea;
    private Tab tabIncideciaBien;
    private VerticalLayout contenidoTab;

    private VerticalLayout VL_CatalogoUsuairios = new VerticalLayout();
        private FormLayout FL_principal = new FormLayout();
            private ComboBox<UsuarioSoporteEntity> CB_usuario = new ComboBox<UsuarioSoporteEntity>("Usuario");
            private TextField TF_userName = new TextField("User Name");
            private Checkbox CKB_resetPassword = new Checkbox("ResetConstraseña");
        private Button Btt_SalvarCatalogoUsuario = new Button("GUARDAR");
    private HorizontalLayout VL_separador = new HorizontalLayout();


    private VerticalLayout VL_CatalogoUnidadArea = new VerticalLayout();
    private VerticalLayout VL_CatalogoIncidenciaBien = new VerticalLayout();

    public Catalogos() {
        layoutCatalogousuario();

        this.setHeight("100%");

        layoutTabs();

        this.add(tabs, contenidoTab);
    }

    public class Divider extends Span {
        public Divider(){
            getStyle().set("background-image", "linear-gradient(135deg, #777 , rgba(0, 0, 0, 0))");
            getStyle().set("flex", "0 0 2px");
            getStyle().set("align-self", "stretch");
        }
    }

    private void layoutCatalogousuario(){
        VL_CatalogoUsuairios.setMargin(false);
        VL_CatalogoUsuairios.setPadding(false);

        Label label = new Label("Language <span style='color: red'>*</span>");


        FL_principal.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));



        FL_principal.add(CB_usuario,TF_userName,CKB_resetPassword);

        VL_CatalogoUsuairios.add(label,FL_principal,Btt_SalvarCatalogoUsuario,new Divider());
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
