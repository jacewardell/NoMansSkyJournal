package com.sadostrich.nomansskyjournal.Utils;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.ColorRes;

import com.sadostrich.nomansskyjournal.Models.CustomSpinnerObject;
import com.sadostrich.nomansskyjournal.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by jacewardell on 7/25/15.
 */
public class MiscUtil {

    public static String getImagePath(Context context, Uri imageUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(context, imageUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    public static Uri getImageUri(String imageUrl) {
        if (imageUrl != null) {
            return Uri.parse(imageUrl);
        }
        return null;
    }

    public static List<CustomSpinnerObject> getSystemTypeOptions() {
        List<CustomSpinnerObject> options = new ArrayList<>();
        options.add(new CustomSpinnerObject(Enums.SystemType.UNARY, R.color.system_purple));
        options.add(new CustomSpinnerObject(Enums.SystemType.BINARY, R.color.system_purple));
        options.add(new CustomSpinnerObject(Enums.SystemType.TERNARY, R.color.system_purple));
        options.add(new CustomSpinnerObject(Enums.SystemType.QUATERNARY, R.color.system_purple));
        options.add(new CustomSpinnerObject(Enums.SystemType.QUINARY, R.color.system_purple));
        options.add(new CustomSpinnerObject(Enums.SystemType.SENARY, R.color.system_purple));
        options.add(new CustomSpinnerObject(Enums.SystemType.SEPTENARY, R.color.system_purple));
        return options;
    }

    public static List<CustomSpinnerObject> getSystemPlanetCountOptions() {
        List<CustomSpinnerObject> options = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            options.add(new CustomSpinnerObject("" + i, R.color.system_purple));
        }
        options.add(new CustomSpinnerObject("More", R.color.system_purple));
        options.add(new CustomSpinnerObject("Over 9000!", R.color.system_purple));
        return options;
    }

    public static List<CustomSpinnerObject> getDiscoveryLifeOptions(Context context, @ColorRes int backgroundRes) {
        List<CustomSpinnerObject> options = new ArrayList<>();

        String[] lifeArray = context.getResources().getStringArray(R.array.array_life_options);
        for (int i = 0; i < lifeArray.length; i++) {
            String lifeString = lifeArray[i];
            options.add(new CustomSpinnerObject(lifeString, backgroundRes));
        }
        return options;
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static List<CustomSpinnerObject> getDiscoverySizeOptions(Context context, @ColorRes int backgroundRes) {
        List<CustomSpinnerObject> options = new ArrayList<>();

        String[] discoverySizes = context.getResources().getStringArray(R.array.array_discovery_sizes);
        for (int i = 0; i < discoverySizes.length; i++) {
            String size = discoverySizes[i];
            options.add(new CustomSpinnerObject(size, backgroundRes));
        }
        return options;
    }

    public static List<CustomSpinnerObject> getStarTypeOptions(Context context) {
        List<CustomSpinnerObject> options = new ArrayList<>();

        String[] starTypes = context.getResources().getStringArray(R.array.array_star_types);
        for (int i = 0; i < starTypes.length; i++) {
            String type = starTypes[i];
            options.add(new CustomSpinnerObject(type, R.color.star_yellow));
        }
        return options;
    }

    public static List<CustomSpinnerObject> getPlanetTemperatureOptions(Context context) {
        List<CustomSpinnerObject> options = new ArrayList<>();

        String[] starSizes = context.getResources().getStringArray(R.array.array_temperatures);
        for (int i = 0; i < starSizes.length; i++) {
            String size = starSizes[i];
            options.add(new CustomSpinnerObject(size, R.color.planet_purple));
        }
        return options;
    }

    public static List<CustomSpinnerObject> getPlanetResourcesOptions(Context context) {
        List<CustomSpinnerObject> options = new ArrayList<>();

        String[] starSizes = context.getResources().getStringArray(R.array.array_resources);
        for (int i = 0; i < starSizes.length; i++) {
            String size = starSizes[i];
            options.add(new CustomSpinnerObject(size, R.color.planet_purple));
        }
        return options;
    }

    public static List<CustomSpinnerObject> getFaunaSizeOptions(Context context) {
        List<CustomSpinnerObject> options = new ArrayList<>();

        String[] faunaSizes = context.getResources().getStringArray(R.array.array_fauna_sizes);
        for (int i = 0; i < faunaSizes.length; i++) {
            String size = faunaSizes[i];
            options.add(new CustomSpinnerObject(size, R.color.fauna_red));
        }
        return options;
    }

    public static List<CustomSpinnerObject> getFaunaBehaviorOptions(Context context) {
        List<CustomSpinnerObject> options = new ArrayList<>();

        String[] faunaBehaviors = context.getResources().getStringArray(R.array.array_fauna_behavior);
        for (int i = 0; i < faunaBehaviors.length; i++) {
            String size = faunaBehaviors[i];
            options.add(new CustomSpinnerObject(size, R.color.fauna_red));
        }
        return options;
    }

    public static List<CustomSpinnerObject> getStructureTypeOptions(Context context) {
        List<CustomSpinnerObject> options = new ArrayList<>();

        String[] structureTypes = context.getResources().getStringArray(R.array.array_structure_type);
        for (int i = 0; i < structureTypes.length; i++) {
            String type = structureTypes[i];
            options.add(new CustomSpinnerObject(type, R.color.structure_green));
        }
        return options;
    }
}
