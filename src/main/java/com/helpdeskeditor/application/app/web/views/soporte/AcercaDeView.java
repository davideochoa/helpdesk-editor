package com.helpdeskeditor.application.app.web.views.soporte;

import com.helpdeskeditor.application.app.web.MainLayout;
import com.helpdeskeditor.application.app.web.charts.LineMultiYAxesChartExample;
import com.helpdeskeditor.application.app.web.charts.VerticalBarChartExample;
import com.helpdeskeditor.application.app.web.components.camera.VCameraDemoView;
import com.helpdeskeditor.application.util.ApexCharts.ApexCharts;
import com.helpdeskeditor.application.util.ApexCharts.config.TitleSubtitle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.Lumo;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.net.URISyntaxException;

@Slf4j
@PageTitle("Acerca De")
@Route(value = "acerca-de", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)
//@AnonymousAllowed
//@PermitAll
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

        H1 h1 = new H1("Hello darkness");

        Button toggleButton = new Button("Toggle dark theme", click -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList();

            if (themeList.contains(Lumo.DARK)) {
                themeList.remove(Lumo.DARK);
            } else {
                themeList.add(Lumo.DARK);
            }
        });

        add(toggleButton, h1);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
