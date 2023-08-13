package com.helpdeskeditor.application.util.ApexCharts.config.builder;

import com.helpdeskeditor.application.util.ApexCharts.config.series.SeriesType;
import com.helpdeskeditor.application.util.ApexCharts.helper.Series;

public class SeriesBuilder<T> {
	private String name;
	private SeriesType type;
	private T[] data;

    private SeriesBuilder() {
    }

    public static SeriesBuilder<?> get() {
        return new SeriesBuilder();
    }

    public SeriesBuilder<T> withName(String name) {
        this.name = name;
        return this;
    }
    
    public SeriesBuilder<T> withType(SeriesType type) {
        this.type = type;
        return this;
    }
    
    public SeriesBuilder<T> withData(T... data) {
        this.data = data;
        return this;
    }

    public Series<T> build() {
        Series<T> series = new Series<T>();
        series.setName(name);
        series.setType(type);
        series.setData(data);
        return series;
    }
}
