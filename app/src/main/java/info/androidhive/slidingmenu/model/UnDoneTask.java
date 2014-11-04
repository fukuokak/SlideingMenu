package info.androidhive.slidingmenu.model;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by fukuokak on 2014/11/04.
 */
public class UnDoneTask {

    private Activity activity;
    private static String TASK_FILE_NAME = "TaskFile.txt";

    public UnDoneTask(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<TaskItem> getUnDoneTask(Calendar today) {
        ArrayList<TaskItem> unDoneTasks = readTaskFile(TASK_FILE_NAME);
        return unDoneTasks;
    }

    private ArrayList<TaskItem> readTaskFile(String taskFileName) {
        ArrayList<TaskItem> unDoneTasks = new ArrayList<TaskItem>();

        try {
            InputStream in = activity.openFileInput(taskFileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    in, "UTF-8"));
            String s;
            while ((s = reader.readLine()) != null) {
                TaskItem unDoneTaskItem = splitSaveLine(s);
                int diff = unDoneTaskItem.getCalendar().compareTo(Calendar.getInstance());
                if (diff < 0 ||
                        unDoneTaskItem.getExecuteStatus() == false) {
                    unDoneTasks.add(unDoneTaskItem);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return unDoneTasks;
    }

    private TaskItem splitSaveLine(String s) {
        Object[] saveLine = s.split(",", -1);
        //Todo:どうしようかな
        //取得したTaskのSchedule情報を扱うのがめんどくさい。
        Calendar calendar = Calendar.getInstance();
        TaskItem saveTask = new TaskItem(calendar,
                Integer.valueOf(saveLine[1].toString()),
                saveLine[2].toString(),
                saveLine[3].toString(),
                saveLine[4].toString(),
                Boolean.valueOf(saveLine[5].toString()),
                Boolean.valueOf(saveLine[6].toString()));
        return saveTask;
    }
}
