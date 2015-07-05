package com.example.sadostrich.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Helper method used for database creation and other manipulations
 * <p/>
 * Created by jacewardell on 7/3/15.
 */
public final class DiscoveriesContract {
    public DiscoveriesContract() {
    }

    /**
     * Class for the Planets table
     */
    public static abstract class PlanetsTable implements BaseColumns {
        public static final String COLUMN_NAME_NULLABLE = "null";

        public static final String PLANET_TABLE_NAME = "planets";
        public static final String COLUMN_NAME_PLANET_ID = "planetId";
        public static final String COLUMN_NAME_PLANET_DATE = "planetDate";
        public static final String COLUMN_NAME_PLANET_COMMON_NAME = "planetCommonName";
        public static final String COLUMN_NAME_PLANET_SCIENTIFIC_NAME = "planetScientificName";
        public static final String COLUMN_NAME_PLANET_DESCRIPTION = "planetDescription";
        public static final String COLUMN_NAME_PLANET_STORY = "planetStory";
        public static final String COLUMN_NAME_PLANET_IMAGE_URL = "planetImageUrl";
        public static final String COLUMN_NAME_PLANET_SOLAR_SYSTEM_NAME = "planetSolarSystemName";
        public static final String COLUMN_NAME_PLANET_SIZE = "planetSize";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + PLANET_TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," + COLUMN_NAME_PLANET_ID +
                TEXT_TYPE + COMMA_SEP + COLUMN_NAME_PLANET_DATE + TEXT_TYPE + COMMA_SEP + COLUMN_NAME_PLANET_COMMON_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_PLANET_SCIENTIFIC_NAME + TEXT_TYPE + COMMA_SEP + COLUMN_NAME_PLANET_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_PLANET_STORY + TEXT_TYPE + COMMA_SEP + COLUMN_NAME_PLANET_IMAGE_URL + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_PLANET_SOLAR_SYSTEM_NAME + TEXT_TYPE + COMMA_SEP + COLUMN_NAME_PLANET_SIZE + " )";
        private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + PLANET_TABLE_NAME;

        /**
         *
         */
        public static class DiscoveriesDbHelper extends SQLiteOpenHelper {
            // If you change the database schema, increment this
            public static final int DATABASE_VERSION = 1;
            public static final String DATABASE_NAME = "Discoveries.db";

            public DiscoveriesDbHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(SQL_CREATE_ENTRIES);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                // TODO: do this crap
            }
        }
    }
}
