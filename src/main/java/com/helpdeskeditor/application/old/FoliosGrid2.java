package com.helpdeskeditor.application.old;

import com.helpdeskeditor.application.app.web.MainLayout;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
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

@PageTitle("Listado de Folios2")
@Route(value = "foliosgrid2", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)
//@RolesAllowed("USER")
public class FoliosGrid2 extends HorizontalLayout {
    private Grid<LocalDate> grid;
    private TextField toStringFilter, yearFilter;
    private ComboBox<Month> monthFilter;
    private ComboBox<DayOfWeek> dayOfWeekFilter;

    public FoliosGrid2() {
        grid = new Grid<>(LocalDate.class, false);
        List< LocalDate > dates = List.of(
                LocalDate.of( 2020 , Month.JANUARY , 23 ) ,
                LocalDate.of( 2019 , Month.FEBRUARY , 24 ) ,
                LocalDate.of( 2022 , Month.MARCH , 25 ) ,
                LocalDate.of( 2011 , Month.APRIL , 26 ) ,
                LocalDate.of( 2022 , Month.APRIL , 23 )
        );
        grid.setItems( new ArrayList< LocalDate >( dates ) );
        grid.addColumn( LocalDate :: toString ).setHeader("String Representation").setKey("tostring");
        grid.addColumn( LocalDate :: getYear ).setHeader("Year").setKey("year");
        grid.addColumn( LocalDate :: getMonth ).setHeader("Month").setKey("month");
        grid.addColumn( LocalDate :: getDayOfWeek ).setHeader("Day Of Week").setKey("dayofweek");

        prepareFilterFields();
        add(grid);


    }
    private void prepareFilterFields() {
        HeaderRow headerRow = grid.appendHeaderRow();

        toStringFilter = gridTextFieldFilter("tostring", headerRow);
        yearFilter = gridTextFieldFilter("year", headerRow);
        monthFilter = gridComboBoxFilter("month", headerRow, Month::toString, Month.values());
        dayOfWeekFilter = gridComboBoxFilter("dayofweek", headerRow, DayOfWeek::toString, DayOfWeek.values());
    }

    private <T> ComboBox<T> gridComboBoxFilter(String columnKey, HeaderRow headerRow, ItemLabelGenerator<T> itemLabelGenerator, T... items) {
        ComboBox<T> filter = new ComboBox<>();
        filter.addValueChangeListener(event -> this.onFilterChange());
        filter.setItemLabelGenerator(itemLabelGenerator);
        filter.setItems(items);
        filter.setWidthFull();
        filter.setClearButtonVisible(true);
        headerRow.getCell(grid.getColumnByKey(columnKey)).setComponent(filter);
        return filter;
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
        ListDataProvider<LocalDate> listDataProvider = (ListDataProvider<LocalDate>) grid.getDataProvider();
        // Since this will be the only active filter, it needs to account for all values of my filter fields
        listDataProvider.setFilter(item -> {
            boolean toStringFilterMatch = true;
            boolean yearFilterMatch = true;
            boolean monthFilterMatch = true;
            boolean dayOfWeekFilterMatch = true;

            if(!toStringFilter.isEmpty()){
                toStringFilterMatch = item.toString().contains(toStringFilter.getValue());
            }
            if(!yearFilter.isEmpty()){
                yearFilterMatch = String.valueOf(item.getYear()).contains(yearFilter.getValue());
            }
            if(!monthFilter.isEmpty()){
                monthFilterMatch = item.getMonth().equals(monthFilter.getValue());
            }
            if(!dayOfWeekFilter.isEmpty()){
                dayOfWeekFilterMatch = item.getDayOfWeek().equals(dayOfWeekFilter.getValue());
            }

            return toStringFilterMatch && yearFilterMatch && monthFilterMatch && dayOfWeekFilterMatch;
        });
    }

}
