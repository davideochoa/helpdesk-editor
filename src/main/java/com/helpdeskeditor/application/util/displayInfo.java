package com.helpdeskeditor.application.util;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

public class displayInfo {
    public static Notification notificacion(String texto, NotificationVariant variante, Notification.Position posicion){
        Notification notification = Notification.show(texto);
        notification.addThemeVariants(variante);
        notification.setPosition(posicion);
        return notification;
    }
}
