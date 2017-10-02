package ca.prieto.countbook;

import android.app.Application;
import android.content.Context;

/**
 * Created by Jessica on 2017-10-01.
 */

public class CountBookApplication extends Application {
    
    private static Context context;

    public void onCreate() {
        super.onCreate();
        CountBookApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return CountBookApplication.context;
    }
}
