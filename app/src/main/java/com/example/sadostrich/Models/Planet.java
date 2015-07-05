package com.example.sadostrich.Models;

import com.example.sadostrich.Fragments.AddPlanetFragment;
import com.example.sadostrich.Utils.Enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a discovered planet
 * <p>
 * Created by jacewardell on 7/2/15.
 */
public class Planet extends Discovery implements Serializable {
    private String solarSystemName;
    private Enums.PlanetSize size;
    private List<Animal> animals;
    private List<Plant> plants;

    /**
     * Default constructor
     */
    public Planet() {
        super();
        solarSystemName = "";
        size = Enums.PlanetSize.MEDIUM;
        animals = new ArrayList<>();
        plants = new ArrayList<>();
    }

    /**
     * Creates a Planet using the parameters
     *
     * @param date            date
     * @param commonName      common name,
     * @param scientificName  scientific name
     * @param description     description
     * @param story           story
     * @param imageUrl        image url
     * @param solarSystemName solar system name
     * @param size            size
     */
    public Planet(String date, String commonName, String scientificName, String description, String story, String imageUrl, String solarSystemName,
                  Enums.PlanetSize size) {
        super(date, commonName, scientificName, description, story, imageUrl);
        this.solarSystemName = solarSystemName;
        this.size = size;
        animals = new ArrayList<>();
        plants = new ArrayList<>();
    }

    public Planet(AddPlanetFragment addPlanetFragment) {
        super(addPlanetFragment.getDate(), addPlanetFragment.getCommonNameText(), addPlanetFragment.getScientificNameText(), addPlanetFragment.getDescriptionText(),
                addPlanetFragment.getStoryText(), "");
        this.solarSystemName = addPlanetFragment.getSolarSystemNameText();
        this.size = addPlanetFragment.getSizeSpinnerSelection();
        animals = new ArrayList<>();
        plants = new ArrayList<>();
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

    /**
     * Returns a list of all the animals on this planet
     *
     * @return animals
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * Sets the list of all the animals on this planet
     *
     * @param animals list of animals
     */
    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    /**
     * Returns a list of all the plants on this planet
     *
     * @return plants
     */
    public List<Plant> getPlants() {
        return plants;
    }

    /**
     * Sets the list of all the plants on this planet
     *
     * @param plants list of plants
     */
    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
