package com.sadostrich.nomansskyjournal.Utils;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.sadostrich.nomansskyjournal.Models.CustomSpinnerObject;
import com.sadostrich.nomansskyjournal.R;

import java.util.ArrayList;
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
        options.add(new CustomSpinnerObject(Enums.SystemType.UNARY, R.color.system_purple_dark));
        options.add(new CustomSpinnerObject(Enums.SystemType.BINARY, R.color.system_purple_dark));
        options.add(new CustomSpinnerObject(Enums.SystemType.TERNARY, R.color.system_purple_dark));
        options.add(new CustomSpinnerObject(Enums.SystemType.QUATERNARY, R.color.system_purple_dark));
        options.add(new CustomSpinnerObject(Enums.SystemType.QUINARY, R.color.system_purple_dark));
        options.add(new CustomSpinnerObject(Enums.SystemType.SENARY, R.color.system_purple_dark));
        options.add(new CustomSpinnerObject(Enums.SystemType.SEPTENARY, R.color.system_purple_dark));
        return options;
    }

    public static List<CustomSpinnerObject> getSystemPlanetCountOptions() {
        List<CustomSpinnerObject> options = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            options.add(new CustomSpinnerObject("" + i, R.color.system_purple_dark));
        }
        options.add(new CustomSpinnerObject("More", R.color.system_purple_dark));
        options.add(new CustomSpinnerObject("Over 9000!", R.color.system_purple_dark));
        return options;
    }

    public static List<CustomSpinnerObject> getSystemLifeOptions() {
        List<CustomSpinnerObject> options = new ArrayList<>();
        options.add(new CustomSpinnerObject(-1, -1, R.color.system_purple_dark, R.string.unknown));
        options.add(new CustomSpinnerObject(-1, -1, R.color.system_purple_dark, R.string.yes));
        options.add(new CustomSpinnerObject(-1, -1, R.color.system_purple_dark, R.string.no));
        return options;
    }
}
