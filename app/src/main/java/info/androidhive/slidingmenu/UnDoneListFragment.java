package info.androidhive.slidingmenu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import info.androidhive.slidingmenu.adapter.TaskItemListAdapter;
import info.androidhive.slidingmenu.listener.toDoListItemOnItemClickListener;
import info.androidhive.slidingmenu.model.TaskItem;
import info.androidhive.slidingmenu.model.UnDoneTask;

/**
 * Created by fukuokak on 2014/10/29.
 */


public class UnDoneListFragment extends Fragment {
    ListView toDoList;
    View rootView;

    public UnDoneListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_todo_forgetlist, container, false);

        toDoList = (ListView) rootView.findViewById(R.id.toDoForgetListView);
        toDoList.setOnItemClickListener(new toDoListItemOnItemClickListener());

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        toDoList = (ListView) rootView.findViewById(R.id.toDoForgetListView);

        toDoList.setAdapter(setNotDoneTaskItemAdapter());

    }

    public TaskItemListAdapter setNotDoneTaskItemAdapter() {
        UnDoneTask unDoneTasks = new UnDoneTask(getActivity());
        ArrayList<TaskItem> taskItemArrayList = unDoneTasks.getUnDoneTask(Calendar.getInstance());
        TaskItemListAdapter todoAdapter = new TaskItemListAdapter(getActivity().getApplicationContext(),
                taskItemArrayList);
        return todoAdapter;
    }

    public ArrayList<TaskItem> getInitialTaskItem(Calendar calendar){
        ArrayList<TaskItem> initialTaskItem = new ArrayList<TaskItem>();
        try{
            InputStream in = getActivity().openFileInput(Config.TASK_FILE_NAME);
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
