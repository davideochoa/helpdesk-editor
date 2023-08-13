package com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.builder;

import com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.Candlestick;
import com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.candlestick.Colors;
import com.helpdeskeditor.application.util.ApexCharts.config.plotoptions.candlestick.Wick;

public class CandlestickBuilder {
    private Colors colors;
    private Wick wick;

    private CandlestickBuilder() {
    }

    public static CandlestickBuilder get() {
        return new CandlestickBuilder();
    }

    public CandlestickBuilder withColors(Colors colors) {
        this.colors = colors;
        return this;
    }

    public CandlestickBuilder withWick(Wick wick) {
        this.wick = wick;
        return this;
    }

    public Candlestick build() {
        Candlestick candlestick = new Candlestick();
        candlestick.setColors(colors);
        candlestick.setWick(wick);
        return candlestick;
    }
}
