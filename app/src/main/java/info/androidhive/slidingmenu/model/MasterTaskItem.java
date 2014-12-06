package info.androidhive.slidingmenu.model;

import java.util.Calendar;
import java.util.Date;

import info.androidhive.slidingmenu.Config;

/**
 * Created by fukuokak on 2014/11/26.
 */
public class MasterTaskItem {
    private Long MasterTaskNum;
    private String taskTitle;
    private String repeatPattern;
    private Integer theDay;
    private String[] theWeekDay;
    private String doTime;

    public MasterTaskItem(Long MasterTaskNum, String taskTitle, String repeatPattern, Integer theDay, String[] theWeekDay, String doTime) throws NullPointerException {

        this.MasterTaskNum = MasterTaskNum;
        this.taskTitle = taskTitle;
        if (repeatPattern.equals(Config.TASK_INTERVAL_DAILY) ||
                repeatPattern.equals(Config.TASK_INTERVAL_WEEKLY) ||
                repeatPattern.equals(Config.TASK_INTERVAL_MONTHLY) ||
                repeatPattern.equals(Config.TASK_INTERVAL_YEARLY)) {
            this.repeatPattern = repeatPattern;
        } else {
            throw new NullPointerException
                    ("taskInterval is not match");
    }
    this.theDay = theDay;
    this.theWeekDay = theWeekDay;
    this.doTime = doTime;
    }

    public Long getMasterTaskNum() {
        return MasterTaskNum;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getRepeatPattern() {
        return repeatPattern;
    }

    public Integer getTheDay() {
        return theDay;
    }

    public String[] getTheWeekDay() {
        return theWeekDay;
    }

    public String getDoTime() {
        return doTime;
    }
}
