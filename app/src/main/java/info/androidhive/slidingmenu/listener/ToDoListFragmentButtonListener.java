package info.androidhive.slidingmenu.listener;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.adapter.TaskItemListAdapter;
import info.androidhive.slidingmenu.model.TaskItem;
import info.androidhive.slidingmenu.model.ToDoTask;
import info.androidhive.slidingmenu.view.AddTaskItemFragment;

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
        Fragment fragment = new AddTaskItemFragment();
        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment).commit();
    }
}
