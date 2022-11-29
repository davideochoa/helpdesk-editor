package com.helpdeskeditor.application.app.facade;

import java.io.Serializable;

public interface BaseEntity <T extends Serializable> extends Serializable{
    T getId();

    void setId(T id);

    //default Map<String, String> toMap(){}
}
