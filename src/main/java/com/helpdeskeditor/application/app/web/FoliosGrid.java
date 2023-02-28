package com.helpdeskeditor.application.app.web;

import com.helpdeskeditor.application.app.data.DAO.FolioDAO;
import com.helpdeskeditor.application.app.service.FolioService;
import com.helpdeskeditor.application.app.web.MainLayout;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Listado de Folios")
@Route(value = "foliosgrid", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class FoliosGrid extends HorizontalLayout {
    private Grid<FolioDAO> grid;
    private TextField folioFilter;//,unidadFilter,areaFilter,usuarioreportaFilter;
    //private ComboBox<Month> monthFilter;
    //private ComboBox<DayOfWeek> dayOfWeekFilter;

    private final FolioService folioService;

    public FoliosGrid(FolioService folioService) {
        this.folioService = folioService;

        grid = new Grid<>(FolioDAO.class, false);
        grid.setItems(folioService.getAll());
        grid.addColumn( FolioDAO :: getFolio ).setHeader("Folio").setKey("folio");
        //grid.addColumn( FolioDAO :: getUnidad ).setHeader("Unidad").setKey("unidad");
        //grid.addColumn( FolioDAO :: getArea ).setHeader("Area").setKey("area");
        //grid.addColumn( FolioDAO :: getUsuarioReporta ).setHeader("Usuario Reporta").setKey("usuarioreporta");

        prepareFilterFields();
        add(grid);


    }
    private void prepareFilterFields() {
        HeaderRow headerRow = grid.appendHeaderRow();

        folioFilter = gridTextFieldFilter("folio", headerRow);/*
        unidadFilter = gridTextFieldFilter("unidad", headerRow);
        areaFilter = gridTextFieldFilter("area", headerRow);
        usuarioreportaFilter = gridTextFieldFilter("usuarioreporta", headerRow);*/
    }

    private TextField gridTextFieldFilter(String columnKey, HeaderRow headerRow) {
        TextField filter = new TextField();
        filter.setValueChangeMode(ValueChangeMode.TIMEOUT);
        filter.addValueChangeListener(event -> this.onFilterChange());
        filter.setWidthFull();
        headerRow.getCell(grid.getColumnByKey(columnKey)).setComponent(filter);
        return filter;
    }

    private void onFilterChange(){
        ListDataProvider<FolioDAO> listDataProvider = (ListDataProvider<FolioDAO>) grid.getDataProvider();
        // Since this will be the only active filter, it needs to account for all values of my filter fields
        listDataProvider.setFilter(item -> {
            boolean toStringFilterMatch = true;
            boolean yearFilterMatch = true;
            boolean monthFilterMatch = true;
            boolean dayOfWeekFilterMatch = true;

            if(!folioFilter.isEmpty()){
                toStringFilterMatch = item.toString().contains(folioFilter.getValue());
            }
/*
            if(!unidadFilter.isEmpty()){
                yearFilterMatch = String.valueOf(item.getUnidad()).contains(unidadFilter.getValue());
            }
            if(!areaFilter.isEmpty()){
                monthFilterMatch = item.getArea().equals(areaFilter.getValue());
            }

            if(!usuarioreportaFilter.isEmpty()){
                dayOfWeekFilterMatch = item.getUsuarioReporta().equals(usuarioreportaFilter.getValue());
            }*/

            return toStringFilterMatch && yearFilterMatch && monthFilterMatch && dayOfWeekFilterMatch;
        });
    }

}
