package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.web.charts.LineMultiYAxesChartExample;
import com.helpdeskeditor.application.app.web.charts.VerticalBarChartExample;
import com.helpdeskeditor.application.app.web.components.camera.VCameraDemoView;
import com.helpdeskeditor.application.util.ApexCharts.ApexCharts;
import com.helpdeskeditor.application.util.ApexCharts.config.TitleSubtitle;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.RolesAllowed;
import java.net.URISyntaxException;

@Slf4j
@PageTitle("Acerca De")
@Route(value = "acerca-de", layout = MainLayout.class)
//@AnonymousAllowed
@RolesAllowed("ADMINS")
public class AcercaDeView extends VerticalLayout {
    public AcercaDeView() throws URISyntaxException {
        setSpacing(false);

        Image img = new Image("images/empty-plant.png", "placeholder plant");

        img.setWidth("200px");
        add(img);

        add(new H2("This place intentionally left empty"));

        VCameraDemoView vc = new VCameraDemoView();
        add(vc);

        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
