package info.androidhive.slidingmenu.listener;

import android.app.Activity;
import android.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;

import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.view.UnDoneListFragment;
import info.androidhive.slidingmenu.adapter.TaskItemListAdapter;
import info.androidhive.slidingmenu.model.TaskItem;
import info.androidhive.slidingmenu.model.ToDoTask;

/**
 * Created by fukuokak on 2014/10/24.
 */
public class UnDoneListItemOnItemClickListener implements AdapterView.OnItemClickListener {
    private Activity activity;
    private TaskItemListAdapter adapter;

    public UnDoneListItemOnItemClickListener(Activity activity, TaskItemListAdapter adapter) {
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

        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, new UnDoneListFragment()).commit();
    }
}
