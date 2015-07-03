package com.example.sadostrich.Objects;

import com.example.sadostrich.Utils.Enums;

import java.util.Date;

/**
 * Represents a discovered animal
 * <p>
 * Created by jacewardell on 7/2/15.
 */
public class Animal extends Discovery {
    private String planetName;
    private Enums.AnimalDiet diet;


    /**
     * Default constructor
     */
    public Animal() {
        super();
        this.planetName = "";
        this.diet = Enums.AnimalDiet.UNKNOWN;
    }

    /**
     * Creates a Planet using the parameters
     *
     * @param date           date
     * @param scientificName scientific name
     * @param commonName     common name
     * @param description    description
     * @param story          story
     * @param imageUrl       image url
     * @param planetName     planet name
     * @param diet           diet
     */
    public Animal(Date date, String scientificName, String commonName, String description, String story, String imageUrl, String planetName, Enums.AnimalDiet diet) {
        super(date, scientificName, commonName, description, story, imageUrl);
        this.planetName = planetName;
        this.diet = diet;
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

    /**
     * Returns the diet of the animal
     *
     * @return diet
     */
    public Enums.AnimalDiet getDiet() {
        return diet;
    }

    /**
     * Sets the diet of the animal
     *
     * @param diet diet
     */
    public void setDiet(Enums.AnimalDiet diet) {
        this.diet = diet;
    }
}
