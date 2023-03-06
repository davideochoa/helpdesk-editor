package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.data.DAO.FolioDAO;
import com.helpdeskeditor.application.app.service.FolioService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Catalogos")
@Route(value = "catalogos", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class Catalogos extends VerticalLayout{
    private Grid<FolioDAO> grid;
    private TextField folioFilter,usuarioreportaFilter,marcaFilter,modeloFilter,
            numeroSerieFilter,numeroInventarioFilter,estadoFilter,unidadFilter;

    private final FolioService folioService;

    public Catalogos(FolioService folioService) {
        this.folioService = folioService;

        grid = new Grid<>(FolioDAO.class, false);
        grid.setItems(folioService.getAll());

        grid.addColumn(FolioDAO :: getId).setHeader("Folio").setKey("id").setResizable(true);
        grid.addColumn(FolioDAO :: getUnidad).setHeader("Unidad").setKey("unidad").setResizable(true);
        grid.addColumn(FolioDAO :: getUsuarioReporta).setHeader("Usuario Reporta").setKey("usuarioReporta").setResizable(true);
        grid.addColumn(FolioDAO :: getMarca).setHeader("Marca").setKey("marca").setResizable(true);
        grid.addColumn(FolioDAO :: getModelo).setHeader("Modelo").setKey("modelo").setResizable(true);
        grid.addColumn(FolioDAO :: getNumeroSerie).setHeader("Numero Serie").setKey("numeroSerie").setResizable(true);
        grid.addColumn(FolioDAO :: getNumeroInventario).setHeader("Numero Inventario").setKey("numeroInventario").setResizable(true);
        grid.addColumn(FolioDAO :: getEstado).setHeader("Estado").setKey("estado").setResizable(true);

        prepareFilterFields();
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
                folioFilterMatch = item.toString().contains(folioFilter.getValue().toUpperCase());

            if(!unidadFilter.isEmpty())
                unidadFilterMatch = item.toString().contains(unidadFilter.getValue().toUpperCase());

            if(!usuarioreportaFilter.isEmpty())
                usuarioreportaFilterMatch = item.toString().contains(usuarioreportaFilter.getValue().toUpperCase());

            if(!marcaFilter.isEmpty())
                marcaFilterMatch = item.toString().contains(marcaFilter.getValue().toUpperCase());

            if(!modeloFilter.isEmpty())
                modeloFilterMatch = item.toString().contains(modeloFilter.getValue().toUpperCase());

            if(!numeroSerieFilter.isEmpty())
                numeroSerieFilterMatch = item.toString().contains(numeroSerieFilter.getValue().toUpperCase());

            if(!numeroInventarioFilter.isEmpty())
                numeroInventarioFilterMatch = item.toString().contains(numeroInventarioFilter.getValue().toUpperCase());

            if(!estadoFilter.isEmpty())
                estadoFilterMatch = item.toString().contains(estadoFilter.getValue().toUpperCase());

            return folioFilterMatch && unidadFilterMatch && usuarioreportaFilterMatch && marcaFilterMatch &&
                    modeloFilterMatch && numeroSerieFilterMatch && numeroInventarioFilterMatch && estadoFilterMatch;
        });
    }

}
