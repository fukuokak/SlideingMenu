package info.androidhive.slidingmenu.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.adapter.TaskItemListAdapter;
import info.androidhive.slidingmenu.listener.UnDoneListItemOnItemClickListener;
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
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        toDoList = (ListView) rootView.findViewById(R.id.toDoForgetListView);
        TaskItemListAdapter adapter = setNotDoneTaskListAdapter();
        toDoList.setAdapter(adapter);
        toDoList.setOnItemClickListener(new UnDoneListItemOnItemClickListener(getActivity(), adapter));
    }

    public TaskItemListAdapter setNotDoneTaskListAdapter() {
        Calendar calendar = Calendar.getInstance();
        UnDoneTask unDoneTask = new UnDoneTask(getActivity());
        ArrayList<TaskItem> taskItemArrayList = unDoneTask.getUnDoneTasks(calendar);
        TaskItemListAdapter todoAdapter = new TaskItemListAdapter(getActivity().getApplicationContext(),
                taskItemArrayList);
        return todoAdapter;
    }
}
