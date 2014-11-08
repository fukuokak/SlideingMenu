package info.androidhive.slidingmenu.listener;

import android.app.Activity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Calendar;

import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.model.TaskItem;
import info.androidhive.slidingmenu.model.ToDoListSaveItem;

/**
 * Created by fukuokak on 2014/11/08.
 */
public class AddTaskItemButtonOnClickListener implements View.OnClickListener {

    private Activity activity ;

    public AddTaskItemButtonOnClickListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        EditText taskTitle = (EditText) activity.findViewById(R.id.add_task_item_task_title_value);

        RadioGroup repeatFlag = (RadioGroup)activity.findViewById(R.id.add_task_item_task_repeat_group);
        int radioId = repeatFlag.getCheckedRadioButtonId();
        String repeatFragValue = getRepeatFragValue(radioId);

        Spinner hourSpin = (Spinner)activity.findViewById(R.id.add_task_item_task_hour);
        String hourSpinValue  = (String) hourSpin.getSelectedItem();

        Spinner minutesSpin = (Spinner)activity.findViewById(R.id.add_task_item_task_minutes);
        String minutesSpinValue = (String) minutesSpin.getSelectedItem();

        DatePicker datePicker = (DatePicker)activity.findViewById(R.id.datePicker);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,datePicker.getYear());
        calendar.set(Calendar.MONTH,datePicker.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());

        TaskItem taskItem = new TaskItem(calendar,1,taskTitle.getText().toString(),repeatFragValue , hourSpinValue+minutesSpinValue);

        ToDoListSaveItem tdls = new ToDoListSaveItem(activity);
        tdls.saveTaskItem(taskItem);
    }

    private String getRepeatFragValue(int radioId){
        String repeatFragValue = "";
        switch (radioId){
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
