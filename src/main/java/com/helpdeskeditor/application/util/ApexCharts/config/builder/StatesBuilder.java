package com.helpdeskeditor.application.util.ApexCharts.config.builder;

import com.helpdeskeditor.application.util.ApexCharts.config.States;
import com.helpdeskeditor.application.util.ApexCharts.config.states.Active;
import com.helpdeskeditor.application.util.ApexCharts.config.states.Hover;
import com.helpdeskeditor.application.util.ApexCharts.config.states.Normal;

public class StatesBuilder {
    private Normal normal;
    private Hover hover;
    private Active active;

    private StatesBuilder() {
    }

    public static StatesBuilder get() {
        return new StatesBuilder();
    }

    public StatesBuilder withNormal(Normal normal) {
        this.normal = normal;
        return this;
    }

    public StatesBuilder withHover(Hover hover) {
        this.hover = hover;
        return this;
    }

    public StatesBuilder withActive(Active active) {
        this.active = active;
        return this;
    }

    public States build() {
        States states = new States();
        states.setNormal(normal);
        states.setHover(hover);
        states.setActive(active);
        return states;
    }
}
