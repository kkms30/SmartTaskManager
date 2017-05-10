package pl.kkms.smarttask.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Locale;

import pl.kkms.smarttask.database.IDatabaseObject;
import pl.kkms.smarttask.database.SmartTaskApplication;


/**
 * Created by Damian on 2016-12-09.
 */

public abstract class BaseDao<T extends IDatabaseObject> {

    private static final String TAG = "BaseDao";
    private String mTableName;

    public BaseDao(String tableName){
        mTableName = tableName;
    }

    protected abstract T getObjectFromCursor(Cursor cursor);
    protected abstract ContentValues getObjectContentValues(T object);

    protected Cursor getRawQueryFromCursor(String sql) {
        Cursor cursor = SmartTaskApplication.getInstance().getDbHelper().getWritableDatabase().rawQuery(sql, null);
        return cursor;
    }

    public long insertObject(T object) {
        SQLiteDatabase database = SmartTaskApplication.getInstance().getDbHelper().getWritableDatabase();

        ContentValues values = getObjectContentValues(object);
        long objectId = database.insert(mTableName, null, values);
        return objectId;
    }

    public long updateObject(T object) {
        SQLiteDatabase database = SmartTaskApplication.getInstance().getDbHelper().getWritableDatabase();

        ContentValues values = getObjectContentValues(object);

        String whereClause = String.format(Locale.getDefault(), "id = %d", object.getId());

        return database.update(mTableName, values, whereClause, null);
    }

    public boolean deleteObject(T object) {
        SQLiteDatabase database = SmartTaskApplication.getInstance().getDbHelper().getWritableDatabase();

        String whereClause = String.format(Locale.getDefault(), "id = %d", object.getId());

        return database.delete(mTableName, whereClause, null) > 0;
    }

    public boolean deleteAll() {
        SQLiteDatabase database = SmartTaskApplication.getInstance().getDbHelper().getWritableDatabase();

        return database.delete(mTableName, null, null) > 0;
    }
}
