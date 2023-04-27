package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.data.DAO.FolioDAO;
import com.helpdeskeditor.application.app.data.entity.AreaEntity;
import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.helpdeskeditor.application.app.service.FolioService;
import com.helpdeskeditor.application.app.service.UsuarioSoporteService;
import com.helpdeskeditor.application.configuration.AuthenticatedUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Slf4j
@PageTitle("Listado de Folios")
@Route(value = "foliosgrid", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed({"USER","ADMIN"})
public class FoliosGridView extends VerticalLayout{
    private Grid<FolioDAO> grid;
    private TextField folioFilter,usuarioreportaFilter,marcaFilter,modeloFilter,
            numeroSerieFilter,numeroInventarioFilter,estadoFilter,unidadFilter;

    private ComboBox<UsuarioSoporteEntity> CB_UsuarioSoporte = new ComboBox<UsuarioSoporteEntity>("Soporte");

    private final FolioService folioService;

    public FoliosGridView(FolioService folioService, AuthenticatedUser authenticatedUser, UsuarioSoporteService usuarioSoporteService) {
        this.folioService = folioService;

        grid = new Grid<>(FolioDAO.class, false);

        if(authenticatedUser.get().get().getRol().equals("ADMIN")){
            List<UsuarioSoporteEntity> usuarioSoporteEntities = usuarioSoporteService.findAll();
            CB_UsuarioSoporte.setItems(usuarioSoporteEntities);
            grid.setItems(folioService.getAll());
        }
        else{
            CB_UsuarioSoporte.setItems(authenticatedUser.get().get());
            CB_UsuarioSoporte.setValue(authenticatedUser.get().get());
            grid.setItems(folioService.getByIdUsuarioSoporteAsignado(authenticatedUser.get().get().getId()));
        }

        CB_UsuarioSoporte.setItemLabelGenerator(UsuarioSoporteEntity::getNombrePropio);
        CB_UsuarioSoporte.setWidth("400px");

        CB_UsuarioSoporte.addValueChangeListener(e -> {
            if(e.getValue() != null){
                folioFilter.clear();
                folioFilter.clear();
                usuarioreportaFilter.clear();
                marcaFilter.clear();
                modeloFilter.clear();
                numeroSerieFilter.clear();
                numeroInventarioFilter.clear();
                estadoFilter.clear();
                unidadFilter.clear();

                Integer Id = e.getValue().getId();
                grid.setItems(folioService.getByIdUsuarioSoporteAsignado(Id));
            }
        });

        Button B_allFolios = new Button ("Todos los Folios");
        B_allFolios.addClickListener(e -> {
            folioFilter.clear();
            folioFilter.clear();
            usuarioreportaFilter.clear();
            marcaFilter.clear();
            modeloFilter.clear();
            numeroSerieFilter.clear();
            numeroInventarioFilter.clear();
            estadoFilter.clear();
            unidadFilter.clear();

            grid.setItems(folioService.getAll());
            CB_UsuarioSoporte.clear();
        });

        HorizontalLayout horizontalLayoutComboTecnicos = new HorizontalLayout();
        horizontalLayoutComboTecnicos.setVerticalComponentAlignment(Alignment.BASELINE,CB_UsuarioSoporte,B_allFolios);
        horizontalLayoutComboTecnicos.setPadding(false);
        horizontalLayoutComboTecnicos.setMargin(false);
        horizontalLayoutComboTecnicos.add(CB_UsuarioSoporte,B_allFolios);

        //grid.addColumn(FolioDAO :: getId).setHeader("Folio").setKey("id").setResizable(true);
/*
        grid.addColumn(new ComponentRenderer<>(RouterLink::new, (routerLink, folioDAO) -> {
            routerLink.setRoute(FolioView.class,folioDAO.getId()+"");
            routerLink.setText(folioDAO.getId()+"");

        })).setHeader("FOLIO").setKey("id").setResizable(true);
*/

        grid.addColumn(new ComponentRenderer<>(Anchor::new, (anchor, folioDAO) -> {
            anchor.setHref("/folios/"+folioDAO.getId()+"");
            anchor.setText(folioDAO.getId()+"");
            //anchor.getElement().setAttribute("target", "_blank");
            anchor.getElement().setAttribute("router-ignore", "");
        })).setHeader("FOLIO").setKey("id").setResizable(true);

        grid.addColumn(FolioDAO :: getUnidad).setHeader("Unidad").setKey("unidad").setResizable(true);
        grid.addColumn(FolioDAO :: getUsuarioReporta).setHeader("Usuario Reporta").setKey("usuarioReporta").setResizable(true);
        grid.addColumn(FolioDAO :: getMarca).setHeader("Marca").setKey("marca").setResizable(true);
        grid.addColumn(FolioDAO :: getModelo).setHeader("Modelo").setKey("modelo").setResizable(true);
        grid.addColumn(FolioDAO :: getNumeroSerie).setHeader("Numero Serie").setKey("numeroSerie").setResizable(true);
        grid.addColumn(FolioDAO :: getNumeroInventario).setHeader("Numero Inventario").setKey("numeroInventario").setResizable(true);
        grid.addColumn(FolioDAO :: getEstado).setHeader("Estado").setKey("estado").setResizable(true);



        grid.setClassNameGenerator(folio -> {
            if(folio.getEstado().equals("ABIERTO")){
                LocalDate fechaInicial = folio.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate fechaActual = LocalDate.now();

                Integer noOfDaysBetween = Math.toIntExact(ChronoUnit.DAYS.between(fechaInicial, fechaActual));

                log.info("fechaInicial:"+fechaInicial);
                log.info("fechaActual:"+fechaActual);
                log.info("noOfDaysBetween:"+noOfDaysBetween);

                if (noOfDaysBetween >= 5 && noOfDaysBetween <= 9) return "low-rating";
                if (noOfDaysBetween > 9) return "high-rating";
            }
            return null;
        });

        prepareFilterFields();

        add(horizontalLayoutComboTecnicos);

        add(grid);

        this.setHeight("100%");
    }
    private void prepareFilterFields() {
        HeaderRow headerRow = grid.appendHeaderRow();

        folioFilter = gridTextFieldFilter("id", headerRow);
        unidadFilter = gridTextFieldFilter("unidad", headerRow);
        usuarioreportaFilter = gridTextFieldFilter("usuarioReporta", headerRow);
        marcaFilter = gridTextFieldFilter("marca", headerRow);
        modeloFilter = gridTextFieldFilter("modelo", headerRow);
        numeroSerieFilter = gridTextFieldFilter("numeroSerie", headerRow);
        numeroInventarioFilter = gridTextFieldFilter("numeroInventario", headerRow);
        estadoFilter = gridTextFieldFilter("estado", headerRow);
    }

    private TextField gridTextFieldFilter(String columnKey, HeaderRow headerRow) {
        TextField filter = new TextField();
        filter.setValueChangeMode(ValueChangeMode.TIMEOUT);

        filter.setClearButtonVisible(true);
        filter.addValueChangeListener(event -> this.onFilterChange());
        filter.setWidthFull();
        headerRow.getCell(grid.getColumnByKey(columnKey)).setComponent(filter);
        return filter;
    }

    private void onFilterChange(){
        ListDataProvider<FolioDAO> listDataProvider = (ListDataProvider<FolioDAO>) grid.getDataProvider();
        listDataProvider.setFilter(item -> {
            boolean folioFilterMatch = true;
            boolean unidadFilterMatch = true;
            boolean usuarioreportaFilterMatch = true;
            boolean marcaFilterMatch = true;
            boolean modeloFilterMatch = true;
            boolean numeroSerieFilterMatch = true;
            boolean numeroInventarioFilterMatch = true;
            boolean estadoFilterMatch = true;

            if(!folioFilter.isEmpty())
                folioFilterMatch = item.getId().toString().contains(folioFilter.getValue().toUpperCase());

            if(!unidadFilter.isEmpty())
                unidadFilterMatch = item.getUnidad().contains(unidadFilter.getValue().toUpperCase());

            if(!usuarioreportaFilter.isEmpty())
                usuarioreportaFilterMatch = item.getUsuarioReporta().contains(usuarioreportaFilter.getValue().toUpperCase());

            if(!marcaFilter.isEmpty())
                marcaFilterMatch = item.getMarca().contains(marcaFilter.getValue().toUpperCase());

            if(!modeloFilter.isEmpty())
                modeloFilterMatch = item.getModelo().contains(modeloFilter.getValue().toUpperCase());

            if(!numeroSerieFilter.isEmpty())
                numeroSerieFilterMatch = item.getNumeroSerie().contains(numeroSerieFilter.getValue().toUpperCase());

            if(!numeroInventarioFilter.isEmpty())
                numeroInventarioFilterMatch = item.getNumeroInventario().contains(numeroInventarioFilter.getValue().toUpperCase());

            if(!estadoFilter.isEmpty())
                estadoFilterMatch = item.getEstado().contains(estadoFilter.getValue().toUpperCase());

            return folioFilterMatch && unidadFilterMatch && usuarioreportaFilterMatch && marcaFilterMatch &&
                    modeloFilterMatch && numeroSerieFilterMatch && numeroInventarioFilterMatch && estadoFilterMatch;
        });
    }

}
