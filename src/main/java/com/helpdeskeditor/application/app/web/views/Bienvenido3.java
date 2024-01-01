package com.helpdeskeditor.application.app.web.views;

import com.helpdeskeditor.application.app.web.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;

@Slf4j
@PageTitle("Bienvenido3")
@Route(value = "Bienvenido3", layout = MainLayout.class)
@AnonymousAllowed
public class Bienvenido3 extends VerticalLayout {

    public Bienvenido3() throws URISyntaxException {
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(false);
        layout.setAlignItems(Alignment.CENTER);
        layout.add(new Button("Button 1"));
        layout.add(new Button("Button 2"));
        layout.add(new Button("Button 3"));

        this.add(layout);

    }

}
