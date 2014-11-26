package info.androidhive.slidingmenu.model;

import java.util.Calendar;
import java.util.Date;

import info.androidhive.slidingmenu.Config;

/**
 * Created by fukuokak on 2014/10/24.
 */
public class TaskItem {
    private Calendar calendar;
    private String scheduleDateString;
    private Long taskNum;
    private String taskTitle;
    private String repeatPattern;
    private String doTime;
    private Boolean executeStatus;

    /*
    *新規のTaskItemを生成するときに使用する。
     */
    public TaskItem(Calendar calendar, Long taskNum, String taskTitle, String repeatPattern, String doTime) throws NullPointerException {
        Date date = calendar.getTime();
        this.calendar = calendar;
        int monthString = calendar.get(Calendar.MONTH) + 1;
        this.scheduleDateString = String.valueOf(calendar.get(Calendar.YEAR) + "/" + monthString + "/" + calendar.get(Calendar.DAY_OF_MONTH));
        this.taskNum = taskNum;
        this.taskTitle = taskTitle;
        if (repeatPattern.equals(Config.TASK_INTERVAL_DAILY) ||
                repeatPattern.equals(Config.TASK_INTERVAL_WEEKLY) ||
                repeatPattern.equals(Config.TASK_INTERVAL_MONTHLY) ||
                repeatPattern.equals(Config.TASK_INTERVAL_YEARLY) ||
                repeatPattern.equals(Config.TASK_INTERVAL_NO_REPEAT)) {
            this.repeatPattern = repeatPattern;
        } else {
            throw new NullPointerException
                    ("taskInterval is not match");
        }
        this.doTime = doTime;
        this.executeStatus = false;
    }

    /*
    ファイル読み出し、ファイル書き出しで既存のTaskItemを操作する時に使用する
     */
    public TaskItem(Calendar calendar, Long taskNum, String taskTitle, String repeatPattern, String doTime, Boolean executeStatus) throws NullPointerException {
        this.calendar = calendar;
        int monthString = calendar.get(Calendar.MONTH) + 1;
        this.scheduleDateString = String.valueOf(calendar.get(Calendar.YEAR) + "/" + monthString + "/" + calendar.get(Calendar.DAY_OF_MONTH));
        this.taskNum = taskNum;
        this.taskTitle = taskTitle;
        this.repeatPattern = repeatPattern;
        this.doTime = doTime;
        this.executeStatus = executeStatus;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public String getScheduleDateString() {
        return scheduleDateString;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getRepeatPattern() {
        return repeatPattern;
    }

    public Long getTaskNum() {
        return taskNum;
    }

    public String getDoTime() {
        return doTime;
    }

    public Boolean getExecuteStatus() {
        return executeStatus;
    }

}