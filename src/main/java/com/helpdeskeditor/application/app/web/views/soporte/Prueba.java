package com.helpdeskeditor.application.app.web.views.soporte;

import com.helpdeskeditor.application.app.web.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("PRUEBA")
@RolesAllowed({"USER","ADMIN"})
@Route(value = "prueba", layout = MainLayout.class)
public class Prueba extends HorizontalLayout {

    private Icon checkIcon;
    private Span passwordStrengthText;

    public Prueba() {
        TextField textField = new TextField();
        textField.setLabel("Phone number");
        textField.setHelperText("Include country and area prefixes");
        textField.setWidth(400, Unit.PIXELS);

        PasswordField passwordField = new PasswordField();
        passwordField.setLabel("Password");
        passwordField.setRevealButtonVisible(false);

        checkIcon = VaadinIcon.CHECK.create();
        checkIcon.setVisible(false);
        passwordField.setSuffixComponent(checkIcon);

        Div passwordStrength = new Div();
        passwordStrengthText = new Span();
        passwordStrength.add(new Text("Password strength: "),
                passwordStrengthText);
        passwordField.setHelperComponent(passwordStrength);

        add(textField);
        add(passwordField);

        passwordField.setValueChangeMode(ValueChangeMode.EAGER);
        passwordField.addValueChangeListener(e -> {
            String password = e.getValue();
            updateHelper(password);
        });

        updateHelper("");
    }

    private void updateHelper(String password) {
        if (password.length() > 9) {
            passwordStrengthText.setText("strong");
            passwordStrengthText.getStyle().set("color",
                    "var(--lumo-success-color)");
            checkIcon.setVisible(true);
        } else if (password.length() > 5) {
            passwordStrengthText.setText("moderate");
            passwordStrengthText.getStyle().set("color", "#e7c200");
            checkIcon.setVisible(false);
        } else {
            passwordStrengthText.setText("weak");
            passwordStrengthText.getStyle().set("color",
                    "var(--lumo-error-color)");
            checkIcon.setVisible(false);
        }
    }

}
