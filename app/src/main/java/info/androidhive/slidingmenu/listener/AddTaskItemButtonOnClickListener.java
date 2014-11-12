package info.androidhive.slidingmenu.listener;

import android.app.Activity;
import android.app.FragmentManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Calendar;

import info.androidhive.slidingmenu.AddTaskItemFragment;
import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.model.TaskItem;
import info.androidhive.slidingmenu.model.ToDoTask;

/**
 * Created by fukuokak on 2014/11/08.
 */
public class AddTaskItemButtonOnClickListener implements View.OnClickListener {

    private Activity activity;

    public AddTaskItemButtonOnClickListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        EditText taskTitle = (EditText) activity.findViewById(R.id.add_task_item_task_title_value);
        String TitleValue = taskTitle.getText().toString();

        Spinner hourSpin = (Spinner) activity.findViewById(R.id.add_task_item_task_hour);
        String hourSpinValue = (String) hourSpin.getSelectedItem();

        Spinner minutesSpin = (Spinner) activity.findViewById(R.id.add_task_item_task_minutes);
        String minutesSpinValue = (String) minutesSpin.getSelectedItem();

        RadioGroup repeatFlag = (RadioGroup) activity.findViewById(R.id.add_task_item_task_repeat_group);
        int radioId = repeatFlag.getCheckedRadioButtonId();
        String repeatFragValue = getRepeatFragValue(radioId);

        if (repeatFragValue.equals(TaskItem.TASK_INTERVAL_NO_REPEAT)) {
            try {
                saveNoRepeatTask( TitleValue, repeatFragValue, hourSpinValue , minutesSpinValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else{
            saveRepeatTask(TitleValue, repeatFragValue, hourSpinValue , minutesSpinValue);

        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, new AddTaskItemFragment()).commit();

    }

    private void saveNoRepeatTask(String TitleValue, String repeatFragValue, String hourSpinValue, String minutesSpinValue) throws InterruptedException {
        Calendar calendar = Calendar.getInstance();
        DatePicker datePicker = (DatePicker) activity.findViewById(R.id.datePicker);
        calendar.set(Calendar.YEAR, datePicker.getYear());
        calendar.set(Calendar.MONTH, datePicker.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());

        TaskItem taskItem = new TaskItem(calendar, TitleValue, repeatFragValue, hourSpinValue + minutesSpinValue);

        ToDoTask tdls = new ToDoTask(activity);
        tdls.saveTaskItem(taskItem);

    }

    private void saveRepeatTask(String titleValue, String repeatFragValue, String hourSpinValue, String minutesSpinValue) {

    }

    private String getRepeatFragValue(int radioId) {
        String repeatFragValue = "";
        switch (radioId) {
            case R.id.add_task_item_task_repeat_no_repeat:
                repeatFragValue = TaskItem.TASK_INTERVAL_NO_REPEAT;
                break;
            case R.id.add_task_item_task_repeat_daily:
                repeatFragValue = TaskItem.TASK_INTERVAL_DAILY;
                break;
            case R.id.add_task_item_task_repeat_weekly:
                repeatFragValue = TaskItem.TASK_INTERVAL_WEEKLY;
                break;
            case R.id.add_task_item_task_repeat_monthly:
                repeatFragValue = TaskItem.TASK_INTERVAL_MONTHLY;
                break;
            case R.id.add_task_item_task_repeat_year:
                repeatFragValue = TaskItem.TASK_INTERVAL_YEARLY;
                break;
            default:
                break;
        }
        return repeatFragValue;

    }

}
