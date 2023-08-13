package com.helpdeskeditor.application.util.ApexCharts.config;


import com.helpdeskeditor.application.util.ApexCharts.config.states.Active;
import com.helpdeskeditor.application.util.ApexCharts.config.states.Hover;
import com.helpdeskeditor.application.util.ApexCharts.config.states.Normal;

public class States {
    private Normal normal;
    private Hover hover;
    private Active active;


    public States() {
    }

    public Normal getNormal() {
        return normal;
    }

    public Hover getHover() {
        return hover;
    }

    public Active getActive() {
        return active;
    }

    public void setNormal(Normal normal) {
        this.normal = normal;
    }

    public void setHover(Hover hover) {
        this.hover = hover;
    }

    public void setActive(Active active) {
        this.active = active;
    }

}