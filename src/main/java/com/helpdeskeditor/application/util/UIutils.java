package com.helpdeskeditor.application.util;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.theme.lumo.LumoIcon;

public class UIutils {

    private static int timeShow = 3000;

    public static Span lineaDivision(){
        Span span = new Span();
        span.getStyle().set("background-image", "linear-gradient(135deg, #777 , rgba(0, 0, 0, 0))");
        span.getStyle().set("flex", "0 0 2px");
        span.getStyle().set("align-self", "stretch");

        return span;
    }

    public static Notification notificacionERROR(String texto){
        Notification notification = new Notification(texto);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setPosition(Notification.Position.MIDDLE);
        notification.setDuration(timeShow);
        return notification;
    }

    public static Notification notificacionSUCCESS(String texto){
        Notification notification = new Notification(texto);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        notification.setPosition(Notification.Position.MIDDLE);
        notification.setDuration(timeShow);
        return notification;
    }

    public static Notification notificacionNeutral(String texto){
        Notification notification = new Notification(texto);
        notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
        notification.setPosition(Notification.Position.MIDDLE);
        notification.setDuration(timeShow);
        return notification;
    }


    public static Dialog dialogPorgressBarIndeterminate(String textoHeader, String texto){
        ProgressBar progressBar = new ProgressBar();
        progressBar.setIndeterminate(true);

        Dialog dialog = new Dialog();
        dialog.setHeaderTitle(textoHeader);
        dialog.add(texto);
        dialog.add(progressBar);
        dialog.setModal(true);

        return dialog;
    }

    public static ConfirmDialog confirmDialog(String header, String texto){
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setHeader(header);
        dialog.setText(texto);

        dialog.setConfirmText("OK");

        return dialog;
    }



    public static class PanelPaginacion extends HorizontalLayout{

        private Integer TOTAL_PAGINAS = 0;

        private Integer PAGINA_ACTUAL = 0;

        private H4 textoPaginacion = new H4(PAGINA_ACTUAL+" de "+TOTAL_PAGINAS);

        Button BT_IzqMax = new Button(LumoIcon.CHEVRON_LEFT.create());
        Button BT_Izq = new Button(LumoIcon.ANGLE_LEFT.create());
        Button BT_Der = new Button(LumoIcon.ANGLE_RIGHT.create());
        Button BT_DerMax = new Button(LumoIcon.CHEVRON_RIGHT.create());

        public PanelPaginacion() {

            this.setPadding(false);
            this.setMargin(false);
            this.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            this.setAlignItems(FlexComponent.Alignment.CENTER);
            this.setWidthFull();

            textoPaginacion.getStyle().set("border","0px");
            textoPaginacion.getStyle().set("padding","0px");
            textoPaginacion.getStyle().set("margin","0px");

            this.add(BT_IzqMax);
            this.add(BT_Izq);
            this.add(textoPaginacion);
            this.add(BT_Der);
            this.add(BT_DerMax);

        }

        public Button getButtonBT_IzqMax(){ return BT_IzqMax; }

        public Button getButtonBT_Izq(){ return BT_Izq; }

        public Button getButtonBT_Der(){ return BT_Der; }

        public Button getButtonBT_DerMax(){ return BT_DerMax; }


        public void setTextoPaginacionL(Integer paginaActual, Integer totalPaginas){
            PAGINA_ACTUAL = paginaActual;
            TOTAL_PAGINAS = totalPaginas;

            textoPaginacion.setText(PAGINA_ACTUAL+" de "+TOTAL_PAGINAS);
        }
    }
}
