package com.sadostrich.nomansskyjournal.Utils;

import android.content.Context;
import android.text.format.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by jacewardell on 7/25/15.
 */
public class Formatter {
    public static DateFormat detailedDateFormat = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'");
    public static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static String calculateTimeAgo(Context context, String dateString) {
        try {
            DateFormat formatter = detailedDateFormat;
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date = formatter.parse(dateString);
//            return DateUtils.getRelativeDateTimeString(context, date.getTime(), DateUtils.MINUTE_IN_MILLIS, DateUtils.YEAR_IN_MILLIS, DateUtils
//                    .FORMAT_ABBREV_ALL).toString();
            return DateUtils.getRelativeTimeSpanString(date.getTime(), Calendar.getInstance().getTime().getTime(), DateUtils.SECOND_IN_MILLIS,
                    DateUtils.FORMAT_ABBREV_RELATIVE).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        } return "";
    }
}
