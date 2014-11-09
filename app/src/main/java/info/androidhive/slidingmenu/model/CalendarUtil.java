package info.androidhive.slidingmenu.model;

import android.text.format.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by fukuokak on 2014/11/09.
 */
public class CalendarUtil {

    public int compareCalendar(Calendar beComparedCalendar , Calendar baseCalendar){
        beComparedCalendar.set(Calendar.HOUR_OF_DAY , 0);
        beComparedCalendar.set(Calendar.MINUTE , 0);
        beComparedCalendar.set(Calendar.SECOND , 0);
        beComparedCalendar.set(Calendar.MILLISECOND , 0);

        baseCalendar.set(Calendar.HOUR_OF_DAY , 0);
        baseCalendar.set(Calendar.MINUTE , 0);
        baseCalendar.set(Calendar.SECOND , 0);
        baseCalendar.set(Calendar.MILLISECOND , 0);

        Date beComparedDate = beComparedCalendar.getTime();
        Date baseDate = baseCalendar.getTime();

        int diff = beComparedDate.compareTo(baseDate);

        return diff;
    }

}
