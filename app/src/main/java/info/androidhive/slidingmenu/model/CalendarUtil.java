package info.androidhive.slidingmenu.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by fukuokak on 2014/11/09.
 */
public class CalendarUtil {

    public int compareCalendar(Calendar beComparedCalendar , Calendar baseCalendar){
        Date beComparedDate = setZeroTimeStamp(beComparedCalendar).getTime();
        Date baseDate = setZeroTimeStamp(baseCalendar).getTime();

        int diff = beComparedDate.compareTo(baseDate);

        return diff;
    }

    public Long generateTimeStamp() throws InterruptedException {
        Thread.sleep(1);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sidf = new SimpleDateFormat("yyyyMMddhhmmssS");
        return Long.parseLong(sidf.format(date).toString());
    }

    public Calendar setZeroTimeStamp(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

}
