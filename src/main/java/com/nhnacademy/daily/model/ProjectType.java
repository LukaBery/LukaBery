package com.nhnacademy.daily.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProjectType {
    PUBLIC, PRIVATE;
    @JsonValue
    public String toJson(){
        return this.name().toLowerCase();
    }
}

