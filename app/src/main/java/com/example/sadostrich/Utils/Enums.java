package com.example.sadostrich.Utils;

/**
 * Class to contain all enums that will be used within the app
 * <p>
 * Created by jacewardell on 7/2/15.
 */
public class Enums {

    public enum PlanetSize {
        XX_SMALL, X_SMALL, SMALL, MEDIUM, LARGE, X_LARGE, XX_LARGE;
    }

    /**
     * Represents the animals diet
     * Carnivore = meat eater
     * Omnivore = meat and plant eater
     * Herbivore = plant eater
     */
    public enum AnimalDiet {
        UNKNOWN, CARNIVORE, OMNIVORE, HERBIVORE;
    }
}