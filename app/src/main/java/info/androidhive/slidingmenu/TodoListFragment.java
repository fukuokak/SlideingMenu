package info.androidhive.slidingmenu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import info.androidhive.slidingmenu.adapter.TaskItemListAdapter;
import info.androidhive.slidingmenu.listener.ToDoListFragmentButtonListener;
import info.androidhive.slidingmenu.listener.toDoListItemOnItemClickListener;
import info.androidhive.slidingmenu.model.CalendarItem;
import info.androidhive.slidingmenu.model.TaskItem;
import info.androidhive.slidingmenu.model.ToDoListSaveItem;

/**
 * Created by fukuokak on 2014/10/24.
 */
public class TodoListFragment extends Fragment {
    ListView toDoList;
    View rootView;
    ArrayList<CalendarItem> ciArray;
    private int position;

    public TodoListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_todo, container, false);
        ImageButton addButton = (ImageButton) rootView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new ToDoListFragmentButtonListener(getActivity()));

        toDoList = (ListView) rootView.findViewById(R.id.toDoListView);
        toDoList.setOnItemClickListener(new toDoListItemOnItemClickListener());

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.ciArray = (ArrayList<CalendarItem>) this.getArguments().get(Config.PUT_EXTRA_CALENDAR_ITEM);
        this.position = this.getArguments().getInt(Config.PUT_EXTRA_POSITION);
        toDoList = (ListView) rootView.findViewById(R.id.toDoListView);

        try {
            toDoList.setAdapter(setInitialTaskItemAdapter(ciArray.get(this.position - 1)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public TaskItemListAdapter setInitialTaskItemAdapter(CalendarItem calendarItem) throws IOException {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,calendarItem.getYear());
        calendar.set(Calendar.MONTH,calendarItem.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH,calendarItem.getDay());

        ToDoListSaveItem tsi = new ToDoListSaveItem(getActivity());
        ArrayList<TaskItem> taskItemArrayList = tsi.getInitialTaskItem(calendar);

        for(int i = 0 ; i < taskItemArrayList.size() ; i++){
            tsi.saveTaskItem(taskItemArrayList.get(i));
        }

        TaskItemListAdapter todoAdapter = new TaskItemListAdapter(getActivity().getApplicationContext(),
                taskItemArrayList);
        return todoAdapter;
    }
}
