package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.helpdeskeditor.application.app.service.UsuarioSoporteService;
import com.helpdeskeditor.application.configuration.AuthenticatedUser;
import com.helpdeskeditor.application.configuration.SecurityConfiguration;
import com.helpdeskeditor.application.util.UIutils;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DialogRePasword extends Dialog {
    private PasswordField passwordField1 = new PasswordField();
    private PasswordField passwordField2 = new PasswordField();

    private UsuarioSoporteService usuarioSoporteService;
    private SecurityConfiguration securityConfiguration;
    private AuthenticatedUser authenticatedUser;
    public DialogRePasword(SecurityConfiguration securityConfiguration,
                           AuthenticatedUser authenticatedUser,
                           UsuarioSoporteService usuarioSoporteService) {
        super.setModal(false);
        this.setModal(false);

        this.usuarioSoporteService = usuarioSoporteService;

        this.securityConfiguration = securityConfiguration;
        this.authenticatedUser = authenticatedUser;

        PasswordEncoder passwordEncoder = securityConfiguration.passwordEncoder();

        this.setHeaderTitle("Nueva contraseña");

        passwordField1.setRevealButtonVisible(true);
        passwordField1.setLabel("Nueva Contraseña");

        passwordField2.setRevealButtonVisible(true);
        passwordField2.setLabel("Repite Contraseña");

        VerticalLayout dialogLayout = new VerticalLayout(passwordField1,passwordField2);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

        this.add(dialogLayout);

        Button saveButton = new Button("GUARDAR");
        saveButton.addClickListener(e -> {
            String password1 = passwordField1.getValue();
            String password2 = passwordField2.getValue();

            if(password1 != null && password2 != null){
                if(password1.length() > 3 && password2.length() > 3){
                    if(password1.equals(password2)){
                        UsuarioSoporteEntity usuarioSoporteEntity = authenticatedUser.get().get();
                        usuarioSoporteEntity.setPassword(passwordEncoder.encode(password1));
                        usuarioSoporteEntity.setEsReseteadoPassword(false);

                        usuarioSoporteService.save(usuarioSoporteEntity);

                        authenticatedUser.logout();
                    }
                    else
                        UIutils.notificacionERROR("Las contaseñas deben ser iguales!").open();
                }
                else
                    UIutils.notificacionERROR("Las contaseñas deben de contener mas de tres caracteres!").open();
            }
            else
                UIutils.notificacionERROR("Las contaseñas no pueden ser vacias!").open();
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.getFooter().add(saveButton);
    }

}