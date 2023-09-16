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

        VerticalBarChartExample vbce = new VerticalBarChartExample();
        ApexCharts chart = vbce.build();
        chart.getStyle().set("align-self", "center");

        LineMultiYAxesChartExample lm = new LineMultiYAxesChartExample();
        TitleSubtitle titleSubtitle = new TitleSubtitle();
        titleSubtitle.setText("PRUEBAA");
        lm.withTitle(titleSubtitle);
        ApexCharts plchart = lm.build();

        FormLayout fm = new FormLayout();
        fm.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 2));

        fm.add(chart);
        fm.add(plchart);

        add(fm);

        VCameraDemoView vc = new VCameraDemoView();
        add(vc);

        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
