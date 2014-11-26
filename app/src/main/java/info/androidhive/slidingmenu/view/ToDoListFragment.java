package info.androidhive.slidingmenu.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;

import info.androidhive.slidingmenu.Config;
import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.adapter.TaskItemListAdapter;
import info.androidhive.slidingmenu.listener.ToDoListFragmentButtonListener;
import info.androidhive.slidingmenu.listener.ToDoListItemOnItemClickListener;
import info.androidhive.slidingmenu.model.CalendarItem;
import info.androidhive.slidingmenu.model.TaskItem;
import info.androidhive.slidingmenu.model.ToDoTask;

/**
 * Created by fukuokak on 2014/10/24.
 */
public class ToDoListFragment extends Fragment {
    ListView toDoList;
    View rootView;
    ArrayList<CalendarItem> ciArray;
    int position;

    public ToDoListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_todo, container, false);
        ImageButton addButton = (ImageButton) rootView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new ToDoListFragmentButtonListener(getActivity()));
        this.ciArray = (ArrayList<CalendarItem>) this.getArguments().get(Config.PUT_EXTRA_CALENDAR_ITEM);
        this.position = this.getArguments().getInt(Config.PUT_EXTRA_POSITION);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        toDoList = (ListView) rootView.findViewById(R.id.toDoListView);
        TaskItemListAdapter adapter = null;
        try {
            adapter = setToDoTaskListAdapter(ciArray.get(this.position - 1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        toDoList.setAdapter(adapter);
        toDoList.setOnItemClickListener(new ToDoListItemOnItemClickListener(getActivity(), adapter));
    }

    public TaskItemListAdapter setToDoTaskListAdapter(CalendarItem calendarItem) throws IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendarItem.getYear());
        calendar.set(Calendar.MONTH, calendarItem.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, calendarItem.getDay());

        ToDoTask toDoTask = new ToDoTask(getActivity());
        ArrayList<TaskItem> taskItemArrayList = toDoTask.getInitialTaskItem(calendar);
        TaskItemListAdapter todoAdapter = new TaskItemListAdapter(getActivity().getApplicationContext(),
                taskItemArrayList);
        return todoAdapter;
    }
}
