package info.androidhive.slidingmenu.model;

import android.app.Activity;

import org.json.JSONArray;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by fukuokak on 2014/10/25.
 */
public class ToDoListSaveItem {
    //ToDo:実装すること
    //TaskItem → 保管できる
    //ArrayList<TaskItem> → 保管できる
    //日にちごとに保管/取得できるITEMであること
    private Activity activity ;
    private String toDoListSaveItemKey ;
    private String toDoListSaveItemString ;
    private boolean toDoListSaveItemDoCheckBox ;

    public ToDoListSaveItem(Activity activity , TaskItem taskItem){
        this.activity = activity ;
        this.toDoListSaveItemKey = taskItem.getScheduleDateString() +',' +taskItem.getTaskNum();
        this.toDoListSaveItemString = taskItem.getTaskTitle()+',' +taskItem.getRepeatPattern();
        this.toDoListSaveItemDoCheckBox = taskItem.getExecuteStatus() ;
    }

    public ToDoListSaveItem(Activity activity , ArrayList<TaskItem> taskItemArray , boolean[] taskItemDo) {

    }
    public void saveFile() throws FileNotFoundException, UnsupportedEncodingException {

        try {
            OutputStream out = activity.openFileOutput("a.txt", Activity.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.append(toDoListSaveItemKey+","+toDoListSaveItemString+","+toDoListSaveItemDoCheckBox);
            writer.close();
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
