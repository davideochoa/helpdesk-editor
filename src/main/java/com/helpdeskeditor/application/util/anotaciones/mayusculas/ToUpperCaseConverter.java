package com.helpdeskeditor.application.util.anotaciones.mayusculas;

import com.fasterxml.jackson.databind.util.StdConverter;

public class ToUpperCaseConverter  extends StdConverter<String, String> {
    @Override
    public String convert(String value) {
        if (value == null){
            return null;
        }
        return value.toUpperCase();
    }
}