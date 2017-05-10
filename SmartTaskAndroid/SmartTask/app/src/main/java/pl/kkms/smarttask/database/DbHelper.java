package pl.kkms.smarttask.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pl.kkms.smarttask.R;

/**
 * Created by Damian on 2017-04-20.
 */

public class DbHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String NAME = "smarttask.db";
    private static final int VERSION = 1;

    public DbHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(context.getString(R.string.sql_create_table_users));
        db.execSQL(context.getString(R.string.sql_create_table_tasks));
        db.execSQL(context.getString(R.string.sql_create_table_users_tasks));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public String getSql(int sqlId, Object... args) {
        String sqlFormat = getSql(sqlId);
        return String.format(sqlFormat, args);
    }

    public String getSql(int sqlId) {
        return context.getString(sqlId);
    }
}
