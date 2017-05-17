package edu.sce.tom.physotrack;

import android.graphics.Color;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class Utils {
    //constants
    //expressions
    public static final String SMILE_EXP = "Smile";
    public static final String KISS_EXP = "Kiss";
    public static final String NATURAL_EXP = "Blankly";
    public static final String EYEBROWRAISED_EXP = "EyebrowRaised";
    public static final String EYECLOSED_EXP = "EyesClosed";
    public static final String UPPERLIPRAISED_EXP = "Rabbit";

    //name of the shared preferences file and keys
    public static final String USER_DETAILS_SP_FILE = "userDetails";
    public static final String TODAYS_DATE = "todaysDate";
    public static final String USER_NAME = "userName";
    public static final String SIDE = "defSide";
    public static final String THERAPIST_MAIL = "therapistMail";
    public static final String NEW_SESSION_SP_FILE = "newSession";

    //Graphs Colors//
    public static final int Colors[]={Color.rgb(40,220,117),Color.rgb(255,211,5),Color.rgb(251,75,56),Color.rgb(47,161,237),Color.rgb(231,54,218),Color.rgb(250,111,35)};

    //returns todays date as a string
    public static String todaysDateToString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }

    //takes a string date and returns a date object
    public static Date stringToDate(String string) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = format.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //Convert a date to string
    public static String dateToString(Date date) {
        if(date!=null){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.format(date);
        }
        else
            return todaysDateToString();
    }
}
