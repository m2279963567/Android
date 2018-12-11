package apk.cn.notepad;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ExitApplication extends Application {

    private List<Activity> list = new ArrayList<Activity>();
    private static apk.cn.notepad.ExitApplication ea;

    private ExitApplication(){}

    public static apk.cn.notepad.ExitApplication getInstance(){
        if (null == ea){
            ea = new apk.cn.notepad.ExitApplication();
        }
        return ea;
    }

    public void addActivity(Activity activity){
        list.add(activity);
    }

    public void exit(Context context){
        for (Activity activity : list){
            activity.finish();
        }
        System.exit(0);
    }


}
