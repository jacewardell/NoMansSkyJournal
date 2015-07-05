package com.example.sadostrich.Models;

import java.util.Date;

/**
 * Created by jacewardell on 7/2/15.
 */
public class Plant extends Discovery {
    private String planetName;

    /**
     * Default constructor
     */
    public Plant() {
        super();
        this.planetName = "";
    }

    /**
     * Creates a Planet using the parameters
     *
     * @param date           date
     * @param commonName     common name
     * @param scientificName scientific name
     * @param description    description
     * @param story          story
     * @param imageUrl       image url
     * @param planetName     planet name
     */
    public Plant(Date date, String commonName, String scientificName, String description, String story, String imageUrl, String planetName) {
        super(date, commonName, scientificName, description, story, imageUrl);
        this.planetName = planetName;
    }

    /**
     * Returns the name of the planet on which the animal lives
     *
     * @return planet name
     */
    public String getPlanetName() {
        return planetName;
    }

    /**
     * Sets the name of the planet on which the animal lives
     *
     * @param planetName planet name
     */
    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }
}

