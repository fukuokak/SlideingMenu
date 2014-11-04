package info.androidhive.slidingmenu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.model.NavDrawerItem;
import info.androidhive.slidingmenu.model.TaskItem;

/**
 * Created by fukuokak on 2014/10/24.
 */
public class TaskItemListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TaskItem> taskItems;

    public TaskItemListAdapter(Context context, ArrayList<TaskItem> taskItems){
        this.context = context;
        this.taskItems = taskItems;
    }

    @Override
    public int getCount() {
        return taskItems.size();
    }

    @Override
    public TaskItem getItem(int position) {
        return taskItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView taskNum ;
        TextView taskTitle;
        TextView taskInterval;
        CheckBox doneCheckBox ;
        View view = convertView;

        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_todo,null);
        }
        TaskItem taskItem = getItem(position);
        if (taskItem != null){
            taskNum = (TextView)view.findViewById(R.id.taskNum);
            taskTitle =  (TextView)view.findViewById(R.id.taskTitle);
            taskInterval= (TextView)view.findViewById(R.id.taskInterval);

            taskNum.setText(Integer.toString(taskItem.getTaskNum()));
            taskTitle.setText(taskItem.getTaskTitle());
//            taskTitle.setText(taskItem.getTaskTitle()+"<"+taskItem.getDoTime()+">");
            taskInterval.setText(taskItem.getRepeatPattern());
        }
        return view;
    }
}
