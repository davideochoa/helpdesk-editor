package com.helpdeskeditor.application.views;

import com.helpdeskeditor.application.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import javax.annotation.security.RolesAllowed;

@PageTitle("Folioss")
@Route(value = "folioss", layout = MainLayout.class)
@RolesAllowed("USER")
public class FoliossView extends VerticalLayout {
    HorizontalLayout HL_Folio = new HorizontalLayout();
    VerticalLayout VL_Unidad = new VerticalLayout();
    VerticalLayout VL_Objeto = new VerticalLayout();

    HorizontalLayout HL_Estatus = new HorizontalLayout();
    VerticalLayout VL_Estatus = new VerticalLayout();
    VerticalLayout VL_Archivo = new VerticalLayout();

    IntegerField IF_Folio = new IntegerField();


    public FoliossView() {
        layoutUnidad();
        HL_Folio.add(VL_Unidad,VL_Objeto);
        HL_Estatus.add(VL_Estatus,VL_Archivo);
        this.add(HL_Folio,HL_Estatus);
    }

    private void layoutUnidad(){
        IF_Folio.setLabel("Numero Folio");
        VL_Unidad.add(IF_Folio);
    }

}
