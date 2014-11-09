package info.androidhive.slidingmenu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

/**
 * Created by fukuokak on 2014/10/20.
 */
public class CalendarItem {
    private Calendar calendar;
    private Integer year;
    private Integer month;
    private Integer day;
    private int dayName;
    private String listText;
    private String dayNameFlag;
    private String[] week_name = {"","Sun", "Mon", "Tue", "Wed",
            "Thu", "Fri", "Sat"};
    static String ARG_EXTRA_KEY_CALENDAR = "calendar";

    public CalendarItem(Calendar calendar) {
        this.calendar = calendar ;
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.day = calendar.get(Calendar.DATE);
        this.dayName = calendar.get(Calendar.DAY_OF_WEEK);
        this.listText = String.valueOf(month+1) + "/"
                + day.toString() + "(" + week_name[dayName] + ")";
        this.dayNameFlag = week_name[dayName];
    }

    public Calendar getCalendar(){return calendar;};

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getDay() {
        return day;
    }

    public int getDayName() {
        return dayName;
    }

    public String getListText() {
        return listText;
    }

    public String getDayNameFlag() {
        return dayNameFlag;
    }

    public String[] getWeek_name() {
        return week_name;
    }


}
