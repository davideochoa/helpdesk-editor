package com.helpdeskeditor.application.app.web.views;

import com.helpdeskeditor.application.app.web.MainLayout;
import com.helpdeskeditor.application.app.web.components.camera.VCameraDemoView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
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
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import java.net.URISyntaxException;

@Slf4j
@PageTitle("Bienvenido")

@Route(value = "bienvenido", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class Bienvenido extends VerticalLayout {

    public Bienvenido(){
        VerticalLayout VL_Principal = new VerticalLayout();
        VL_Principal.setSpacing(false);
        VL_Principal.setAlignItems(FlexComponent.Alignment.CENTER);

        Image logoSecretaria = new Image(new StreamResource("logo_salud.png",
                                        () -> getClass().getResourceAsStream("/images/logo_salud.png")),
                                    "Logo Salud");

        Image logoGobierno = new Image(new StreamResource("logo_salud.png",
                                        () -> getClass().getResourceAsStream("/images/logo_gobierno_estado.png")),
                                    "Logo Salud");

        HorizontalLayout HL_Logos_Texto_Secretaria = new HorizontalLayout();
        HL_Logos_Texto_Secretaria.setSpacing(false);
        HL_Logos_Texto_Secretaria.setAlignItems(FlexComponent.Alignment.CENTER);

        H1 Texto_Secretaria = new H1("SECRETARIA DE SALUD");
        Texto_Secretaria.getStyle().set("border","0px");
        Texto_Secretaria.getStyle().set("padding","0px");
        Texto_Secretaria.getStyle().set("margin","0px");

        HL_Logos_Texto_Secretaria.add(logoSecretaria);
        HL_Logos_Texto_Secretaria.add(Texto_Secretaria);
        HL_Logos_Texto_Secretaria.add(logoGobierno);

        H2 Texto_Direccion = new H2("DIRECCION DE PLANEACION");
        Texto_Direccion.getStyle().set("border","0px");
        Texto_Direccion.getStyle().set("padding","0px");
        Texto_Direccion.getStyle().set("margin","0px");

        H3 Texto_SubDireccion = new H3("SUBDIRECCION DE TECNOLOGIAS DE LA INFORMACION");
        Texto_SubDireccion.getStyle().set("border","0px");
        Texto_SubDireccion.getStyle().set("padding","0px");
        Texto_SubDireccion.getStyle().set("margin","0px");

        H1 Texto_Portal = new H1("PORTAL DE SERVICIOS INFORMATICOS");
        Texto_Portal.getStyle().set("border","0px");
        Texto_Portal.getStyle().set("padding","0px");
        Texto_Portal.getStyle().set("margin","0px");

        VL_Principal.add(HL_Logos_Texto_Secretaria);
        VL_Principal.add(Texto_Direccion);
        VL_Principal.add(Texto_SubDireccion);
        VL_Principal.add(Texto_Portal);

        add(VL_Principal);

        //****************************************

        VerticalLayout VL_Texto_Bienvenido = new VerticalLayout();
        VL_Texto_Bienvenido.setSpacing(false);
        VL_Texto_Bienvenido.setAlignItems(FlexComponent.Alignment.CENTER);

        VL_Texto_Bienvenido.add(new H4("Bienvenido al portal de servicios informaticos, como usuario que solicita soporte, podras entrar a la siguiente liga"));

        VL_Texto_Bienvenido.add(new Anchor("portal-usuario","PORTAL USUARIO"));

        VL_Texto_Bienvenido.add(new H4("como usuario que otorga soporte teccnico, podras entrar a la siguiente liga"));

        VL_Texto_Bienvenido.add(new Anchor("folio","PORTAL SOPORTE TECNICO"));

        add(VL_Texto_Bienvenido);


        //****************************************

    }

}
