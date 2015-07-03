package com.example.sadostrich.Objects;

import com.example.sadostrich.Utils.Enums;

import java.util.Date;

/**
 * Represents a discovered planet
 * <p>
 * Created by jacewardell on 7/2/15.
 */
public class Planet extends Discovery {
    private String solarSystemName;
    private Enums.PlanetSize size;

    /**
     * Default constructor
     */
    public Planet() {
        super();
        solarSystemName = "";
        size = Enums.PlanetSize.MEDIUM;
    }

    /**
     * Creates a Planet using the parameters
     *
     * @param date            date
     * @param scientificName  scientific name
     * @param commonName      common name,
     * @param description     description
     * @param story           story
     * @param imageUrl        image url
     * @param solarSystemName solar system name
     * @param size            size
     */
    public Planet(Date date, String scientificName, String commonName, String description, String story, String imageUrl, String solarSystemName, Enums.PlanetSize size) {
        super(date, scientificName, commonName, description, story, imageUrl);
        this.solarSystemName = solarSystemName;
        this.size = size;
    }

    /**
     * Returns the solar system name
     *
     * @return solarSystemName
     */
    public String getSolarSystemName() {
        return solarSystemName;
    }

    /**
     * Sets the solar system name
     *
     * @param solarSystemName solar system name
     */
    public void setSolarSystemName(String solarSystemName) {
        this.solarSystemName = solarSystemName;
    }

    /**
     * Returns the size of the planet
     *
     * @return size
     */
    public Enums.PlanetSize getSize() {
        return size;
    }

    /**
     * Sets the size of the planet
     *
     * @param size size
     */
    public void setSize(Enums.PlanetSize size) {
        this.size = size;
    }
}
