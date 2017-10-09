package com.rao2100.starter.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JournalEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("__CURSOR")
    private String cursor;
    @JsonProperty("UNIT")
    private String unit;
    @JsonProperty("MESSAGE")
    private String message;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
