package com.helpdeskeditor.application.util;

import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.progressbar.ProgressBar;

public class UIutils {

    public static Span lineaDivision(){
        Span span = new Span();
        span.getStyle().set("background-image", "linear-gradient(135deg, #777 , rgba(0, 0, 0, 0))");
        span.getStyle().set("flex", "0 0 2px");
        span.getStyle().set("align-self", "stretch");

        return span;
    }

    public static Notification notificacionEventoERROR(String texto){
        Notification notification = new Notification(texto);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setPosition(Notification.Position.MIDDLE);
        notification.setDuration(5000);
        return notification;
    }

    public static Notification notificacionEventoSUCCESS(String texto){
        Notification notification = new Notification(texto);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        notification.setPosition(Notification.Position.MIDDLE);
        notification.setDuration(5000);
        return notification;
    }

    public static Notification notificacionEventoNeutro(String texto){
        Notification notification = new Notification(texto);
        notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
        notification.setPosition(Notification.Position.MIDDLE);
        notification.setDuration(5000);
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
}
