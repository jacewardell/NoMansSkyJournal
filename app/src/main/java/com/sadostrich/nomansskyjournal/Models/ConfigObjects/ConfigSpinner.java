package com.sadostrich.nomansskyjournal.Models.ConfigObjects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jacewardell on 8/5/16.
 */
public class ConfigSpinner {
    private String field, label;
    @SerializedName("default")
    private String defaultOption;
    private List<String> options;

    public ConfigSpinner(String field, String label, String defaultOption, List<String> options) {
        this.field = field;
        this.label = label;
        this.defaultOption = defaultOption;
        this.options = options;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDefaultOption() {
        return defaultOption;
    }

    public void setDefaultOption(String defaultOption) {
        this.defaultOption = defaultOption;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
