package com.sadostrich.nomansskyjournal.Utils;

import android.support.annotation.StringRes;

import com.sadostrich.nomansskyjournal.R;

/**
 * Class to contain all enums that will be used within the app
 * <p/>
 * Created by jacewardell on 7/2/15.
 */
public class Enums {

    public enum DiscoveryType {
        SOLAR_SYSTEM(R.string.title_solar_system),
        STAR(R.string.title_star),
        STATION(R.string.title_station),
        PLANET(R.string.title_planet),
        FAUNA(R.string.title_fauna),
        FLORA(R.string.title_flora),
        STRUCTURE(R.string.title_structure),
        ITEM(R.string.title_item),
        SHIP(R.string.title_ship);

        int nameRes;

        private DiscoveryType(@StringRes int nameRes) {
            this.nameRes = nameRes;
        }

        public int getNameRes() {
            return nameRes;
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

        public static String[] toStringArray() {
            String[] array = new String[values().length];
            for (int i = 0; i < values().length; i++) {
                array[i] = values()[i].toString();
            }

            return array;
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