package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.helpdeskeditor.application.app.service.UsuarioSoporteService;
import com.helpdeskeditor.application.app.web.components.appnav.AppNav;
import com.helpdeskeditor.application.app.web.components.appnav.AppNavItem;
import com.helpdeskeditor.application.app.web.views.soporte.AcercaDeView;
import com.helpdeskeditor.application.app.web.views.soporte.Bienvenido;
import com.helpdeskeditor.application.app.web.views.soporte.CatalogosView;
import com.helpdeskeditor.application.app.web.views.soporte.DashBoard1View;
import com.helpdeskeditor.application.app.web.views.soporte.DashBoard2View;
import com.helpdeskeditor.application.app.web.views.soporte.FolioView;
import com.helpdeskeditor.application.app.web.views.soporte.FoliosGridView;
import com.helpdeskeditor.application.app.web.views.usuario_final.PortalUsuarioView;
import com.helpdeskeditor.application.configuration.AuthenticatedUser;
import com.helpdeskeditor.application.configuration.SecurityConfiguration;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {
    private H2 viewTitle;
    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    UsuarioSoporteEntity usuarioSoporteEntity;
    public MainLayout(AuthenticatedUser authenticatedUser,
                      AccessAnnotationChecker accessChecker,
                      SecurityConfiguration securityConfiguration,
                      UsuarioSoporteService usuarioSoporteService) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
/*
        if( authenticatedUser.get() != null) {
            usuarioSoporteEntity = authenticatedUser.get().get();
            if (usuarioSoporteEntity.getEsReseteadoPassword()) {
                this.setVisible(false);
                DialogRePasword dr = new DialogRePasword(securityConfiguration, authenticatedUser, usuarioSoporteService);
                dr.open();
            }
        }
*/


    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("HelpDesk");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();

        if (accessChecker.hasAccess(FolioView.class)) {
            nav.addItem(new AppNavItem("Folio", FolioView.class, "la la-globe"));
        }
        if (accessChecker.hasAccess(FoliosGridView.class)) {
            nav.addItem(new AppNavItem("Listado de Folios", FoliosGridView.class, "la la-globe"));
        }
        if (accessChecker.hasAccess(CatalogosView.class)) {
            nav.addItem(new AppNavItem("Catalogos", CatalogosView.class, "la la-globe"));
        }
        if (accessChecker.hasAccess(DashBoard1View.class)) {
            nav.addItem(new AppNavItem("DashBoard Pastel", DashBoard1View.class, "la la-file"));
        }
        if (accessChecker.hasAccess(DashBoard2View.class)) {
            nav.addItem(new AppNavItem("DashBoard TimeLine", DashBoard2View.class, "la la-file"));
        }
        if (accessChecker.hasAccess(AcercaDeView.class)) {
            nav.addItem(new AppNavItem("Acerca De", AcercaDeView.class, "la la-file"));
        }
        if (accessChecker.hasAccess(PortalUsuarioView.class)) {
            nav.addItem(new AppNavItem("Portal Soporte a Usuario", PortalUsuarioView.class, "la la-file"));
        }
        if (accessChecker.hasAccess(Bienvenido.class)) {
            nav.addItem(new AppNavItem("Bienvenido", Bienvenido.class, "la la-file"));
        }

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        Optional<UsuarioSoporteEntity> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            UsuarioSoporteEntity user = maybeUser.get();

            Avatar avatar = new Avatar(user.getNombrePropio());
            /*StreamResource resource = new StreamResource("profile-pic",
                    () -> new ByteArrayInputStream(user.getProfilePicture()));
            avatar.setImageResource(resource);*/
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(avatar);
            div.add(user.getNombrePropio());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
            userName.getSubMenu().addItem("Sign out", e -> {
                authenticatedUser.logout();
            });

            layout.add(userMenu);
        } else {
            Anchor loginLink = new Anchor("login", "Sign in");
            layout.add(loginLink);
        }

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
