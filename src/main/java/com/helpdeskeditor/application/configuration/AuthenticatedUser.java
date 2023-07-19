package com.helpdeskeditor.application.configuration;

import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.helpdeskeditor.application.app.data.repository.UsuarioSoporteRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUser {

    private final UsuarioSoporteRepository userRepository;

    @Value("${server.servlet.context-path}")
    private String context;

    @Autowired
    public AuthenticatedUser(UsuarioSoporteRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Optional<Authentication> getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        return Optional.ofNullable(context.getAuthentication())
                .filter(authentication -> !(authentication instanceof AnonymousAuthenticationToken));
    }

    public Optional<UsuarioSoporteEntity> get() {
        return getAuthentication().map(authentication -> userRepository.findByNombreUsuario(authentication.getName()));
    }

    public void logout() {
        UI.getCurrent().getPage().setLocation(context+SecurityConfiguration.LOGOUT_URL);
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(VaadinServletRequest.getCurrent().getHttpServletRequest(), null, null);
    }

}
