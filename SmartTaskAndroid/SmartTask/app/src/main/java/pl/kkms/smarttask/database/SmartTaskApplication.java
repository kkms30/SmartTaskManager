package pl.kkms.smarttask.database;

import android.app.Application;

/**
 * Created by Damian on 2017-04-20.
 */

public class SmartTaskApplication extends Application{

    private static SmartTaskApplication instance;

    protected SmartTaskApplication() {}

    private DbHelper mDbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static SmartTaskApplication getInstance() {
        return instance;
    }

    public DbHelper getDbHelper(){
        if(mDbHelper == null) {
            mDbHelper = new DbHelper(this);
        }
        return mDbHelper;
    }
}
