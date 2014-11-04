package info.androidhive.slidingmenu.listener;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.TodoListFragment;
import info.androidhive.slidingmenu.adapter.TaskItemListAdapter;
import info.androidhive.slidingmenu.model.TaskItem;
import info.androidhive.slidingmenu.model.ToDoListSaveItem;

/**
 * Created by fukuokak on 2014/10/24.
 */
public class ToDoListFragmentButtonListener implements View.OnClickListener {
    private Activity activity;

    public ToDoListFragmentButtonListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addButton:
                try {
                    onClickAddButton(v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        ;
    }

    public void onClickAddButton(View v) throws IOException {

        ListView toDoList = (ListView) activity.findViewById(R.id.toDoListView);


        TaskItemListAdapter todoAdapter = new TaskItemListAdapter(activity.getApplicationContext(),
                getTaskItem());

        toDoList.setAdapter(todoAdapter);
    }

    public ArrayList<TaskItem> getTaskItem() throws IOException {
        ToDoListSaveItem tsi = new ToDoListSaveItem(activity);

        ArrayList<TaskItem> taskItemArrayList = tsi.getInitialTaskItem(Calendar.getInstance());

//        Calendar calendar =Calendar.getInstance();
//        calendar.set(2014, Calendar.JANUARY ,24);

//        TaskItem ti1 = new TaskItem(calendar, 1, "睡眠", TaskItem.TASK_INTERVAL_DAILY, "00:00");
//        TaskItem ti2 = new TaskItem(calendar, 2, "ご挨拶", TaskItem.TASK_INTERVAL_YEARLY, "01:00");
//        taskItemArrayList.add(ti1);
//        taskItemArrayList.add(ti2);
        return taskItemArrayList;
    }


}
