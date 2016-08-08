package com.sadostrich.nomansskyjournal.Models.ConfigObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jacewardell on 8/5/16.
 */
public class ConfigBaseObject {
    @SerializedName("system")
    private ConfigObject systemConfigObject;
    @SerializedName("star")
    private ConfigObject starConfigObject;
    @SerializedName("station")
    private ConfigObject stationConfigObject;
    @SerializedName("planet")
    private ConfigObject planetConfigObject;
    @SerializedName("fauna")
    private ConfigObject faunaConfigObject;
    @SerializedName("flora")
    private ConfigObject floraConfigObject;
    @SerializedName("structure")
    private ConfigObject structureConfigObject;
    @SerializedName("item")
    private ConfigObject itemConfigObject;
    @SerializedName("ship")
    private ConfigObject shipConfigObject;

    public ConfigObject getSystemConfigObject() {
        return systemConfigObject;
    }

    public void setSystemConfigObject(ConfigObject systemConfigObject) {
        this.systemConfigObject = systemConfigObject;
    }

    public ConfigObject getStarConfigObject() {
        return starConfigObject;
    }

    public void setStarConfigObject(ConfigObject starConfigObject) {
        this.starConfigObject = starConfigObject;
    }

    public ConfigObject getStationConfigObject() {
        return stationConfigObject;
    }

    public void setStationConfigObject(ConfigObject stationConfigObject) {
        this.stationConfigObject = stationConfigObject;
    }

    public ConfigObject getPlanetConfigObject() {
        return planetConfigObject;
    }

    public void setPlanetConfigObject(ConfigObject planetConfigObject) {
        this.planetConfigObject = planetConfigObject;
    }

    public ConfigObject getFaunaConfigObject() {
        return faunaConfigObject;
    }

    public void setFaunaConfigObject(ConfigObject faunaConfigObject) {
        this.faunaConfigObject = faunaConfigObject;
    }

    public ConfigObject getFloraConfigObject() {
        return floraConfigObject;
    }

    public void setFloraConfigObject(ConfigObject floraConfigObject) {
        this.floraConfigObject = floraConfigObject;
    }

    public ConfigObject getStructureConfigObject() {
        return structureConfigObject;
    }

    public void setStructureConfigObject(ConfigObject structureConfigObject) {
        this.structureConfigObject = structureConfigObject;
    }

    public ConfigObject getItemConfigObject() {
        return itemConfigObject;
    }

    public void setItemConfigObject(ConfigObject itemConfigObject) {
        this.itemConfigObject = itemConfigObject;
    }

    public ConfigObject getShipConfigObject() {
        return shipConfigObject;
    }

    public void setShipConfigObject(ConfigObject shipConfigObject) {
        this.shipConfigObject = shipConfigObject;
    }
}
