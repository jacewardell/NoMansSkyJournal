package com.example.sadostrich.Utils;

/**
 * Class to contain all enums that will be used within the app
 * <p/>
 * Created by jacewardell on 7/2/15.
 */
public class Enums {

    public enum DiscoveryType {
        PLANET("Planet"), ANIMAL("Animal"), PLANT("Plant");

        String friendlyName;

        private DiscoveryType(String friendlyName) {
            this.friendlyName = friendlyName;
        }

        @Override
        public String toString() {
            return friendlyName;
        }
    }

    public enum PlanetSize {
        XX_SMALL("XX-Small"), X_SMALL("X-Small"), SMALL("Small"), MEDIUM("Medium"), LARGE("Large"), X_LARGE("X-Large"), XX_LARGE("XX-Large");

        String friendlyName;

        private PlanetSize(String friendlyName) {
            this.friendlyName = friendlyName;
        }

        /**
         * Gets the enum by the friendly name
         *
         * @param value friendly name
         * @return PlanetSize
         */
        public static PlanetSize getByFriendlyName(String value) {
            for (PlanetSize size : values()) {
                if (size.friendlyName.equals(value)) {
                    return size;
                }
            }
            return MEDIUM;
        }

        @Override
        public String toString() {
            return friendlyName;
        }
    }

    /**
     * Represents the animals diet
     * Carnivore = meat eater
     * Omnivore = meat and plant eater
     * Herbivore = plant eater
     */
    public enum AnimalDiet {
        UNKNOWN("Unknown"), CARNIVORE("Carnivore"), OMNIVORE("Omnivore"), HERBIVORE("Herbivore");

        String friendlyName;

        private AnimalDiet(String friendlyName) {
            this.friendlyName = friendlyName;
        }

        @Override
        public String toString() {
            return friendlyName;
        }
    }
}