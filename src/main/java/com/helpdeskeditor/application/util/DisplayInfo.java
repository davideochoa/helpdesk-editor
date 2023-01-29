package com.helpdeskeditor.application.util;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.progressbar.ProgressBar;

public class DisplayInfo {

    public static Notification notificacion(String texto, NotificationVariant variante, Notification.Position posicion){
        Notification notification = Notification.show(texto);
        notification.addThemeVariants(variante);
        notification.setPosition(posicion);
        notification.setDuration(10000);
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
}