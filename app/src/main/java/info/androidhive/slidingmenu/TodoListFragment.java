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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import info.androidhive.slidingmenu.adapter.TaskItemListAdapter;
import info.androidhive.slidingmenu.listener.ToDoListFragmentButtonListener;
import info.androidhive.slidingmenu.listener.toDoListItemOnItemClickListener;
import info.androidhive.slidingmenu.model.TaskItem;

/**
 * Created by fukuokak on 2014/10/24.
 */
public class TodoListFragment extends Fragment {
    ListView toDoList;
    View rootView;

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
        toDoList = (ListView) rootView.findViewById(R.id.toDoListView);

        toDoList.setAdapter(setInitialTaskItemAdapter());

    }

    public TaskItemListAdapter setInitialTaskItemAdapter() {
        ArrayList<TaskItem> taskItemArrayList = new ArrayList<TaskItem>();

        //Todo ここはCalendarクラスを使用しなければならない？かMainActivityから引き渡しをする。
        Calendar calendar =Calendar.getInstance();
        calendar.set(2014, Calendar.DECEMBER ,24);

        TaskItem ti1 = new TaskItem(calendar, 1, "睡眠", TaskItem.TASK_INTERVAL_DAILY, "00:00");
        TaskItem ti2 = new TaskItem(calendar, 2, "メリークリスマス", TaskItem.TASK_INTERVAL_YEARLY, "12:00");
        taskItemArrayList.add(ti1);
        taskItemArrayList.add(ti2);

//        getInitialTaskItem(Calendar.getInstance());

        TaskItemListAdapter todoAdapter = new TaskItemListAdapter(getActivity().getApplicationContext(),
                taskItemArrayList);
        return todoAdapter;
    }
    public ArrayList<TaskItem> getInitialTaskItem(Calendar calendar){
        ArrayList<TaskItem> initialTaskItem = new ArrayList<TaskItem>();
        try{
        InputStream in = getActivity().openFileInput("a.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                in, "UTF-8"));
        String s;
        while ((s = reader.readLine()) != null) {
            Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }

    return null ;
    }
}
