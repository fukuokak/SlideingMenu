package info.androidhive.slidingmenu.model;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import info.androidhive.slidingmenu.Config;

/**
 * Created by fukuokak on 2014/12/07.
 */
public class PlannedTask {
    private Activity activity;
    private String fileName = Config.RESERVE_TASK_FILE_NAME;

    public PlannedTask(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<MasterTaskItem> getPlannedTask() throws IOException {
        ArrayList<MasterTaskItem> masterTasks = readTaskFile(fileName);
        return masterTasks;
    }

    private ArrayList<MasterTaskItem> readTaskFile(String filename) throws IOException {
        ArrayList<MasterTaskItem> masterTasks = new ArrayList<MasterTaskItem>();
        InputStream in = activity.openFileInput(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                in, "UTF-8"));
        String s;
        while ((s = reader.readLine()) != null) {
            MasterTaskItem MasterTaskItem = convertSaveFormatToReserveTaskItem(s);
            masterTasks.add(MasterTaskItem);
        }
        return masterTasks;
    }

    public void storeMasterTask() {

    }

    public void updateMAsterTask() {

    }

    public String convertReserveTaskItemToSaveFormat(MasterTaskItem taskItem) {
        String s = "";
        return s;
    }

    public MasterTaskItem convertSaveFormatToReserveTaskItem(String s) {
        MasterTaskItem rti = null;
        return rti;
    }
}
