package info.androidhive.slidingmenu.model;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import info.androidhive.slidingmenu.Config;

/**
 * Created by fukuokak on 2014/11/04.
 */
public class UnDoneTask {

    private Activity activity;

    public UnDoneTask(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<TaskItem> getUnDoneTask(Calendar today) {
        ArrayList<TaskItem> unDoneTasks = readTaskFile(Config.TASK_FILE_NAME);
        return unDoneTasks;
    }

    private ArrayList<TaskItem> readTaskFile(String taskFileName) {
        ArrayList<TaskItem> unDoneTasks = new ArrayList<TaskItem>();
        CalendarUtil cutil = new CalendarUtil();

            try {
                InputStream in = activity.openFileInput(taskFileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        in, "UTF-8"));
                    String s;
                    while ((s = reader.readLine()) != null) {
                        TaskItem unDoneTaskItem = convertSaveFormatToTaskItem(s);
                        int diff = cutil.compareCalendar(unDoneTaskItem.getCalendar(),Calendar.getInstance());
                        if (diff < 0 ){
//Todo 終了いるものを表示しない場合はここをコメントアウトを解除
//                                && unDoneTaskItem.getExecuteStatus() == false) {
                    unDoneTasks.add(unDoneTaskItem);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return unDoneTasks;
    }



    private TaskItem convertSaveFormatToTaskItem(String s) {

        String[] ss = s.split(",", -1);
        for (int i = 0; i < ss.length; i++) {
            ss[i]=ss[i].replace("\"","");
        }
        String[] sc = ss[0].split("/", -1);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,  Integer.valueOf(sc[0]));
        calendar.set(Calendar.MONTH, Integer.valueOf(sc[1])-1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(sc[2]));

        TaskItem taskItem = new TaskItem(
                calendar,
                Long.parseLong(ss[1].toString()),
                ss[2].toString(),
                ss[3].toString(),
                ss[4].toString(),
                Boolean.valueOf(ss[5].toString())
        );

        return taskItem;
    }
}
