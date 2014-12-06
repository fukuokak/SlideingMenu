package info.androidhive.slidingmenu.model;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;

import info.androidhive.slidingmenu.Config;

/**
 * Created by fukuokak on 2014/10/25.
 */
public class ToDoTask {

    private Activity activity;
    private CalendarUtil cUtil = new CalendarUtil();

    public ToDoTask(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<TaskItem> getTaskItems(Calendar calendar) throws IOException {
        ArrayList<TaskItem> taskItems = new ArrayList<TaskItem>();
        try {
            InputStream in = activity.openFileInput(Config.TASK_FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String s;
            while ((s = reader.readLine()) != null) {
                TaskItem ti = convertSaveFormatToTaskItem(s);
                int diff = cUtil.compareCalendar(ti.getCalendar(), calendar);
                if (diff == 0) {
                    taskItems.add(ti);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskItems;
    }

    public void updateTaskItem(TaskItem modifyTaskItem) {
        ArrayList<TaskItem> taskItems = new ArrayList<TaskItem>();
        Long modifyTaskItemNumber = modifyTaskItem.getTaskNum();

        try {

            InputStream in = activity.openFileInput(Config.TASK_FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String s;
            while ((s = reader.readLine()) != null) {
                TaskItem ti = convertSaveFormatToTaskItem(s);
                Long tiNum = ti.getTaskNum();
                if (tiNum.equals(modifyTaskItemNumber)) {
                } else {
                    taskItems.add(ti);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        taskItems.add(modifyTaskItem);
        storeTaskItems(taskItems);
    }

    public void storeTaskItems(ArrayList<TaskItem> taskItems) {
        try {
            OutputStream out = activity.openFileOutput(Config.TASK_FILE_NAME, Activity.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));

            for (int i = 0; i < taskItems.size(); i++) {
                TaskItem taskItem = taskItems.get(i);
                writer.append(convertTaskItemToSaveFormat(taskItem) + "\r\n");
            }
            writer.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveTaskItem(TaskItem taskItem) {
        try {
            OutputStream out = activity.openFileOutput(Config.TASK_FILE_NAME, Activity.MODE_APPEND | Activity.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.append(convertTaskItemToSaveFormat(taskItem) + "\r\n");
            writer.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertTaskItemToSaveFormat(TaskItem taskItem) {
        String s =
                '"' + taskItem.getScheduleDateString().toString() + "\"," +
                        '"' + taskItem.getTaskNum() + "\"," +
                        '"' + taskItem.getTaskTitle() + "\"," +
                        '"' + taskItem.getRepeatPattern() + "\"," +
                        '"' + taskItem.getDoTime() + "\"," +
                        '"' + taskItem.getExecuteStatus() + "\"," +
                        '"' + taskItem.getCalendar().toString() + "\"";
        return s;
    }

    private TaskItem convertSaveFormatToTaskItem(String s) {
        String[] ss = s.split(",", -1);
        for (int i = 0; i < ss.length; i++) {
            ss[i] = ss[i].replace("\"", "");
        }
        String[] sc = ss[0].split("/", -1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.valueOf(sc[0]));
        calendar.set(Calendar.MONTH, Integer.valueOf(sc[1]) - 1);
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
