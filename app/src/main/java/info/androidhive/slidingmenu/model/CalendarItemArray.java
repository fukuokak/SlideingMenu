package info.androidhive.slidingmenu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by fukuokak on 2014/10/24.
 */
public class CalendarItemArray extends ArrayList<CalendarItemArray> implements Serializable {
    private ArrayList<CalendarItem> ciArray = new ArrayList<CalendarItem>();

    final static int loopCount = 7;

    public CalendarItemArray(Calendar calendar) {
        for (int i = 0; i <= loopCount; i++) {
            CalendarItem ci = new CalendarItem(calendar);
            this.ciArray.add(ci);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    public ArrayList<CalendarItem> getCalendarItemArray(){
        return this.ciArray ;
    }

    public CalendarItem get(Integer i) {
        return this.ciArray.get(i);
    }
}
