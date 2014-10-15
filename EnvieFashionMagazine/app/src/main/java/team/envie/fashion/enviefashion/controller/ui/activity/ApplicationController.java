package team.envie.fashion.enviefashion.controller.ui.activity;

import android.app.Application;
import android.content.res.Configuration;
import android.os.StrictMode;

public class ApplicationController extends Application {

    // *******************************************************************
    // Constants
    // *******************************************************************
    /** debug mode */
    public static final boolean DEBUG_MODE = true;

    public static int position = 0;


    // *******************************************************************
    // LifeCycle
    // *******************************************************************
    @Override
    public void onCreate() {
        doStrictMode();
        super.onCreate();
        team.envie.fashion.enviefashion.utils.Logged.i("Application onCreate");
    }



    @Override
    public void onLowMemory() {
        team.envie.fashion.enviefashion.utils.Logged.i("Application onLowMemory");
    }

    @Override
    public void onTerminate() {
        team.envie.fashion.enviefashion.utils.Logged.i("Application onTerminate");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    }

    // *******************************************************************
    // Method
    // *******************************************************************
    /** StrictMode */
    private void doStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }
}

