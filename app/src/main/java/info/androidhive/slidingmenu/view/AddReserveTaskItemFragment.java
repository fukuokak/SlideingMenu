package info.androidhive.slidingmenu.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Calendar;

import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.listener.AddReserveTaskItemButtonOnClickListener;
import info.androidhive.slidingmenu.listener.AddTaskItemButtonOnClickListener;


public class AddReserveTaskItemFragment extends Fragment {

    View rootView;
    private Calendar calendar;
    private String scheduleDateString;
    private int taskNum;
    private String taskTitle;
    private String repeatPattern;
    private String doTime;
    private Boolean executeStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_add_reserve_task_item, container, false);

        Spinner hourSpin = (Spinner) rootView.findViewById(R.id.add_reserve_task_item_task_hour);
        ArrayAdapter<CharSequence> taskHourAdapter = ArrayAdapter.createFromResource(
                getActivity().getApplicationContext(), R.array.task_hour,
                R.layout.time_spinner);
        taskHourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpin.setAdapter(taskHourAdapter);

        Spinner minutesSpin = (Spinner) rootView.findViewById(R.id.add_reserve_task_item_task_minutes);
        ArrayAdapter<CharSequence> taskMinutesAdapter = ArrayAdapter.createFromResource(
                getActivity().getApplicationContext(), R.array.task_minutes,
                R.layout.time_spinner);
        taskMinutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minutesSpin.setAdapter(taskMinutesAdapter);

        View addTaskItemButton = rootView.findViewById(R.id.add_task_item_add_button);
        addTaskItemButton.setOnClickListener(new AddReserveTaskItemButtonOnClickListener(getActivity()));

        return rootView;
    }


}
