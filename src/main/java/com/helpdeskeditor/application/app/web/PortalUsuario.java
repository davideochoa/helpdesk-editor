package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.util.signaturepad.SignaturePad;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.RolesAllowed;
import java.net.URISyntaxException;

@Slf4j
@PageTitle("Portal de Soporte a Usuarios")
@Route(value = "portal-usuario", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
//@AnonymousAllowed
@RolesAllowed("PORTAL")
public class PortalUsuario extends VerticalLayout {
    private Tabs tabs;
    private Tab tabSolicitud;
    private Tab tabHistorial;
    private Tab tabDatosTitular;
    private VerticalLayout contenidoTab;

    private VerticalLayout VL_Solicitud = new VerticalLayout();
    private VerticalLayout VL_Historial = new VerticalLayout();
    private VerticalLayout VL_DatosTitular = new VerticalLayout();

    public PortalUsuario(){
        layoutTabs();

        VL_DatosTitular = layoutDatosUnidad();

        this.add(tabs, contenidoTab);
    }

    private VerticalLayout layoutDatosUnidad(){
        VerticalLayout VL_Principal = new VerticalLayout();

        FormLayout FL_principal = new FormLayout();
            TextField TF_Unidad = new TextField("Unidad");
            TextField TF_Cargo = new TextField("Cargo");
            TextField TF_InicialesTitulo = new TextField("Iniciales Titulo");
            TextField TF_Nombre = new TextField("Nombre Completo");
            HorizontalLayout HL_TextoFirma = new HorizontalLayout(new H5("FIRMA"));
            SignaturePad SP_Firma = new SignaturePad();

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

        VL_Principal.add(FL_principal,HL_BotonBorrarFirma,HL_BotonGrabar);

        Btt_borrarFirma.addClickListener(e -> {
            SP_Firma.clear();
            SP_Firma.setImage(null);
        });

        Btt_grabar.addClickListener(e -> {

        });

        return VL_Principal;
    }

    private void layoutTabs(){
        tabSolicitud = new Tab("SOLICITUD");
        tabHistorial = new Tab("HISTORIAL");
        tabDatosTitular = new Tab("DATOS DE TITULAR DE LA UNIDAD");

        tabs = new Tabs(tabSolicitud, tabHistorial,tabDatosTitular);

        tabs.addSelectedChangeListener(event -> setContent(event.getSelectedTab()));

        contenidoTab = new VerticalLayout();
        contenidoTab.setSpacing(false);
        setContent(tabs.getSelectedTab());
    }

    private void setContent(Tab tab) {
        contenidoTab.removeAll();

        if (tab.equals(tabSolicitud))
            contenidoTab.add(VL_Solicitud);
        else
        if (tab.equals(tabHistorial))
            contenidoTab.add(VL_Historial);
        else
        if (tab.equals(tabDatosTitular))
            contenidoTab.add(VL_DatosTitular);
    }

}
