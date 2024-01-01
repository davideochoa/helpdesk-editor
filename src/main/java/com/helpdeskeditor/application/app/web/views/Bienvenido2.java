package com.helpdeskeditor.application.app.web.views;

import com.helpdeskeditor.application.app.web.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.net.URISyntaxException;

@Slf4j
@PageTitle("Bienvenido2")
@Route(value = "Bienvenido2", layout = MainLayout.class)
@AnonymousAllowed
//@RolesAllowed("ADMINS")
public class Bienvenido2 extends VerticalLayout {

    @Value("${server.servlet.context-path}")
    private String context;

    public Bienvenido2() throws URISyntaxException {

        setWidth("100%");
        setWidthFull();
        getStyle().set("flex-grow", "1");
        setJustifyContentMode(JustifyContentMode.START);
        setAlignItems(Alignment.CENTER);
        setPadding(false);

        VerticalLayout VL_Encabeado = new VerticalLayout();
        VL_Encabeado.setSpacing(false);
        VL_Encabeado.setAlignItems(FlexComponent.Alignment.STRETCH);
        VL_Encabeado.setWidth("100%");
        VL_Encabeado.setWidthFull();
        VL_Encabeado.setClassName("spacing-xs");

        VL_Encabeado.setHeight("min-content");
        VL_Encabeado.setSpacing(false);
        VL_Encabeado.setPadding(false);
        VL_Encabeado.setMargin(true);
        VL_Encabeado.getThemeList().add("spacing-xs");
        VL_Encabeado.setAlignItems(FlexComponent.Alignment.STRETCH);

        Image logoSecretaria = new Image(new StreamResource("logo_salud.png",() -> getClass().getResourceAsStream("/images/logo_salud.png")), "Logo Salud");
        logoSecretaria.setWidth(100, Unit.PIXELS);

        Image logoGobierno = new Image(new StreamResource("logo_salud.png",() -> getClass().getResourceAsStream("/images/logo_gobierno_estado.png")), "Logo Salud");
        logoGobierno.setWidth(100, Unit.PIXELS);

        H1 TextoSecretariaSalud= new H1("SECRETARIA DE SALUD");
        TextoSecretariaSalud.setWidth("max-content");

        HorizontalLayout HL_Logos_Texto_Secretaria = new HorizontalLayout();
        HL_Logos_Texto_Secretaria.setSpacing(false);
        HL_Logos_Texto_Secretaria.getThemeList().add("spacing-xs");
        HL_Logos_Texto_Secretaria.setAlignItems(FlexComponent.Alignment.STRETCH);
        HL_Logos_Texto_Secretaria.setWidth("100%");
        HL_Logos_Texto_Secretaria.setWidthFull();
        HL_Logos_Texto_Secretaria.addClassName(Gap.MEDIUM);
        HL_Logos_Texto_Secretaria.setWidth("100%");
        HL_Logos_Texto_Secretaria.setHeight("min-content");
        HL_Logos_Texto_Secretaria.setAlignItems(Alignment.CENTER);
        HL_Logos_Texto_Secretaria.setJustifyContentMode(JustifyContentMode.CENTER);
        HL_Logos_Texto_Secretaria.setSpacing(false);
        HL_Logos_Texto_Secretaria.setPadding(false);
        HL_Logos_Texto_Secretaria.setMargin(true);
        HL_Logos_Texto_Secretaria.getThemeList().add("spacing-xs");

        HL_Logos_Texto_Secretaria.add(logoSecretaria);
        HL_Logos_Texto_Secretaria.add(TextoSecretariaSalud);
        HL_Logos_Texto_Secretaria.add(logoGobierno);

        H2 Texto_Direccion_Planeacion = new H2("DIRECCION DE PLANEACION");
        Texto_Direccion_Planeacion.setWidth("max-content");

        HorizontalLayout HL_Texto_Direccion_Planeacion = new HorizontalLayout();
        HL_Texto_Direccion_Planeacion.setWidthFull();
        HL_Texto_Direccion_Planeacion.addClassName(Gap.MEDIUM);
        HL_Texto_Direccion_Planeacion.setWidth("100%");
        HL_Texto_Direccion_Planeacion.setHeight("min-content");
        HL_Texto_Direccion_Planeacion.setAlignItems(Alignment.CENTER);
        HL_Texto_Direccion_Planeacion.setJustifyContentMode(JustifyContentMode.CENTER);
        HL_Texto_Direccion_Planeacion.setSpacing(false);
        HL_Texto_Direccion_Planeacion.setPadding(false);
        HL_Texto_Direccion_Planeacion.setMargin(true);
        HL_Texto_Direccion_Planeacion.getThemeList().add("spacing-xs");

        HL_Texto_Direccion_Planeacion.add(Texto_Direccion_Planeacion);

        H3 Texto_Subdireccion_Tecnologias = new H3("SUBDIRECCION DE TECNOLOGIAS DE LA INFORMACION");
        Texto_Subdireccion_Tecnologias.setWidth("max-content");

        HorizontalLayout HL_Texto_Subdireccion_Tecnologias = new HorizontalLayout();
        HL_Texto_Subdireccion_Tecnologias.setWidthFull();
        HL_Texto_Subdireccion_Tecnologias.addClassName(Gap.MEDIUM);
        HL_Texto_Subdireccion_Tecnologias.setWidth("100%");
        HL_Texto_Subdireccion_Tecnologias.setHeight("min-content");
        HL_Texto_Subdireccion_Tecnologias.setAlignItems(Alignment.CENTER);
        HL_Texto_Subdireccion_Tecnologias.setJustifyContentMode(JustifyContentMode.CENTER);
        HL_Texto_Subdireccion_Tecnologias.add(Texto_Subdireccion_Tecnologias);
        HL_Texto_Subdireccion_Tecnologias.setSpacing(false);
        HL_Texto_Subdireccion_Tecnologias.setPadding(false);
        HL_Texto_Subdireccion_Tecnologias.setMargin(true);
        HL_Texto_Subdireccion_Tecnologias.getThemeList().add("spacing-xs");

        H1 Texto_Portal_Servicios = new H1("PORTAL DE SERVICIOS INFORMATICOS");
        Texto_Portal_Servicios.setWidth("max-content");

        HorizontalLayout HL_Texto_Portal_Servicios = new HorizontalLayout();
        HL_Texto_Portal_Servicios.setWidthFull();
        HL_Texto_Portal_Servicios.addClassName(Gap.MEDIUM);
        HL_Texto_Portal_Servicios.setWidth("100%");
        HL_Texto_Portal_Servicios.setHeight("min-content");
        HL_Texto_Portal_Servicios.setAlignItems(Alignment.CENTER);
        HL_Texto_Portal_Servicios.setJustifyContentMode(JustifyContentMode.CENTER);
        HL_Texto_Portal_Servicios.add(Texto_Portal_Servicios);
        HL_Texto_Portal_Servicios.setSpacing(false);
        HL_Texto_Portal_Servicios.setPadding(false);
        HL_Texto_Portal_Servicios.setMargin(true);
        HL_Texto_Portal_Servicios.getThemeList().add("spacing-xs");

        VL_Encabeado.setFlexGrow(1.0, HL_Logos_Texto_Secretaria);
        VL_Encabeado.setFlexGrow(1.0, HL_Texto_Direccion_Planeacion);
        VL_Encabeado.setFlexGrow(1.0, HL_Texto_Subdireccion_Tecnologias);
        VL_Encabeado.setFlexGrow(1.0, HL_Texto_Portal_Servicios);

        VL_Encabeado.add(HL_Logos_Texto_Secretaria);
        VL_Encabeado.add(HL_Texto_Direccion_Planeacion);
        VL_Encabeado.add(HL_Texto_Subdireccion_Tecnologias);
        VL_Encabeado.add(HL_Texto_Portal_Servicios);

        setFlexGrow(1.0, VL_Encabeado);


        add(VL_Encabeado);


        //****************************************

        H4 Texto_Bienvenido = new H4("Bienvenido al portal de servicios informaticos, como usuario que solicita soporte, podras entrar a la siguiente liga");
        Texto_Bienvenido.setWidth("max-content");

        VerticalLayout VL_Texto_Bienvenido = new VerticalLayout();
        VL_Texto_Bienvenido.setWidthFull();
        VL_Texto_Bienvenido.setWidth("100%");
        VL_Texto_Bienvenido.getStyle().set("flex-grow", "1");
        VL_Texto_Bienvenido.setJustifyContentMode(JustifyContentMode.START);
        VL_Texto_Bienvenido.setAlignItems(Alignment.CENTER);
        VL_Texto_Bienvenido.setSpacing(false);
        VL_Texto_Bienvenido.setPadding(false);
        VL_Texto_Bienvenido.setMargin(true);

        VL_Texto_Bienvenido.add(Texto_Bienvenido);

        add(VL_Texto_Bienvenido);

    }

}
