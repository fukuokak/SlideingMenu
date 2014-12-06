package info.androidhive.slidingmenu.listener;

import android.app.Activity;
import android.app.FragmentManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Calendar;

import info.androidhive.slidingmenu.Config;
import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.model.CalendarUtil;
import info.androidhive.slidingmenu.model.ReserveTaskItem;
import info.androidhive.slidingmenu.model.TaskItem;
import info.androidhive.slidingmenu.model.ToDoTask;
import info.androidhive.slidingmenu.view.AddTaskItemFragment;

/**
 * Created by fukuokak on 2014/12/03.
 */
public class AddReserveTaskItemButtonOnClickListener implements View.OnClickListener {
    private Activity activity;

    public AddReserveTaskItemButtonOnClickListener(Activity activity) {
        this.activity = activity ;
    }

    @Override
    public void onClick(View v) {
        EditText taskTitle = (EditText) activity.findViewById(R.id.add_reserve_task_item_task_title_value);
        String TitleValue = taskTitle.getText().toString();

        Spinner hourSpin = (Spinner) activity.findViewById(R.id.add_reserve_task_item_task_hour);
        String hourSpinValue = (String) hourSpin.getSelectedItem();

        Spinner minutesSpin = (Spinner) activity.findViewById(R.id.add_reserve_task_item_task_minutes);
        String minutesSpinValue = (String) minutesSpin.getSelectedItem();

        RadioGroup repeatFlag = (RadioGroup) activity.findViewById(R.id.add_reserve_task_item_task_repeat_group);
        int radioId = repeatFlag.getCheckedRadioButtonId();
        String repeatFragValue = getRepeatFlagValue(radioId);

        saveRepeatTask(TitleValue, repeatFragValue, hourSpinValue, minutesSpinValue);

        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, new AddTaskItemFragment()).commit();

    }

    private void saveRepeatTask(String titleValue, String repeatFragValue, String hourSpinValue, String minutesSpinValue) {


    }

    private String getRepeatFlagValue(int radioId) {
        String repeatFlagValue = "";
        switch (radioId) {
            case R.id.add_reserve_task_item_task_repeat_daily:
                repeatFlagValue = Config.TASK_INTERVAL_DAILY;
                break;
            case R.id.add_reserve_task_item_task_repeat_weekly:
                repeatFlagValue = Config.TASK_INTERVAL_WEEKLY;
                break;
            case R.id.add_reserve_task_item_task_repeat_monthly:
                repeatFlagValue = Config.TASK_INTERVAL_MONTHLY;
                break;
            case R.id.add_reserve_task_item_task_repeat_year:
                repeatFlagValue = Config.TASK_INTERVAL_YEARLY;
                break;
            default:
                break;
        }
        return repeatFlagValue;

    }

}
