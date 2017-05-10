package pl.kkms.smarttask.database.dao;

import android.content.ContentValues;
import android.database.Cursor;

import pl.kkms.smarttask.model.User;

/**
 * Created by Damian on 2017-04-20.
 */

public class UserDao extends BaseDao<User> {

    private static final String TAG = "USER_DAO";

    public static final String TABLE_NAME = "users";

    private static final String ID = "id";
    private static final String ID_USER = "iduser";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String USERCODE = "usercode";
    private static final String ADDTIME = "addtime";

    public UserDao() {
        super(TABLE_NAME);
    }

    @Override
    protected User getObjectFromCursor(Cursor cursor) {
        User user = new User();
        int columnIndex = cursor.getColumnIndex(ID);
        user.setId(cursor.getLong(columnIndex));
        columnIndex = cursor.getColumnIndex(ID_USER);
        user.setIdUser(cursor.getInt(columnIndex));
        columnIndex = cursor.getColumnIndex(LOGIN);
        user.setLogin(cursor.getString(columnIndex));
        columnIndex = cursor.getColumnIndex(PASSWORD);
        user.setPassword(cursor.getString(columnIndex));
        columnIndex = cursor.getColumnIndex(NAME);
        user.setName(cursor.getString(columnIndex));
        columnIndex = cursor.getColumnIndex(SURNAME);
        user.setSurname(cursor.getString(columnIndex));
        columnIndex = cursor.getColumnIndex(USERCODE);
        user.setUserCode(cursor.getString(columnIndex));
        columnIndex = cursor.getColumnIndex(ADDTIME);
        user.setAddTime(cursor.getString(columnIndex));

        return user;
    }

    @Override
    protected ContentValues getObjectContentValues(User object) {
        ContentValues values = new ContentValues();

        values.put(ID_USER, object.getIdUser());
        values.put(LOGIN, object.getLogin());
        values.put(PASSWORD, object.getPassword());
        values.put(NAME, object.getName());
        values.put(SURNAME, object.getSurname());
        values.put(USERCODE, object.getUserCode());
        values.put(ADDTIME, object.getAddTime());

        return values;
    }
}
