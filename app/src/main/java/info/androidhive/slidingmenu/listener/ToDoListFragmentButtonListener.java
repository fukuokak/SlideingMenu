package info.androidhive.slidingmenu.listener;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.adapter.TaskItemListAdapter;
import info.androidhive.slidingmenu.model.TaskItem;
import info.androidhive.slidingmenu.model.ToDoTask;

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
        ToDoTask tsi = new ToDoTask(activity);

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
