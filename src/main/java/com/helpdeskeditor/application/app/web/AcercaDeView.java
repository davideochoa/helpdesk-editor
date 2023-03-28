package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.web.MainLayout;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import de.f0rce.signaturepad.SignaturePad;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.RolesAllowed;
import java.net.URISyntaxException;

@Slf4j
@PageTitle("Acerca De")
@Route(value = "acerca-de", layout = MainLayout.class)
@AnonymousAllowed
//@RolesAllowed("USER")
public class AcercaDeView extends VerticalLayout {

    public AcercaDeView() throws URISyntaxException {
        setSpacing(false);

        Image img = new Image("images/empty-plant.png", "placeholder plant");

        img.setWidth("200px");
        add(img);

        add(new H2("This place intentionally left empty"));
        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));


        SignaturePad signature = new SignaturePad();
        signature.setClearButtonVisible(true);
        signature.setHeight("150px");
        signature.setWidth("300px");
        signature.setBackgroundColor(0, 0, 0, 0);
        signature.setPenColor("#000000");
        signature.setVisible(true);

        add(new H2("This place intentionally left empty"));

        FormLayout formLayout = new FormLayout();
        formLayout.add(signature);
        add(formLayout);
        add(new H2("This place intentionally left empty"));


        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
