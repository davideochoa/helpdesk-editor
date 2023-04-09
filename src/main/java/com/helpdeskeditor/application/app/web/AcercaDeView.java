package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.util.signaturepad.SignaturePad;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Value;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.helpdeskeditor.application.configuration.DriverManagerDataSource.SQLDataSource;

@Slf4j
@PageTitle("Acerca De")
@Route(value = "acerca-de", layout = MainLayout.class)
@AnonymousAllowed
//@RolesAllowed("USER")
public class AcercaDeView extends VerticalLayout {
    @Value("${app.datasource.jdbc-url}")
    private String url;
    @Value("${app.datasource.username}")
    private String userName;
    @Value("${app.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String className;
    public AcercaDeView() throws URISyntaxException {
        setSpacing(false);

        Image img = new Image("images/empty-plant.png", "placeholder plant");

        img.setWidth("200px");
        add(img);

        add(new H2("This place intentionally left empty"));
        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
