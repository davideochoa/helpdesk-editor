package com.helpdeskeditor.application.views;

import com.helpdeskeditor.application.data.modelo.Area;
import com.helpdeskeditor.application.data.modelo.TipoIncidencia;
import com.helpdeskeditor.application.data.modelo.Unidad;
import com.helpdeskeditor.application.data.modelo.UsuarioReporta;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Folioss")
@Route(value = "folioss", layout = MainLayout.class)
@RolesAllowed("USER")
public class FoliossView extends HorizontalLayout {
    VerticalLayout VL_Unidad = new VerticalLayout();
    VerticalLayout VL_Objeto = new VerticalLayout();

    IntegerField IF_Folio = new IntegerField();
    ComboBox<Unidad> CB_Unidad = new ComboBox<Unidad>("Unidad");
    ComboBox<Area> CB_Area = new ComboBox<Area>("Area");
    ComboBox<UsuarioReporta> CB_UsuarioReporta = new ComboBox<UsuarioReporta>("Usuario Reporta");

    TextField TF_Telefono = new TextField();

    TextField TF_ReferenciaDocumental = new TextField();


    ComboBox<TipoIncidencia> CB_TipoIncidencia = new ComboBox<TipoIncidencia>("Tipo Incidencia");
    public FoliossView() {
        layoutUnidad();
        layoutObjeto();

        this.add(VL_Unidad,VL_Objeto);
    }

    private void layoutObjeto(){
        CB_TipoIncidencia.setItemLabelGenerator(TipoIncidencia::getNombre);

        VL_Objeto.add(CB_TipoIncidencia);
    }

    private void layoutUnidad(){
        IF_Folio.setLabel("Numero Folio");

        //TF_Telefono.setAllowedCharPattern("^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$");
        TF_Telefono.setHelperText("Formato:+(123)456-7890");
        TF_Telefono.setLabel("Numero Telefonico");
        TF_Telefono.setWidth("240px");

        CB_Unidad.setItemLabelGenerator(Unidad::getNombre);
        CB_Area.setItemLabelGenerator(Area::getNombre);
        CB_UsuarioReporta.setItemLabelGenerator(UsuarioReporta::getNombre);

        TF_ReferenciaDocumental.setLabel("Referencia Documental");
        TF_ReferenciaDocumental.setHelperText("Numero de oficio/orden/folio de seguimiento");

        VL_Unidad.add(IF_Folio);
        VL_Unidad.add(CB_Unidad);
        VL_Unidad.add(CB_Area);
        VL_Unidad.add(CB_UsuarioReporta);
        VL_Unidad.add(TF_Telefono);
        VL_Unidad.add(TF_ReferenciaDocumental);
    }

}
