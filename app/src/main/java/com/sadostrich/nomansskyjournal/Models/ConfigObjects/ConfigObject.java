package com.sadostrich.nomansskyjournal.Models.ConfigObjects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jacewardell on 8/5/16.
 */
public class ConfigObject {
    private String plural, singular, type, parent;
    @SerializedName("selects")
    private List<ConfigSpinner> configSpinners;
    @SerializedName("sliders")
    private List<ConfigSpinner> configSliders;
    @SerializedName("checkboxes")
    private List<ConfigCheckBox> configCheckBoxes;

    public ConfigObject(String plural, String singular, String type, String parent, List<ConfigSpinner> configSpinners, List<ConfigSpinner> configSliders, List<ConfigCheckBox> configCheckBoxes) {
        this.plural = plural;
        this.singular = singular;
        this.type = type;
        this.parent = parent;
        this.configSpinners = configSpinners;
        this.configSliders = configSliders;
        this.configCheckBoxes = configCheckBoxes;
    }

    public String getPlural() {
        return plural;
    }

    public void setPlural(String plural) {
        this.plural = plural;
    }

    public String getSingular() {
        return singular;
    }

    public void setSingular(String singular) {
        this.singular = singular;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<ConfigSpinner> getConfigSpinners() {
        return configSpinners;
    }

    public void setConfigSpinners(List<ConfigSpinner> configSpinners) {
        this.configSpinners = configSpinners;
    }

    public List<ConfigSpinner> getConfigSliders() {
        return configSliders;
    }

    public void setConfigSliders(List<ConfigSpinner> configSliders) {
        this.configSliders = configSliders;
    }

    public List<ConfigCheckBox> getConfigCheckBoxes() {
        return configCheckBoxes;
    }

    public void setConfigCheckBoxes(List<ConfigCheckBox> configCheckBoxes) {
        this.configCheckBoxes = configCheckBoxes;
    }
}
