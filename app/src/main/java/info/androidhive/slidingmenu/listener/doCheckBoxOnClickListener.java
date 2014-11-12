package info.androidhive.slidingmenu.listener;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by fukuokak on 2014/11/11.
 */
public class doCheckBoxOnClickListener implements View.OnClickListener {
    private Activity activity;

    public doCheckBoxOnClickListener (Activity activity){
        this.activity = activity;
    }
    @Override
    public void onClick(View v) {
        Toast.makeText(activity,"aaa",Toast.LENGTH_SHORT).show();
    }
}
