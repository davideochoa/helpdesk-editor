package com.helpdeskeditor.application.util.ApexCharts.config.builder;

import com.helpdeskeditor.application.util.ApexCharts.config.ForecastDataPoints;

public class ForecastDataPointsBuilder {
    private Integer count;

    public static ForecastDataPointsBuilder get() {
        return new ForecastDataPointsBuilder();
    }


    public ForecastDataPointsBuilder withCount(Integer count) {
        this.count = count;
        return this;
    }


    public ForecastDataPoints build() {
        ForecastDataPoints forecast = new ForecastDataPoints();
        if (count != null) {
            forecast.setCount(count);
        }
        return forecast;
    }

}
