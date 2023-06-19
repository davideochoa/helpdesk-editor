package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.helpdeskeditor.application.configuration.AuthenticatedUser;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.internal.RouteUtil;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@AnonymousAllowed
@PageTitle("Login")
@Route(value = "login")
@Slf4j

public class LoginView extends LoginOverlay implements BeforeEnterObserver {

   private String Titulo = "Biomedicos - Incidencias";
    private String Description= "Registro de incidencias de Biomedicos";

/*     private String Titulo = "T.I. - Incidencias";
    private String Description= "Registro de incidencias de T.I.";*/

    private final AuthenticatedUser authenticatedUser;

    public LoginView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        setAction(RouteUtil.getRoutePath(VaadinService.getCurrent().getContext(), getClass()));

        LoginI18n i18n = LoginI18n.createDefault();

        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("Acceso");
        i18nForm.setUsername("Usuario");
        i18nForm.setPassword("Contraseña");
        i18nForm.setSubmit("ENVIAR");

        LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        i18nErrorMessage.setTitle("ERROR LOGIN");
        i18nErrorMessage.setMessage("Nombre de usuario o contraseña incorrecta, intente nuevamente");
        i18n.setErrorMessage(i18nErrorMessage);

        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle(Titulo);
        i18n.getHeader().setDescription(Description);

        i18n.setForm(i18nForm);
        i18n.setAdditionalInformation(null);

        i18n.setForm(i18nForm);
        setI18n(i18n);

        setForgotPasswordButtonVisible(false);
        setOpened(true);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (authenticatedUser.get().isPresent()) {
            setOpened(false);
            event.forwardTo("");
        }

        setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
    }

}
