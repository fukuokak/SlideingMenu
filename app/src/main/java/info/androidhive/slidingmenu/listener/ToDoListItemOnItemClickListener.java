package info.androidhive.slidingmenu.listener;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

import info.androidhive.slidingmenu.adapter.TaskItemListAdapter;
import info.androidhive.slidingmenu.model.TaskItem;
import info.androidhive.slidingmenu.model.ToDoTask;

/**
 * Created by fukuokak on 2014/10/24.
 */
public class ToDoListItemOnItemClickListener implements AdapterView.OnItemClickListener {
    private Activity activity;
    private TaskItemListAdapter adapter;

    public ToDoListItemOnItemClickListener(Activity activity, TaskItemListAdapter adapter) {
        this.activity = activity;
        this.adapter = adapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Boolean executeFlag = Boolean.TRUE;
        TaskItem taskItem = adapter.getItem(position);

        if (taskItem.getExecuteStatus() == Boolean.TRUE) {
            executeFlag = Boolean.FALSE;
        }
        TaskItem modifyTaskItem = new TaskItem(taskItem.getCalendar(), taskItem.getTaskNum(), taskItem.getTaskTitle(), taskItem.getRepeatPattern(), taskItem.getDoTime(), executeFlag);
        ;

        ToDoTask tdt = new ToDoTask(activity);
        tdt.updateTaskItem(modifyTaskItem);

//        FragmentManager fragmentManager = activity.getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.frame_container, new ToDoListFragment()).commit();
    }
}
